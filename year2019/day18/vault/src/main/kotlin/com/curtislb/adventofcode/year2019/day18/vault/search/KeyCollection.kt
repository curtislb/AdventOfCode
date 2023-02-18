package com.curtislb.adventofcode.year2019.day18.vault.search

/**
 * A collection of keys in the vault, each of which is represented by a character from `a` to `z`.
 *
 * @param keyFlags An integer whose 26 rightmost bits indicate whether each possible key is in this
 *  collection.
 */
@JvmInline
value class KeyCollection(private val keyFlags: Int = 0) {
    /**
     * Returns `true` if [key] is in this collection, or `false` otherwise.
     */
    operator fun contains(key: Char): Boolean = keyFlags or key.toFlag() == keyFlags

    /**
     * Returns a copy of this collection with [key] included.
     */
    fun withKey(key: Char): KeyCollection = KeyCollection(keyFlags or key.toFlag())

    companion object {
        /**
         * The minimum character code for any key.
         */
        private const val MIN_KEY_CODE = 97

        /**
         * Returns a [KeyCollection] containing the given [keys].
         */
        fun from(keys: Iterable<Char>): KeyCollection {
            var keyFlags = 0
            for (key in keys) {
                keyFlags = keyFlags or key.toFlag()
            }
            return KeyCollection(keyFlags)
        }

        /**
         * Returns the integer flag corresponding to this key [Char].
         */
        private fun Char.toFlag(): Int = 1 shl (code - MIN_KEY_CODE)
    }
}
