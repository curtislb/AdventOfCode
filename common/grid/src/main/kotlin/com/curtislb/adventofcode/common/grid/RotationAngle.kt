package com.curtislb.adventofcode.common.grid

/**
 * A net rotation angle that results from making 0 or more 45-degree turns.
 *
 * @param turnCount The minimum number of 45-degree turns in the direction of rotation that result
 *  in this net angle.
 */
enum class RotationAngle(val turnCount: Int) {
    /**
     * No net angle.
     */
    DEGREES_0(0),

    /**
     * A net angle of 45 degrees in the direction of rotation.
     */
    DEGREES_45(1),

    /**
     * A net angle of 90 degrees in the direction of rotation.
     */
    DEGREES_90(2),

    /**
     * A net angle of 135 degrees in the direction of rotation.
     */
    DEGREES_135(3),

    /**
     * A net angle of 180 degrees.
     */
    DEGREES_180(4),

    /**
     * A net angle of 225 degrees in the direction of rotation.
     */
    DEGREES_225(5),

    /**
     * A net angle of 275 degrees in the direction of rotation.
     */
    DEGREES_270(6),

    /**
     * A net angle of 315 degrees in the direction of rotation.
     */
    DEGREES_315(7);

    /**
     * Returns the net angle given by turning this angle and [other] in the direction of rotation.
     */
    operator fun plus(other: RotationAngle): RotationAngle =
        fromTurnCount(turnCount + other.turnCount)

    /**
     * Returns the net angle given by turning this angle in the direction of rotation, followed by
     * turning the [other] angle opposite the direction of rotation.
     */
    operator fun minus(other: RotationAngle): RotationAngle =
        fromTurnCount(turnCount - other.turnCount)

    /**
     * Returns the net angle given by turning this angle opposite the direction of rotation.
     */
    operator fun unaryMinus(): RotationAngle = fromTurnCount(-turnCount)

    companion object {
        /**
         * Returns the rotation angle corresponding to the given number of [degrees].
         *
         * @throws IllegalArgumentException If no rotation angle matches the number of [degrees].
         */
        fun fromDegrees(degrees: Int): RotationAngle = when (degrees.mod(360)) {
            0 -> DEGREES_0
            45 -> DEGREES_45
            90 -> DEGREES_90
            135 -> DEGREES_135
            180 -> DEGREES_180
            225 -> DEGREES_225
            270 -> DEGREES_270
            315 -> DEGREES_315
            else -> throw IllegalArgumentException("No rotation angle for degrees: $degrees")
        }

        /**
         * Returns the rotation angle corresponding to the given 45-degree [turnCount].
         *
         * @throws IllegalArgumentException If no rotation angle matches the given [turnCount].
         */
        fun fromTurnCount(turnCount: Int): RotationAngle = when (turnCount.mod(8)) {
            0 -> DEGREES_0
            1 -> DEGREES_45
            2 -> DEGREES_90
            3 -> DEGREES_135
            4 -> DEGREES_180
            5 -> DEGREES_225
            6 -> DEGREES_270
            7 -> DEGREES_315
            else -> throw IllegalArgumentException("No rotation angle for turn count: $turnCount")
        }
    }
}
