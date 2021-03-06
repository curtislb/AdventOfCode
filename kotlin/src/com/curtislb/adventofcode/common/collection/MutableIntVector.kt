package com.curtislb.adventofcode.common.collection

/**
 * A mutable vector with a fixed number of integer [components].
 *
 * @param components The current component values of this vector.
 */
class MutableIntVector(override vararg val components: Int) : IntVector(*components) {
    /**
     * Updates the component at [index] to the given [value].
     */
    operator fun set(index: Int, value: Int) {
        components[index] = value
    }

    /**
     * Updates all components of this vector to match [newComponents].
     *
     * @throws IllegalArgumentException If this vector and [newComponents] are not the same size.
     */
    fun update(vararg newComponents: Int) {
        require(size == newComponents.size) {
            "The sizes of this vector ($size) and the new components (${newComponents.size}) must match."
        }
        for (i in 0 until size) {
            components[i] = newComponents[i]
        }
    }

    /**
     * Updates the specified `(index, value)` component pairs of this vector to those given by [componentValues].
     *
     * @throws IllegalArgumentException If any key of [componentValues] is not a valid component index for this vector.
     */
    fun update(componentValues: Map<Int, Int>) {
        componentValues.forEach { (index, value) ->
            require(index in 0 until size) { "Each index must be in the range 0..${size - 1}: $index" }
            components[index] = value
        }
    }

    /**
     * Adds each component of [other] to the corresponding component of this vector.
     *
     * @throws IllegalArgumentException If this vector and [other] are not the same size.
     */
    fun add(other: MutableIntVector) {
        require(size == other.size) { "The sizes of this vector ($size) and other (${other.size}) must match." }
        for (i in 0 until size) {
            components[i] += other[i]
        }
    }

    /**
     * Returns a new vector by summing the corresponding components of this vector and [other].
     *
     * @throws IllegalArgumentException If this vector and [other] are not the same size.
     */
    operator fun plus(other: MutableIntVector): MutableIntVector {
        require(size == other.size) { "The sizes of this vector ($size) and other (${other.size}) must match." }
        val componentSums = IntArray(size) { this[it] + other[it] }
        return MutableIntVector(*componentSums)
    }

    /**
     * Returns a distinct copy of this vector, with the same component values.
     */
    override fun copy(): MutableIntVector = MutableIntVector(*components.clone())

    /**
     * TODO
     */
    fun toVector(): IntVector = copy()

    companion object {
        /**
         * Returns a vector of the given [size] with all components set to 0.
         */
        fun ofZeros(size: Int): MutableIntVector {
            require(size >= 0) { "Size must be non-negative: $size" }
            val components = IntArray(size) { 0 }
            return MutableIntVector(*components)
        }
    }
}
