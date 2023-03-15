package com.curtislb.adventofcode.common.vector

import com.curtislb.adventofcode.common.iteration.nestedLoop
import kotlin.math.sqrt

/**
 * A vector with mutable [Int] components in a fixed dimensional space.
 *
 * @property components The integer component values of the vector.
 *
 * @constructor Creates a new instance of [IntVector] with the given [components].
 */
class IntVector internal constructor(private val components: IntArray) {
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
    var x: Int
        get() = this[0]
        set(value) {
            this[0] = value
        }

    /**
     * The y component of the vector, defined as the component value at dimension index 1.
     *
     * @throws IndexOutOfBoundsException If the [dimension] of the vector is less than 2.
     */
    var y: Int
        get() = this[1]
        set(value) {
            this[1] = value
        }

    /**
     * The z component of the vector, defined as the component value at dimension index 2.
     *
     * @throws IndexOutOfBoundsException If the [dimension] of the vector is less than 3.
     */
    var z: Int
        get() = this[2]
        set(value) {
            this[2] = value
        }

    /**
     * Returns the value of the component at the given dimension [index] in the vector.
     */
    operator fun get(index: Int): Int = components[index]

    /**
     * Updates the component at the given dimension [index] in the vector to the given [value].
     */
    operator fun set(index: Int, value: Int) {
        components[index] = value
    }

    /**
     * Returns a new vector, representing the component-wise sum of this vector and [other].
     *
     * @throws IllegalArgumentException If this vector and [other] have different [dimension]s.
     */
    operator fun plus(other: IntVector): IntVector {
        checkSameDimension(other)
        return if (isEmpty()) this else IntVector(components).apply { add(other) }
    }

    /**
     * Returns a new vector, representing the component-wise difference of this vector and [other].
     *
     * @throws IllegalArgumentException If this vector and [other] have different [dimension]s.
     */
    operator fun minus(other: IntVector): IntVector {
        checkSameDimension(other)
        return if (isEmpty()) this else IntVector(components).apply { subtract(other) }
    }

    /**
     * Returns a new vector, where each component is the additive inverse of the corresponding
     * component in this vector.
     */
    operator fun unaryMinus(): IntVector =
        if (isEmpty()) {
            this
        } else {
            val negComponents = IntArray(components.size) { -components[it] }
            IntVector(negComponents)
        }

    /**
     * Returns the dot product of this vector and [other].
     *
     * @throws IllegalArgumentException If this vector and [other] have different [dimension]s.
     */
    infix fun dot(other: IntVector): Long {
        checkSameDimension(other)
        return components.withIndex().sumOf { it.value.toLong() * other[it.index].toLong() }
    }

    /**
     * Returns a new 3D vector, representing the cross product of this vector and [other].
     *
     * @throws IllegalArgumentException If the [dimension] of this vector or [other] is not 3.
     */
    infix fun cross(other: IntVector): IntVector {
        require(dimension == 3) { "Left vector dimensionality must be 3: $dimension" }
        require(other.dimension == 3) {
            "Right vector dimensionality must be 3: ${other.dimension}"
        }

        val crossX = (y * other.z) - (z * other.y)
        val crossY = (z * other.x) - (x * other.z)
        val crossZ = (x * other.y) - (y * other.x)
        return intVectorOf(crossX, crossY, crossZ)
    }

    /**
     * Adds each component of [other] to the corresponding component of this vector.
     *
     * @throws IllegalArgumentException If this vector and [other] have different [dimension]s.
     */
    fun add(other: IntVector) {
        checkSameDimension(other)
        other.components.forEachIndexed { index, value -> components[index] += value }
    }

    /**
     * Subtracts each component of [other] from the corresponding component of this vector.
     *
     * @throws IllegalArgumentException If this vector and [other] have different [dimension]s.
     */
    fun subtract(other: IntVector) {
        checkSameDimension(other)
        other.components.forEachIndexed { index, value -> components[index] -= value }
    }

    /**
     * Returns the sum of all components in the vector.
     */
    fun componentSum(): Int = components.sum()

    /**
     * Returns the sum of all values produced by the [transform] function applied to each component
     * of the vector.
     */
    inline fun componentSumOf(transform: (component: Int) -> Int): Int {
        var total = 0
        for (index in 0 until dimension) {
            total += transform(this[index])
        }
        return total
    }

    /**
     * Returns the length of the vector, measured from the origin in [dimension]-dimensional space.
     */
    fun magnitude(): Double {
        val sumOfSquares = components.sumOf { component -> component.toDouble().let { it * it } }
        return sqrt(sumOfSquares)
    }

    /**
     * Returns all other vectors with the same [dimension] as this vector whose component values
     * differ from this vector by at most 1.
     */
    fun neighbors(): List<IntVector> {
        val neighborList = mutableListOf<IntVector>()
        nestedLoop(items = listOf(-1, 0, 1), levelCount = dimension) { offsets ->
            if (offsets.any { it != 0 }) {
                val neighbor = copy()
                offsets.forEachIndexed { index, offset -> neighbor[index] += offset }
                neighborList.add(neighbor)
            }
            false // Keep iterating
        }
        return neighborList
    }

    /**
     * Returns a new vector with the same component values as this vector.
     */
    fun copy(): IntVector = if (isEmpty()) this else IntVector(components.copyOf())

    /**
     * Returns an array whose values correspond to the components of this vector.
     */
    fun toArray(): IntArray = components.copyOf()

    /**
     * Returns a [LongVector] with the same component values as this vector.
     */
    fun toLongVector(): LongVector =
        if (isEmpty()) {
            LongVector.EMPTY
        } else {
            val longComponents = LongArray(dimension) { components[it].toLong() }
            LongVector(longComponents)
        }

    override fun toString(): String = components.joinToString(prefix = "<", postfix = ">")

    override fun equals(other: Any?): Boolean =
        other is IntVector && components.contentEquals(other.components)

    override fun hashCode(): Int = components.contentHashCode()

    /**
     * Throws an [IllegalArgumentException] if this vector and [other] have different [dimension]s.
     */
    private fun checkSameDimension(other: IntVector) {
        require(dimension == other.dimension) {
            "Vectors must have the same dimensionality: $dimension != ${other.dimension}"
        }
    }

    /**
     * Returns `true` if the [dimension] of the vector is 0.
     */
    private fun isEmpty(): Boolean = components.isEmpty()

    companion object {
        /**
         * The empty vector, which has a dimensionality of 0.
         */
        val EMPTY: IntVector = IntVector(IntArray(0))
    }
}

/**
 * Returns an [IntVector] with the given [dimension] and all component values initialized to 0.
 */
fun IntVector(dimension: Int): IntVector = when {
    dimension == 0 -> IntVector.EMPTY
    dimension > 0 -> IntVector(IntArray(dimension))
    else -> throw IllegalArgumentException("Vector dimensionality must be non-negative: $dimension")
}

/**
 * Returns an [IntVector] with the given [dimension] and all component values initialized according
 * to the [init] function.
 */
fun IntVector(dimension: Int, init: (index: Int) -> Int) = when {
    dimension == 0 -> IntVector.EMPTY
    dimension > 0 -> IntVector(IntArray(dimension, init))
    else -> throw IllegalArgumentException("Vector dimensionality must be non-negative: $dimension")
}

/**
 * Returns an [IntVector] with the given [components].
 */
fun intVectorOf(vararg components: Int): IntVector =
    if (components.isEmpty()) IntVector.EMPTY else IntVector(components)

/**
 * Returns an [IntVector] whose components correspond to the values of this array.
 */
fun IntArray.toVector(): IntVector = if (isEmpty()) IntVector.EMPTY else IntVector(copyOf())
