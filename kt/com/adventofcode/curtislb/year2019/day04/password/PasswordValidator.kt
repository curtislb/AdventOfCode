package com.adventofcode.curtislb.year2019.day04.password

/**
 * TODO
 */
interface PasswordValidator {
    /**
     * TODO
     */
    val isValid: Boolean

    /**
     * TODO
     */
    val nextDigits: List<Int>

    /**
     * TODO
     */
    fun addDigit(digit: Int): PasswordValidator
}
