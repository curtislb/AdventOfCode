package com.curtislb.adventofcode.year2019.day04.password

/**
 * A generator for producing numeric passwords that satisfy given requirements.
 */
abstract class

PasswordGenerator {
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
    fun generatePasswordSuffixes(): Sequence<String> = sequence {
        val queue = ArrayDeque<Pair<PasswordGenerator, String>>().apply { addLast(Pair(this@PasswordGenerator, "")) }
        while (!queue.isEmpty()) {
            // Check if the current generator matches a valid password.
            val (generator, suffix) = queue.removeFirst()
            if (generator.isValid) {
                yield(suffix)
            }

            // Enqueue generators with next digits appended.
            generator.nextDigits.forEach { digit ->
                queue.addLast(Pair(generator.addDigit(digit), suffix + digit))
            }
        }
    }
}
