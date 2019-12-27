package com.adventofcode.curtislb.year2019.day12.body

/**
 * A mutable 3D vector, consisting of x, y, and z-coordinate values.
 * @param x The x-coordinate component of this [Vector].
 * @param y The y-coordinate component of this [Vector].
 * @param z The z-coordinate component of this [Vector].
 */
data class Vector(var x: Int, var y: Int, var z: Int) {
    /**
     * A mutable 3D vector, consisting of x, y, and z-coordinate values.
     * @param vectorString A [String] representation of the [Vector], in the form `"<x=$x, y=$y, z=$z>"`.
     */
    constructor(vectorString: String) : this(0, 0, 0) {
        val fieldStrings = vectorString.trim(' ', '\t', '\r', '\n', '<', '>').split(',')
        fieldStrings.forEach {
            val (field, value) = it.trim().split('=')
            when (field) {
                "x" -> x = value.toInt()
                "y" -> y = value.toInt()
                "z" -> z = value.toInt()
                else -> throw IllegalArgumentException("Unknown field: $field")
            }
        }
    }

    /**
     * Calculates the sum of a function mapped to each component of this [Vector].
     * @param transform A function to apply to each of the current components of this [Vector].
     * @return The sum of [transform] applied to all components of this [Vector].
     */
    fun sumBy(transform: (Int) -> Int): Int = transform(x) + transform(y) + transform(z)

    operator fun plus(other: Vector): Vector = Vector(x + other.x, y + other.y, z + other.z)

    operator fun plusAssign(other: Vector) {
        x += other.x
        y += other.y
        z += other.z
    }

    override fun toString(): String = "<x=$x, y=$y, z=$z>"
}
