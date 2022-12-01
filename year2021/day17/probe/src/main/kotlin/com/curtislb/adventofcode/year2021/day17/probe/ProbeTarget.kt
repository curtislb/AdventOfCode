package com.curtislb.adventofcode.year2021.day17.probe

/**
 * TODO
 */
data class ProbeTarget(val minX: Long, val maxX: Long, val minY: Long, val maxY: Long) {
    /**
     * TODO
     */
    val xRange: LongRange = minX..maxX

    /**
     * TODO
     */
    val yRange: LongRange = minY..maxY
}
