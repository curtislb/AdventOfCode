package com.curtislb.adventofcode.common.collection

import lombok.Generated

open class IntVector(protected open vararg val components: Int) {
    /**
     * The number of components in this vector.
     */
    val size: Int get() = components.size

    /**
     * Returns the component at the given [index] in this vector.
     */
    operator fun get(index: Int): Int = components[index]

    /**
     * Returns a new vector by summing the corresponding components of this vector and [other].
     *
     * @throws IllegalArgumentException If this vector and [other] are not the same size.
     */
    open operator fun plus(other: IntVector): IntVector {
        require(size == other.size) {
            "The sizes of this vector ($size) and other (${other.size}) must match."
        }

        val componentSums = IntArray(size) { this[it] + other[it] }
        return IntVector(*componentSums)
    }

    /**
     * Returns the sum of [transform] applied to each component of this vector.
     */
    @Generated
    inline fun sumOf(transform: (component: Int) -> Int): Int {
        var sum = 0
        for (i in 0 until size) {
            sum += transform(this[i])
        }
        return sum
    }

    /**
     * All other same-[size] vectors whose component values differ from this one by at most 1.
     */
    fun neighbors(): List<IntVector> {
        return mutableListOf<IntVector>().apply {
            (-1..1).toList().forEachNested(this@IntVector.size) { indexedOffsets ->
                val offsets = indexedOffsets.map { (_, offset) -> offset }.toIntArray()
                if (offsets.any { it != 0 }) {
                    add(this@IntVector + IntVector(*offsets))
                }
                false // Don't stop iterating.
            }
        }
    }

    /**
     * Returns a distinct copy of this vector, with the same component values.
     */
    open fun copy(): IntVector = IntVector(*components)

    override fun equals(other: Any?): Boolean {
        return other is IntVector && components.contentEquals(other.components)
    }

    override fun hashCode(): Int = components.contentHashCode()

    override fun toString(): String = "<${components.joinToString()}>"

    /**
     * Returns a mutable copy of this vector.
     */
    fun toMutableIntVector(): MutableIntVector = MutableIntVector(*components)

    companion object {
        /**
         * Returns a vector of the given [size] with all components set to 0.
         */
        fun ofZeros(size: Int): IntVector {
            require(size >= 0) { "Size must be non-negative: $size" }
            return IntVector(*IntArray(size))
        }
    }
}
