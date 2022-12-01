package com.curtislb.adventofcode.year2019.day12.body

import com.curtislb.adventofcode.common.collection.addVector
import java.util.Objects
import kotlin.math.abs

/**
 * A celestial body, with a [position] and [velocity] in 3D space.
 *
 * @param initialPosition The initial 3D position vector of this body.
 * @param initialVelocity The initial 3D velocity vector of this body.
 */
class Body(
    initialPosition: Triple<Int, Int, Int> = DEFAULT_VECTOR,
    initialVelocity: Triple<Int, Int, Int> = DEFAULT_VECTOR
) {
    /**
     * TODO
     */
    private val _position: IntArray = initialPosition.toList().toIntArray()

    /**
     * TODO
     */
    val position: Triple<Int, Int, Int>
        get() = Triple(_position[0], _position[1], _position[2])

    /**
     * TODO
     */
    private val _velocity: IntArray = initialVelocity.toList().toIntArray()

    /**
     * TODO
     */
    val velocity: Triple<Int, Int, Int>
        get() = Triple(_velocity[0], _velocity[1], _velocity[2])

    /**
     * The current total energy (potential and kinetic) of this body.
     */
    val totalEnergy: Int get() = _position.sumOf { abs(it) } * _velocity.sumOf { abs(it) }

    /**
     * A celestial body, with a [position] and [velocity] in 3D space.
     *
     * @param positionString A string representation of the position for this body, in the form
     *  `<x=${position.first}, y=${position.second}, z=${position.third}>`.
     *
     * @throws IllegalArgumentException If [positionString] has the wrong format.
     */
    constructor(positionString: String) : this() {
        val componentStrings = positionString.trim(' ', '\t', '\r', '\n', '<', '>').split(',')
        require(componentStrings.size == DIMENSION_COUNT) {
            "Incorrect number of components: ${componentStrings.size} != $DIMENSION_COUNT"
        }

        for (componentString in componentStrings) {
            val (name, value) = componentString.trim().split('=')
            when (name) {
                "x" -> _position[0] = value.toInt()
                "y" -> _position[1] = value.toInt()
                "z" -> _position[2] = value.toInt()
                else -> throw IllegalArgumentException("Unknown component name: $name")
            }
        }
    }

    /**
     * Updates the velocity of this body due to gravity from [other].
     */
    fun applyGravity(other: Body) {
        for (i in 0 until DIMENSION_COUNT) {
            if (_position[i] != other._position[i]) {
                _velocity[i] += if (_position[i] < other._position[i]) 1 else -1
            }
        }
    }

    /**
     * Updates the [position] of this body based on its current velocity.
     */
    fun applyVelocity() {
        _position.addVector(_velocity)
    }

    /**
     * Returns a deep copy of this body, with the same current position and velocity.
     */
    fun copy(): Body = Body(position, velocity)

    override fun equals(other: Any?): Boolean {
        return other is Body && position == other.position && velocity == other.velocity
    }

    override fun hashCode(): Int = Objects.hash(position, velocity)

    override fun toString(): String = "pos=${position.toXyzString()}, vel=${velocity.toXyzString()}"

    companion object {
        /**
         * The number of spatial dimensions for the position and velocity of a body.
         */
        private const val DIMENSION_COUNT = 3

        /**
         * TODO
         */
        private val DEFAULT_VECTOR = Triple(0, 0, 0)

        /**
         * Returns a string representation of this 3D vector, in the form `<x=$x, y=$y, z=$z>`.
         *
         * @throws IllegalArgumentException If this vector has the wrong number of components.
         */
        fun Triple<Int, Int, Int>.toXyzString(): String {
            return "<x=${first}, y=${second}, z=${third}>"
        }
    }
}
