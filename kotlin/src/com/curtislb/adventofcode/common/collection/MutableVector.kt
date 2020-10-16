package com.curtislb.adventofcode.common.collection

/**
 * A mutable vector with a fixed number of integer [components].
 *
 * @param components The current component values of this vector.
 */
class MutableVector(private vararg val components: Int) {
    /**
     * The number of components in this vector.
     */
    val size: Int = components.size

    /**
     * Returns the component at the given [index] in this vector.
     */
    operator fun get(index: Int): Int = components[index]

    /**
     * Updates the component at [index] to the given [value].
     */
    operator fun set(index: Int, value: Int) {
        components[index] = value
    }

    /**
     * Returns a new vector by summing the corresponding components of this vector and [other].
     *
     * @throws IllegalArgumentException If this vector and [other] are not the same size.
     */
    operator fun plus(other: MutableVector): MutableVector {
        require(size == other.size) { "The sizes of this vector ($size) and other (${other.size}) must match." }
        val componentSums = IntArray(size) { this[it] + other[it] }
        return MutableVector(*componentSums)
    }

    /**
     * Adds each component of [other] to the corresponding component of this vector.
     *
     * @throws IllegalArgumentException If this vector and [other] are not the same size.
     */
    fun add(other: MutableVector) {
        require(size == other.size) { "The sizes of this vector ($size) and other (${other.size}) must match." }
        for (i in 0 until size) {
            this[i] += other[i]
        }
    }

    /**
     * Returns a distinct copy of this vector, with the same component values.
     */
    fun copy(): MutableVector = MutableVector(*components)

    /**
     * Returns the sum of [transform] applied to each component of this vector.
     */
    inline fun sumBy(transform: (component: Int) -> Int): Int {
        var sum = 0
        for (i in 0 until size) {
            sum += transform(this[i])
        }
        return sum
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
            this[i] = newComponents[i]
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
            this[index] = value
        }
    }

    override fun equals(other: Any?): Boolean = other is MutableVector && components.contentEquals(other.components)

    override fun hashCode(): Int = components.contentHashCode()

    override fun toString(): String = "<${components.joinToString()}>"

    companion object {
        /**
         * Returns a vector of the given [size] with all components set to 0.
         */
        fun ofZeros(size: Int): MutableVector {
            require(size >= 0) { "Size must be non-negative: $size" }
            return MutableVector(*IntArray(size))
        }
    }
}
