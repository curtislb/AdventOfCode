package com.curtislb.adventofcode.year2019.day18.vault.search

/**
 * A collection of keys in the vault, each of which is represented by a character from `'a'` to `'z'`.
 *
 * @param keyFlags An integer whose 26 rightmost bits indicate whether each possible key is in this collection.
 */
@JvmInline
value class KeyCollection(private val keyFlags: Int = 0) {
    /**
     * Returns `true` if [key] is in this collection, or `false` otherwise.
     */
    operator fun contains(key: Char): Boolean = keyFlags or flag(key) == keyFlags

    /**
     * Returns a copy of this collection with [key] included.
     */
    fun withKey(key: Char): KeyCollection = KeyCollection(keyFlags or flag(key))

    companion object {
        /**
         * Returns a [KeyCollection] containing [keys].
         */
        fun from(keys: Iterable<Char>): KeyCollection {
            var keyFlags = 0
            keys.forEach { keyFlags = keyFlags or flag(it) }
            return KeyCollection(keyFlags)
        }

        /**
         * Returns the integer flag corresponding to [key].
         */
        private fun flag(key: Char): Int = 1 shl (key.code - 96)
    }
}
