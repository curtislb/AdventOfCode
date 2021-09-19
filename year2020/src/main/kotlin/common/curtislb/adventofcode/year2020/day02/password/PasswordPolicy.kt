package com.curtislb.adventofcode.year2020.day02.password

/**
 * A policy for validating passwords against associated rule strings.
 */
interface PasswordPolicy {
    /**
     * Checks if [password] is valid according to the given [rule] string.
     */
    fun isValid(rule: String, password: String): Boolean

    /**
     * Returns the number of password [entries] that are valid, where each entry is of the form `"$rule: $password"`.
     *
     * @throws IllegalArgumentException If [entries] contains one or more invalid password entries.
     */
    fun countValidPasswords(entries: List<String>): Int {
        return entries.sumBy { entry ->
            val tokens = entry.split(':').map { it.trim() }
            require(tokens.size == 2) { "Password entry is invalid: $entry" }
            val (rule, password) = tokens
            if (isValid(rule, password)) 1 else 0
        }
    }
}
