package com.curtislb.adventofcode.year2023.day12.spring

/**
 * A condition record for a row of springs, which may lack complete information about the actual
 * conditions of springs in that row.
 *
 * @property conditions An ordered list of the conditions (both known and unknown) of all springs in
 *  the row.
 * @property brokenGroupSizes An ordered list of the sizes of all contiguous groups of broken
 *  springs in the row.
 *
 * @constructor Creates a new instance of [SpringRecord] with the given lists of spring [conditions]
 *  and [brokenGroupSizes].
 */
class SpringRecord(
    private val conditions: List<Condition>,
    private val brokenGroupSizes: List<Int>
) {
    /**
     * Returns the number of distinct arrangements of operational and broken springs that fit the
     * criteria for this record.
     *
     * An arrangement of springs consists of a list of conditions that match the [conditions] for
     * this record, with each [Condition.UNKNOWN] value replaced by a known [Condition]. Such an
     * arrangement fits the criteria if it is also consistent with the list of [brokenGroupSizes]
     * for this record.
     */
    fun countPossibleArrangements(): Long = countRecursive(
        conditionIndex = 0,
        groupSizeIndex = 0,
        currentGroupSize = 0,
        memo = mutableMapOf()
    )

    /**
     * Recursive helper function for [countPossibleArrangements], which memoizes the results of
     * recursive sub-problems.
     *
     * @param conditionIndex Index of the current spring condition being considered in [conditions].
     * @param groupSizeIndex Index of the current group size being considered in [brokenGroupSizes].
     * @param currentGroupSize The number of broken springs seen so far in the current group.
     * @param memo A map from possible argument values to precomputed function results, if any.
     */
    private fun countRecursive(
        conditionIndex: Int,
        groupSizeIndex: Int,
        currentGroupSize: Int,
        memo: MutableMap<List<Int>, Long>
    ): Long {
        val memoArgs = listOf(conditionIndex, groupSizeIndex, currentGroupSize)
        return memo.getOrPut(memoArgs) {
            when {
                // Conditions and group sizes both exhausted: valid if no trailing group
                conditionIndex == conditions.size && groupSizeIndex == brokenGroupSizes.size ->
                    if (currentGroupSize == 0) 1L else 0L

                // Conditions exhausted with group sizes remaining: invalid
                conditionIndex == conditions.size -> 0L

                // Group sizes exhausted with conditions remaining: valid if no more broken springs
                groupSizeIndex == brokenGroupSizes.size ->
                    if (conditions[conditionIndex] == Condition.BROKEN || currentGroupSize != 0) {
                        0L
                    } else {
                        countRecursive(conditionIndex + 1, groupSizeIndex, 0, memo)
                    }

                else -> when (conditions[conditionIndex]) {
                    // Current spring must be broken
                    Condition.BROKEN -> countRecursiveBroken(
                        conditionIndex,
                        groupSizeIndex,
                        currentGroupSize,
                        memo
                    )

                    // Current spring must be operational
                    Condition.OPERATIONAL -> countRecursiveOperational(
                        conditionIndex,
                        groupSizeIndex,
                        currentGroupSize,
                        memo
                    )

                    // Current spring has unknown condition: sum counts for each known condition
                    Condition.UNKNOWN -> {
                        val countIfBroken = countRecursiveBroken(
                            conditionIndex,
                            groupSizeIndex,
                            currentGroupSize,
                            memo
                        )
                        val countIfOperational = countRecursiveOperational(
                            conditionIndex,
                            groupSizeIndex,
                            currentGroupSize,
                            memo
                        )
                        countIfBroken + countIfOperational
                    }
                }
            }
        }
    }

    /**
     * Helper function for [countRecursive] that counts possible suffix arrangements, assuming that
     * the true spring condition for the given [conditionIndex] is [Condition.BROKEN].
     *
     * @param conditionIndex Index of the current spring condition being considered in [conditions].
     * @param groupSizeIndex Index of the current group size being considered in [brokenGroupSizes].
     * @param currentGroupSize The number of broken springs seen so far in the current group.
     * @param memo A map from possible argument values to precomputed function results, if any.
     */
    private fun countRecursiveBroken(
        conditionIndex: Int,
        groupSizeIndex: Int,
        currentGroupSize: Int,
        memo: MutableMap<List<Int>, Long>
    ): Long {
        // Check group size after adding the current spring
        return when (currentGroupSize) {
            // Group size is too large: invalid
            brokenGroupSizes[groupSizeIndex] -> 0L

            // Group size matches expected value
            brokenGroupSizes[groupSizeIndex] - 1 -> when {
                // No more conditions to check: may be valid
                conditionIndex == conditions.lastIndex ->
                    countRecursive(conditionIndex + 1, groupSizeIndex + 1, 0, memo)

                // More conditions to check: may be valid if next spring is operational
                conditions[conditionIndex + 1] != Condition.BROKEN ->
                    countRecursiveOperational(conditionIndex + 1, groupSizeIndex + 1, 0, memo)

                // Next spring is broken: invalid
                else -> 0L
            }

            // Group size is too small: may be valid
            else -> countRecursive(conditionIndex + 1, groupSizeIndex, currentGroupSize + 1, memo)
        }
    }

    /**
     * Helper function for [countRecursive] that counts possible suffix arrangements, assuming that
     * the true spring condition for the given [conditionIndex] is [Condition.OPERATIONAL].
     *
     * @param conditionIndex Index of the current spring condition being considered in [conditions].
     * @param groupSizeIndex Index of the current group size being considered in [brokenGroupSizes].
     * @param currentGroupSize The number of broken springs seen so far in the current group.
     * @param memo A map from possible argument values to precomputed function results, if any.
     */
    private fun countRecursiveOperational(
        conditionIndex: Int,
        groupSizeIndex: Int,
        currentGroupSize: Int,
        memo: MutableMap<List<Int>, Long>
    ): Long {
        return when (currentGroupSize) {
            // No current group: may be valid
            0 -> countRecursive(conditionIndex + 1, groupSizeIndex, currentGroupSize, memo)

            // Group size matches expected value: may be valid
            brokenGroupSizes[groupSizeIndex] ->
                countRecursive(conditionIndex + 1, groupSizeIndex + 1, 0, memo)

            // Group size is too small: invalid
            else -> 0L
        }
    }

    companion object {
        /**
         * A regex used to parse conditions and broken group sizes from a spring record string.
         */
        private val RECORD_REGEX = Regex("""([#.?]+)\s((?:\d+,)*\d+)""")

        /**
         * Returns a [SpringRecord] with [conditions] and [brokenGroupSizes] parsed from the given
         * [string].
         *
         * The [string] must have the format `"$conditions $groupSizes"`, where
         * `conditions` is a sequence of characters corresponding to spring [Condition]s and
         * `groupSizes` is a comma-separated sequence of positive integers representing the sizes of
         * all groups of broken springs.
         *
         * If [foldFactor] is greater than 1, the actual `conditions` and `groupSizes` strings used
         * are "unfolded" from those read from the [string] according to the following rules:
         *
         * - The unfolded `conditions` string consists of [foldFactor] copies of the original string
         *   separated by the `?` character.
         * - The unfolded `groupSizes` string consists of [foldFactor] copies of the original string
         *   separated by the `,` character.
         *
         * @throws IllegalArgumentException If the [string] is formatted incorrectly, or if
         *  [foldFactor] is negative or zero.
         */
        fun fromString(string: String, foldFactor: Int = 1): SpringRecord {
            require(foldFactor > 0) { "Fold factor must be positive: $foldFactor" }

            // Parse the conditions and group sizes strings
            val matchResult = RECORD_REGEX.matchEntire(string)
            require(matchResult != null) { "Malformed spring record string: $string" }
            val (baseConditionsString, baseGroupSizesString) = matchResult.destructured

            // Unfold each of the strings if needed
            val conditionsString: String
            val groupSizesString: String
            if (foldFactor == 1) {
                conditionsString = baseConditionsString
                groupSizesString = baseGroupSizesString
            } else {
                conditionsString = baseConditionsString.repeatWithSeparator(foldFactor, "?")
                groupSizesString = baseGroupSizesString.repeatWithSeparator(foldFactor, ",")
            }

            // Convert strings to lists of the appropriate type
            val conditions = conditionsString.map { Condition.fromChar(it) }
            val groupSizes = groupSizesString.split(",").map { it.toInt() }

            return SpringRecord(conditions, groupSizes)
        }

        /**
         * Returns a string containing the characters of this string repeated [n] times, separated
         * by the given [separator].
         */
        private fun String.repeatWithSeparator(n: Int, separator: String): String =
            List(n) { this }.joinToString(separator = separator)
    }
}
