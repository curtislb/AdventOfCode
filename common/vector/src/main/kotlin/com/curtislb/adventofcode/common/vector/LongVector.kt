package com.curtislb.adventofcode.common.vector

import com.curtislb.adventofcode.common.iteration.nestedLoop
import kotlin.math.sqrt

/**
 * A vector with mutable [Long] components in a fixed dimensional space.
 *
 * @property components The integer component values of the vector.
 *
 * @constructor Creates a new instance of [LongVector] with the given [components].
 */
class LongVector internal constructor(private val components: LongArray) {
    /**
     * The dimensionality of the vector.
     */
    val dimension: Int
        get() = components.size

    /**
     * The x component of the vector, defined as the component value at dimension index 0.
     *
     * @throws IndexOutOfBoundsException If the [dimension] of the vector is 0.
     */
    var x: Long
        get() = this[0]
        set(value) {
            this[0] = value
        }

    /**
     * The y component of the vector, defined as the component value at dimension index 1.
     *
     * @throws IndexOutOfBoundsException If the [dimension] of the vector is less than 2.
     */
    var y: Long
        get() = this[1]
        set(value) {
            this[1] = value
        }

    /**
     * The z component of the vector, defined as the component value at dimension index 2.
     *
     * @throws IndexOutOfBoundsException If the [dimension] of the vector is less than 3.
     */
    var z: Long
        get() = this[2]
        set(value) {
            this[2] = value
        }

    /**
     * Returns the value of the component at the given dimension [index] in the vector.
     */
    operator fun get(index: Int): Long = components[index]

    /**
     * Updates the component at the given dimension [index] in the vector to the given [value].
     */
    operator fun set(index: Int, value: Long) {
        components[index] = value
    }

    /**
     * Returns a new vector, representing the component-wise sum of this vector and [other].
     *
     * @throws IllegalArgumentException If this vector and [other] have different [dimension]s.
     */
    operator fun plus(other: LongVector): LongVector {
        checkSameDimension(other)
        return if (isEmpty()) this else LongVector(components).apply { add(other) }
    }

    /**
     * Returns a new vector, representing the component-wise difference of this vector and [other].
     *
     * @throws IllegalArgumentException If this vector and [other] have different [dimension]s.
     */
    operator fun minus(other: LongVector): LongVector {
        checkSameDimension(other)
        return if (isEmpty()) this else LongVector(components).apply { subtract(other) }
    }

    /**
     * Returns a new vector, where each component is the additive inverse of the corresponding
     * component in this vector.
     */
    operator fun unaryMinus(): LongVector =
        if (isEmpty()) {
            this
        } else {
            val negComponents = LongArray(components.size) { -components[it] }
            LongVector(negComponents)
        }

    /**
     * Returns the dot product of this vector and [other].
     *
     * @throws IllegalArgumentException If this vector and [other] have different [dimension]s.
     */
    infix fun dot(other: LongVector): Long {
        checkSameDimension(other)
        return components.withIndex().sumOf { it.value * other[it.index] }
    }

    /**
     * Returns a new 3D vector, representing the cross product of this vector and [other].
     *
     * @throws IllegalArgumentException If the [dimension] of either vector is not 3.
     */
    infix fun cross(other: LongVector): LongVector {
        require(dimension == 3) { "Left vector dimensionality must be 3: $dimension" }
        require(other.dimension == 3) {
            "Right vector dimensionality must be 3: ${other.dimension}"
        }

        val crossX = (y * other.z) - (z * other.y)
        val crossY = (z * other.x) - (x * other.z)
        val crossZ = (x * other.y) - (y * other.x)
        return longVectorOf(crossX, crossY, crossZ)
    }

    /**
     * Adds each component of [other] to the corresponding component of this vector.
     *
     * @throws IllegalArgumentException If this vector and [other] have different [dimension]s.
     */
    fun add(other: LongVector) {
        checkSameDimension(other)
        other.components.forEachIndexed { index, value -> components[index] += value }
    }

    /**
     * Subtracts each component of [other] from the corresponding component of this vector.
     *
     * @throws IllegalArgumentException If this vector and [other] have different [dimension]s.
     */
    fun subtract(other: LongVector) {
        checkSameDimension(other)
        other.components.forEachIndexed { index, value -> components[index] -= value }
    }

    /**
     * Returns the sum of all components in the vector.
     */
    fun componentSum(): Long = components.sum()

    /**
     * Returns the sum of all values produced by the [transform] function applied to each component
     * of the vector.
     */
    inline fun componentSumOf(transform: (component: Long) -> Long): Long {
        var total = 0L
        for (index in 0 until dimension) {
            total += transform(this[index])
        }
        return total
    }

    /**
     * Returns the length of the vector, as measured from the origin in [dimension]-space.
     */
    fun magnitude(): Double {
        val sumOfSquares = components.sumOf { component -> component.toDouble().let { it * it } }
        return sqrt(sumOfSquares)
    }

    /**
     * Returns all other vectors with the same [dimension] as this vector whose component values
     * differ from this vector by at most 1.
     */
    fun neighbors(): Sequence<LongVector> = sequence {
        nestedLoop(items = listOf(-1L, 0L, 1L), levelCount = dimension) { offsets ->
            if (offsets.any { it != 0L }) {
                val neighbor = copy()
                offsets.forEachIndexed { index, offset -> neighbor[index] += offset }
                yield(neighbor)
            }
            false // Keep iterating
        }
    }

    /**
     * Returns a new vector with the same component values as this vector.
     */
    fun copy(): LongVector = if (isEmpty()) this else LongVector(components.copyOf())

    /**
     * Returns an array whose values correspond to the components of this vector.
     */
    fun toArray(): LongArray = components.copyOf()

    /**
     * Returns an [IntVector] with the same component values as this vector.
     */
    fun toIntVector(): IntVector =
        if (isEmpty()) {
            IntVector.EMPTY
        } else {
            val intComponents = IntArray(dimension) { components[it].toInt() }
            IntVector(intComponents)
        }

    override fun toString(): String = components.joinToString(prefix = "<", postfix = ">")

    override fun equals(other: Any?): Boolean =
        other is LongVector && components.contentEquals(other.components)

    override fun hashCode(): Int = components.contentHashCode()

    /**
     * Checks that this vector and [other] have the same dimensionality.
     *
     * @throws [IllegalArgumentException] If this vector and [other] have different [dimension]s.
     */
    private fun checkSameDimension(other: LongVector) {
        require(dimension == other.dimension) {
            "Vectors must have the same dimensionality: $dimension != ${other.dimension}"
        }
    }

    /**
     * Returns `true` if the [dimension] of this vector is 0.
     */
    private fun isEmpty(): Boolean = components.isEmpty()

    companion object {
        /**
         * The empty vector, which has a dimensionality of 0.
         */
        val EMPTY: LongVector = LongVector(LongArray(0))
    }
}

/**
 * Returns a new [LongVector] with the given [dimension] and all component values initialized to 0.
 */
fun LongVector(dimension: Int): LongVector = when {
    dimension == 0 -> LongVector.EMPTY
    dimension > 0 -> LongVector(LongArray(dimension))
    else -> throw IllegalArgumentException("Vector dimensionality must be non-negative: $dimension")
}

/**
 * Returns a new [LongVector] with the given [dimension], where all component values initialized by
 * calling the specified [init] function.
 */
fun LongVector(dimension: Int, init: (index: Int) -> Long): LongVector = when {
    dimension == 0 -> LongVector.EMPTY
    dimension > 0 -> LongVector(LongArray(dimension, init))
    else -> throw IllegalArgumentException("Vector dimensionality must be non-negative: $dimension")
}

/**
 * Returns a new [LongVector] with the given [components].
 */
fun longVectorOf(vararg components: Long): LongVector =
    if (components.isEmpty()) LongVector.EMPTY else LongVector(components)

/**
 * Returns a new [LongVector] with initial component values that match the values of this array.
 */
fun LongArray.toVector(): LongVector = if (isEmpty()) LongVector.EMPTY else LongVector(copyOf())
