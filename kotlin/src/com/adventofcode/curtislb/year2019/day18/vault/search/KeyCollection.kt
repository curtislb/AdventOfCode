package com.adventofcode.curtislb.year2019.day18.vault.search

/**
 * A collection of keys in the vault, each of which can be represented by a [Char] from `'a'` to `'z'`.
 * @param keyFlags An [Int] whose 26 rightmost bits represent whether each key is part of this [KeyCollection].
 */
inline class KeyCollection(private val keyFlags: Int = 0) {
    /**
     * Checks if a given key is part of this [KeyCollection].
     * @param key A [Char] from `'a'` to `'z'` representing a key.
     * @return `true` if [key] is part of this [KeyCollection], or `false` otherwise.
     */
    operator fun contains(key: Char): Boolean = keyFlags or getFlag(key) == keyFlags

    /**
     * Produces a copy of this [KeyCollection] with a given key added to it.
     * @param key A [Char] from `'a'` to `'z'` representing a key.
     * @return A [KeyCollection] containing [key] along with all keys from this [KeyCollection].
     */
    fun withKey(key: Char): KeyCollection = KeyCollection(keyFlags or getFlag(key))

    companion object {
        /**
         * Creates a [KeyCollection] from an [Iterable] of [Char] keys.
         * @param keys An [Iterable] of [Char] values from `'a'` to `'z'`, each representing a key.
         * @return A [KeyCollection] containing all keys in [keys].
         */
        fun from(keys: Iterable<Char>): KeyCollection {
            var keyFlags = 0
            keys.forEach { keyFlags = keyFlags or getFlag(it) }
            return KeyCollection(keyFlags)
        }

        /**
         * Produces the [Int] flag for a given [Char] key.
         * @param key A [Char] from `'a'` to `'z'` representing a key.
         * @return An [Int] with a single 1 bit, corresponding to the flag for [key] within a [KeyCollection].
         */
        private fun getFlag(key: Char): Int = 1 shl (key.toInt() - 96)
    }
}
