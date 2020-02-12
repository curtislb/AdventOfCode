package com.curtislb.adventofcode.common.collection

/**
 * A mutable 3D vector, consisting of [x], [y], and [z]-coordinate values.
 */
data class MutableVector(var x: Int = 0, var y: Int = 0, var z: Int = 0) {
    /**
     * A mutable 3D vector, consisting of [x], [y], and [z]-coordinate values.
     *
     * @param vectorString A string representation of the vector, in the form `<x=$x, y=$y, z=$z>`.
     *
     * @throws IllegalArgumentException If [vectorString] has the wrong format.
     */
    constructor(vectorString: String) : this() {
        val fieldStrings = vectorString.trim(' ', '\t', '\r', '\n', '<', '>').split(',')
        fieldStrings.forEach { fieldString ->
            val (field, value) = fieldString.trim().split('=')
            when (field) {
                "x" -> x = value.toInt()
                "y" -> y = value.toInt()
                "z" -> z = value.toInt()
                else -> throw IllegalArgumentException("Unknown field: $field")
            }
        }
    }

    /**
     * Returns a new vector by summing the corresponding components of this vector and [other].
     */
    operator fun plus(other: MutableVector): MutableVector = MutableVector(x + other.x, y + other.y, z + other.z)

    /**
     * Adds each component of [other] to the corresponding component of this vector.
     */
    fun add(other: MutableVector) {
        x += other.x
        y += other.y
        z += other.z
    }

    /**
     * Returns the sum of [transform] applied to each component of this vector.
     */
    fun sumBy(transform: (component: Int) -> Int): Int = transform(x) + transform(y) + transform(z)

    override fun toString(): String = "<x=$x, y=$y, z=$z>"
}
