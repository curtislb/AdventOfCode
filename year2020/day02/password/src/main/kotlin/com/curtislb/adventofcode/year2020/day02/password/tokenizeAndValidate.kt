package com.curtislb.adventofcode.year2020.day02.password

/**
 * The regex used to validate the integer range portion of a rule string.
 */
private val DASH_RANGE_REGEX = Regex("""\d+-\d+""")

/**
 * Returns a pair of tokenized string values from a [rule] string of the form `"$num1-$num2 $char"`.
 *
 * @throws IllegalArgumentException If [rule] does not match the expected format.
 */
fun tokenizeAndValidate(rule: String): Pair<String, String> {
    val tokens = rule.trim().split(' ').map { it.trim() }
    require(tokens.size == 2 && DASH_RANGE_REGEX.matches(tokens[0]) && tokens[1].length == 1) {
        "Invalid rule: $rule"
    }
    return Pair(tokens[0], tokens[1])
}
