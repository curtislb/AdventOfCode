package com.curtislb.adventofcode.year2019.day04.password

import com.curtislb.adventofcode.common.collection.removeLast

/**
 * A generator for producing numeric passwords that satisfy given requirements.
 */
abstract class PasswordGenerator {
    /**
     * Whether the currently constructed prefix is a valid password.
     */
    abstract val isValid: Boolean

    /**
     * All digits that, when appended to the current prefix, may produce future valid passwords.
     */
    abstract val nextDigits: Set<Int>

    /**
     * Returns a new [PasswordGenerator] with [digit] appended to the current prefix.
     */
    abstract fun addDigit(digit: Int): PasswordGenerator

    /**
     * Returns the number of valid passwords with the current prefix.
     *
     * The number of such passwords must be finite; otherwise, this method will not return.
     */
    fun countPasswords(): Int = (if (isValid) 1 else 0) + nextDigits.sumBy { addDigit(it).countPasswords() }

    /**
     * Returns a (possibly infinite) sequence of all valid passwords with the current prefix.
     */
    fun generatePasswords(): Sequence<String> = generatePasswordsInternal()

    /**
     * Recursive helper method for [generatePasswords].
     */
    private fun generatePasswordsInternal(digitStrings: MutableList<String> = mutableListOf()): Sequence<String> {
        return sequence {
            if (isValid) {
                yield(digitStrings.joinToString(separator = ""))
            }
            nextDigits.forEach { digit ->
                digitStrings.add(digit.toString())
                yieldAll(addDigit(digit).generatePasswordsInternal(digitStrings))
                digitStrings.removeLast()
            }
        }
    }
}
