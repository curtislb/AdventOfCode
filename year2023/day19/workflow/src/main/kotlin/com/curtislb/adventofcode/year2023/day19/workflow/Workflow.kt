package com.curtislb.adventofcode.year2023.day19.workflow

/**
 * A single workflow in a [SortingProcess], with a sequence of rules for determining the next
 * workflow to which a [MachinePart] should be sent.
 *
 * @property name A name that uniquely identifies the workflow.
 * @property rules An ordered sequence of rules against which a part is checked.
 *
 * @constructor Creates a new [Workflow] with the given [name] and list of [rules].
 */
data class Workflow(val name: String, val rules: List<WorkflowRule>) {
    override fun toString(): String = "$name{${rules.joinToString(separator = ",")}}"

    companion object {
        /**
         * A regex used to match and parse a [Workflow] from a string.
         */
        private val WORKFLOW_REGEX = Regex("""(\w+)\{([^}]+)}""")

        /**
         * Returns a [Workflow] with its name and rules read from the specified [string].
         *
         * The [string] must have the following format, where each `rule` is formatted according to
         * [WorkflowRule.fromString]: `$name{$rule1,$rule2,...,$ruleN}`
         *
         * @throws IllegalArgumentException If [string] is not formatted correctly.
         */
        fun fromString(string: String): Workflow {
            // Match string against the workflow regex
            val matchResult = WORKFLOW_REGEX.matchEntire(string)
            require(matchResult != null) { "Malformed workflow string: $string" }

            // Parse name and rules from the result
            val (name, rulesCsv) = matchResult.destructured
            val rules = rulesCsv.split(",").map { WorkflowRule.fromString(it) }
            return Workflow(name, rules)
        }
    }
}
