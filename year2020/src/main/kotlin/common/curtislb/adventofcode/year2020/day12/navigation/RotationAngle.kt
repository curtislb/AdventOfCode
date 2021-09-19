package com.curtislb.adventofcode.year2020.day12.navigation

/**
 * An angle that a [Ship] may turn in either direction.
 */
enum class RotationAngle {
    /**
     * An angle that leaves the ship facing the same direction.
     */
    DEGREES_0,

    /**
     * An angle that leaves the ship facing 90 degrees in the direction of rotation.
     */
    DEGREES_90,

    /**
     * An angle that leaves the ship facing the opposite direction.
     */
    DEGREES_180,

    /**
     * An angle that leaves the ship facing 90 degrees opposite the direction of rotation.
     */
    DEGREES_270;

    companion object {
        /**
         * Returns the rotation angle corresponding to the given number of [degrees].
         *
         * @throws IllegalArgumentException If no rotation angle matches the number of [degrees].
         */
        fun fromDegrees(degrees: Int): RotationAngle = when (degrees % 360) {
            0 -> DEGREES_0
            90 -> DEGREES_90
            180 -> DEGREES_180
            270 -> DEGREES_270
            else -> throw IllegalArgumentException("No rotation angle for degrees: $degrees")
        }
    }
}
