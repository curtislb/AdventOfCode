package com.adventofcode.curtislb.year2019.day04.password

/**
 * TODO
 */
class NonDecreasingGenerator(prevDigit: Int = 0) : PasswordGenerator() {
    override val isValid: Boolean = true

    override val nextDigits: Set<Int> = (prevDigit..9).toSet()

    override fun addDigit(digit: Int): PasswordGenerator {
        return NonDecreasingGenerator(digit)
    }
}
