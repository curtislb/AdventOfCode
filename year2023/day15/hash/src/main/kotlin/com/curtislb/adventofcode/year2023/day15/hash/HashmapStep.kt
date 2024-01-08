package com.curtislb.adventofcode.year2023.day15.hash

/**
 * A single step in the full HASHMAP process for arranging a sequence of labeled lenses.
 *
 * @property label The label of the lens on which the step operates.
 * @property operation The operation that the step performs on the lens.
 */
data class HashmapStep(val label: String, val operation: Operation) {
    companion object {
        /**
         * A regex used to parse the lens label and operation info from a HASHMAP step string.
         */
        private val STEP_REGEX = Regex("""(\w+)([\-=])(\d*)""")

        /**
         * Returns a [HashmapStep] with a lens label and operation info read from the given string.
         *
         * The [string] must have one of the following formats:
         *
         * - `"$label-"`: Perform the [Operation.Dash] operation on the lens with the given `label`.
         * - `"$label=$focalLength"`: Perform the [Operation.Equals] operation with the specified
         *   `focalLength` on the lens with the given `label`.
         *
         * @throws IllegalArgumentException If [string] is formatted incorrectly.
         */
        fun fromString(string: String): HashmapStep {
            val matchResult = STEP_REGEX.matchEntire(string)
            require(matchResult != null) { "Malformed HASHMAP step string: $string" }

            // Parse label and operation info from the string
            val (label, operationString, operationArgument) = matchResult.destructured
            val operation = when (operationString) {
                "-" -> Operation.Dash
                "=" -> Operation.Equals(operationArgument.toInt())
                else -> error("Invalid operation string: $operationString")
            }

            return HashmapStep(label, operation)
        }
    }
}
