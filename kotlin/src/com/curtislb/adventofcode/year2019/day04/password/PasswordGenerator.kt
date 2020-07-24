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
     * Returns a (possibly infinite) sequence of all suffixes that form valid passwords with the current prefix.
     *
     * For a generator whose current prefix is empty, this is equivalent to generating all valid passwords.
     */
    fun generatePasswordSuffixes(): Sequence<String> = generatePasswordSuffixesInternal(mutableListOf())

    /**
     * Recursive helper method for [generatePasswordSuffixes].
     */
    private fun generatePasswordSuffixesInternal(digitStrings: MutableList<String>): Sequence<String> = sequence {
        if (isValid) {
            yield(digitStrings.joinToString(separator = ""))
        }
        nextDigits.forEach { digit ->
            digitStrings.add(digit.toString())
            yieldAll(addDigit(digit).generatePasswordSuffixesInternal(digitStrings))
            digitStrings.removeLast()
        }
    }
}
