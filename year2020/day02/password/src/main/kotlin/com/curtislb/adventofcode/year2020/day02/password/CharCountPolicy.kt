package com.curtislb.adventofcode.year2020.day02.password

/**
 * A policy requiring a character to appear a given number of times in a password.
 *
 * Password rules for this policy are of the form `"$minValue-$maxValue $char"`, indicating that the
 * character `char` must appear `minValue..maxValue` times in a valid password.
 */
object CharCountPolicy : PasswordPolicy {
    override fun isValid(rule: String, password: String): Boolean {
        val (countRange, char) = processRule(rule)
        return password.count { it == char } in countRange
    }

    /**
     * Returns a pair of the valid count range and required character from the given password
     * [rule].
     *
     * @throws IllegalArgumentException If [rule] does not match the expected format.
     */
    private fun processRule(rule: String): Pair<IntRange, Char> {
        val (countRangeString, charString) = tokenizeAndValidate(rule)
        val countRange = countRangeString.split('-').map { it.toInt() }.let { it[0]..it[1] }
        val char = charString[0]
        return Pair(countRange, char)
    }
}
