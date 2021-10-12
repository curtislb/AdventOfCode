package com.curtislb.adventofcode.year2020.day02.password

/**
 * A policy requiring a character to appear in exactly one of two positions in a password.
 *
 * Password rules for this policy are of the form `"$positionA-$positionB $char"`, indicating that
 * the character `char` must appear at either `positionA` or `positionB` (indexed from 1), but *not*
 * both in a valid password.
 */
object CharPositionPolicy : PasswordPolicy {
    override fun isValid(rule: String, password: String): Boolean {
        val (positions, char) = processRule(rule)
        return (password[positions.first - 1] == char) != (password[positions.second - 1] == char)
    }

    /**
     * Returns a pair of the valid positions and required character from the given password [rule].
     *
     * @throws IllegalArgumentException If [rule] does not match the expected format.
     */
    private fun processRule(rule: String): Pair<Pair<Int, Int>, Char> {
        val (positionsString, charString) = tokenizeAndValidate(rule)
        val positions = positionsString.split('-').map { it.toInt() }.let { Pair(it[0], it[1]) }
        val char = charString[0]
        return Pair(positions, char)
    }
}
