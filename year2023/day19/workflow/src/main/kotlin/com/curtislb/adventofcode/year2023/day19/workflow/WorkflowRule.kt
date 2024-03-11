package com.curtislb.adventofcode.year2023.day19.workflow

/**
 * A single rule in a sorting process that may match a [MachinePart] and cause it to be sent to
 * a specified workflow.
 *
 * @property condition A predicate that determines whether a part should be sent to [nextWorkflow].
 * @property nextWorkflow The name of the workflow to which a part should be sent if it matches the
 *  given [condition].
 *
 * @constructor Creates a new instance of [WorkflowRule] with the specified [condition] and
 *  [nextWorkflow].
 */
data class WorkflowRule(val condition: Condition, val nextWorkflow: String) {
    override fun toString(): String =
        if (condition == Condition.True) nextWorkflow else "$condition:$nextWorkflow"

    companion object {
        /**
         * A regex used to match and parse a [WorkflowRule] from a string.
         */
        private val RULE_REGEX = Regex("""([xmas])([<>])(\d+):(\w+)""")

        /**
         * Returns a [WorkflowRule] with a condition and next workflow name read from the given
         * [string].
         *
         * The [string] must have one of the following formats:
         *
         * - `$category<$threshold:$nextWorkflow`: Parts with a `category` rating below `threshold`
         *   will be sent to `nextWorkflow`.
         * - `$category>$threshold:$nextWorkflow`: Parts with a `category` rating above `threshold`
         *   will be sent to `nextWorkflow`.
         * - `$nextWorkflow`: All parts will be sent to `nextWorkflow`
         */
        fun fromString(string: String): WorkflowRule {
            val matchResult = RULE_REGEX.matchEntire(string)
            return if (matchResult == null) {
                // If regex doesn't match, treat string as a workflow name
                WorkflowRule(condition = Condition.True, nextWorkflow = string)
            } else {
                val (categoryString, comparisonString, thresholdString, nextWorkflow) =
                    matchResult.destructured

                // Parse and convert values from the rule string
                val category = Category.fromChar(categoryString[0])
                val threshold = thresholdString.toInt()
                val condition = when (comparisonString) {
                    "<" -> Condition.Less(category, threshold)
                    ">" -> Condition.More(category, threshold)
                    else -> error("Invalid comparison string: $comparisonString")
                }

                return WorkflowRule(condition, nextWorkflow)
            }
        }
    }
}
