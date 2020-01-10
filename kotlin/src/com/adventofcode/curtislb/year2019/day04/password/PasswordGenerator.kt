package com.adventofcode.curtislb.year2019.day04.password

/**
 * A generator for producing numeric passwords that satisfy a given criterion.
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
     * Produces a new generator with a given digit appended to the current prefix.
     * @param digit The digit to append to the current prefix.
     * @return A [PasswordGenerator] with the same validity criterion, with [digit] appended to the current prefix.
     */
    abstract fun addDigit(digit: Int): PasswordGenerator

    /**
     * Counts the number of valid passwords with the current prefix, assuming a finite set.
     *
     * @return The number of all valid passwords can be generated from the current prefix, if this number is finite.
     *  Otherwise, the behavior of this function is undefined.
     */
    fun countPasswords(): Int = (if (isValid) 1 else 0) + nextDigits.sumBy { addDigit(it).countPasswords() }

    /**
     * Generates all valid passwords with the current prefix.
     * @return A (possibly infinite) [Sequence] of all valid passwords that can be generated from the current prefix.
     */
    fun generatePasswords(): Sequence<String> = generatePasswordsInternal()

    /**
     * Recursive helper function for [generatePasswords].
     * @param digitStrings A [MutableList] containing [String] digits of the currently constructed password prefix.
     * @return A [Sequence] of all valid passwords that can be generated from the prefix [digitStrings].
     */
    private fun generatePasswordsInternal(digitStrings: MutableList<String> = mutableListOf()): Sequence<String> {
        return sequence {
            if (isValid) {
                yield(digitStrings.joinToString(separator = ""))
            }
            nextDigits.forEach { digit ->
                digitStrings.add(digit.toString())
                yieldAll(addDigit(digit).generatePasswordsInternal(digitStrings))
                digitStrings.removeAt(digitStrings.size - 1)
            }
        }
    }
}
