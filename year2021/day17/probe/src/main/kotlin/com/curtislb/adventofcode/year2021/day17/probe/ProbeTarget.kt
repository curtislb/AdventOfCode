package com.curtislb.adventofcode.year2021.day17.probe

/**
 * Representation of the target area for a [ProbeLauncher].
 *
 * @param xRange The range of x-position values within the target area.
 * @param yRange The range of y-position values within the target area.
 */
data class ProbeTarget(val xRange: LongRange, val yRange: LongRange) {
    /**
     * The minimum x-position value that is within the target area.
     */
    val minX: Long
        get() = xRange.first

    /**
     * The maximum x-position value that is within the target area.
     */
    val maxX: Long
        get() = xRange.last

    /**
     * The minimum y-position value that is within the target area.
     */
    val minY: Long
        get() = yRange.first

    /**
     * The maximum y-position value that is within the target area.
     */
    val maxY: Long
        get() = yRange.last
}
