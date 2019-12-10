package com.adventofcode.curtislb.year2019.day04.password

/**
 * TODO
 */
abstract class PasswordGenerator {
    /**
     * TODO
     */
    abstract val isValid: Boolean

    /**
     * TODO
     */
    abstract val nextDigits: Set<Int>

    /**
     * TODO
     */
    abstract fun addDigit(digit: Int): PasswordGenerator

    /**
     * TODO
     */
    fun countPasswords(): Int = (if (isValid) 1 else 0) + nextDigits.sumBy { addDigit(it).countPasswords() }
}
