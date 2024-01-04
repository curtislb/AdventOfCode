package com.curtislb.adventofcode.year2023.day05.almanac

import com.curtislb.adventofcode.common.range.overlap
import com.curtislb.adventofcode.common.range.size

/**
 * A map that provides rules converting values of one category to new values of another category.
 *
 * @property sourceCategory The category of values that can be converted by this map.
 * @property targetCategory The category of new values that are produced by this map.
 * @property mappedRanges A list of pairs, where each pair `(sourceValues, targetValues)` defines a
 *  range of `sourceValues` that are mapped to corresponding `targetValues`.
 *
 * @constructor Creates a new instance of [CategoryMap] with the given [sourceCategory],
 *  [targetCategory], and [mappedRanges].
 */
class CategoryMap(
    val sourceCategory: String,
    val targetCategory: String,
    private val mappedRanges: List<Pair<LongRange, LongRange>>
) {
    init {
        for (mappedRange in mappedRanges) {
            require(mappedRange.first.size() == mappedRange.second.size()) {
                "Mapped ranges must be of equal size: $mappedRanges"
            }
        }
    }

    /**
     * Returns the values that result from converting the given [values] from [sourceCategory] to
     * [targetCategory].
     */
    fun convertValues(values: List<Long>): List<Long> {
        val convertedValues = values.toMutableList()
        convertedValues.forEachIndexed { index, value ->
            for ((sourceRange, targetRange) in mappedRanges) {
                if (value in sourceRange) {
                    convertedValues[index] = value + targetRange.first - sourceRange.first
                    break
                }
            }
        }
        return convertedValues
    }

    /**
     * Returns the value ranges that result from converting the given value [ranges] from
     * [sourceCategory] to [targetCategory].
     */
    fun convertRanges(ranges: Set<LongRange>): Set<LongRange> {
        val convertedRanges = mutableSetOf<LongRange>()
        for (range in ranges) {
            // Search for a mapped range that intersects the source range
            var isMapped = false
            for (mappedRange in mappedRanges) {
                val newRanges = applyRangeMapping(range, mappedRange)
                if (newRanges.size != 1 || newRanges[0] != range) {
                    convertedRanges.addAll(newRanges)
                    isMapped = true
                    break
                }
            }

            // If no intersecting range is found, add the source range as-is
            if (!isMapped) {
                convertedRanges.add(range)
            }
        }
        return convertedRanges
    }

    override fun toString(): String = buildString {
        appendLine("$sourceCategory-to-$targetCategory map:")
        for ((sourceRange, targetRange) in mappedRanges) {
            appendLine("${targetRange.first} ${sourceRange.first} ${sourceRange.size()}")
        }
    }

    /**
     * Returns the value ranges that result from mapping all values in the specified [range] that
     * intersect with the given [mappedRange] to their corresponding mapped values.
     */
    private fun applyRangeMapping(
        range: LongRange,
        mappedRange: Pair<LongRange, LongRange>
    ): List<LongRange> {
        // Find overlap between range and the mapped source range
        val (sourceRange, targetRange) = mappedRange
        val sourceOverlap = range overlap sourceRange
        if (sourceOverlap.isEmpty()) {
            return listOf(range)
        }

        // Create new ranges with overlapping values mapped to their destination
        val targetOverlapStart = targetRange.first + (sourceOverlap.first - sourceRange.first)
        val targetOverlapEnd = targetRange.last - (sourceRange.last - sourceOverlap.last)
        val newRanges = listOf(
            range.first..<sourceOverlap.first,
            (sourceOverlap.last + 1)..range.last,
            targetOverlapStart..targetOverlapEnd
        )

        // Remove empty ranges from the output
        return newRanges.filter { !it.isEmpty() }
    }

    companion object {
        /**
         * A regex used to parse the source and target categories of a category map from its title.
         */
        private val TITLE_REGEX = Regex("""(\w+)-to-(\w+) map:""")

        /**
         * A regex used to parse a single mapped range for a category map.
         */
        private val MAPPING_REGEX = Regex("""\s*(\d+)\s+(\d+)\s+(\d+)\s*""")

        /**
         * Returns a [CategoryMap] with [sourceCategory], [targetCategory], and [mappedRanges] read
         * from the given [lines] of text.
         *
         * The [lines] must have the following format:
         *
         * ```
         * $sourceCategory-to-$targetCategory map:
         * $dst1 $src1 $len1
         * $dst2 $src2 $len2
         * ...
         * $dstN $srcN $lenN
         * ```
         *
         * The resulting [CategoryMap] will have a list of [mappedRanges] equal to
         * `Pair(srcX..<(srcX + lenX), dstX..<(dstX + lenX))` for each `X` in `1..N`.
         *
         * @throws IllegalArgumentException If [lines] are not formatted correctly.
         */
        fun fromLines(lines: List<String>): CategoryMap {
            require(lines.isNotEmpty()) { "Lines list must be non-empty" }

            // Read the source and target category from the first line
            val titleMatchResult = TITLE_REGEX.matchEntire(lines[0])
            require(titleMatchResult != null) { "Malformed category map title: ${lines[0]}" }
            val (sourceCategory, targetCategory) = titleMatchResult.destructured

            // Read all mapped ranges from subsequent lines
            val mappedRanges = mutableListOf<Pair<LongRange, LongRange>>()
            for (index in 1..lines.lastIndex) {
                val mappingMatchResult = MAPPING_REGEX.matchEntire(lines[index])
                require(mappingMatchResult != null) { "Malformed mapping entry: ${lines[index]}" }

                // Convert start + length representation to ranges
                val (targetString, sourceString, sizeString) = mappingMatchResult.destructured
                val sourceStart = sourceString.toLong()
                val targetStart = targetString.toLong()
                val rangeSize = sizeString.toLong()
                val sourceRange = sourceStart..<(sourceStart + rangeSize)
                val targetRange = targetStart..<(targetStart + rangeSize)
                mappedRanges.add(sourceRange to targetRange)
            }

            return CategoryMap(sourceCategory, targetCategory, mappedRanges)
        }
    }
}
