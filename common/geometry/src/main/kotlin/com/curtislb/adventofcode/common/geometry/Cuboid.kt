package com.curtislb.adventofcode.common.geometry

import com.curtislb.adventofcode.common.range.contains
import com.curtislb.adventofcode.common.range.overlapWith
import com.curtislb.adventofcode.common.range.size
import com.curtislb.adventofcode.common.range.toIterableRange

/**
 * A box consisting of unit cubes that are aligned to a 3D grid.
 *
 * @param xRange The x coordinates of all unit cubes in this cuboid.
 * @param yRange The y coordinates of all unit cubes in this cuboid.
 * @param zRange The z coordinates of all unit cubes in this cuboid.
 */
data class Cuboid(val xRange: IntRange, val yRange: IntRange, val zRange: IntRange) {
    /**
     * The volume (or the number of unit cubes) in this cuboid.
     */
    val volume: Long
        get() = if (isEmpty()) {
            0L
        } else {
            xRange.size.toLong() * yRange.size.toLong() * zRange.size.toLong()
        }

    /**
     * Returns if this cuboid is empty (contains no unit cubes).
     */
    fun isEmpty(): Boolean = xRange.isEmpty() || yRange.isEmpty() || zRange.isEmpty()

    /**
     * Performs the given [action] for each unit cube in this cuboid.
     */
    inline fun forEachVoxel(action: (x: Int, y: Int, z: Int) -> Unit) {
        for (x in xRange) {
            for (y in yRange) {
                for (z in zRange) {
                    action(x, y, z)
                }
            }
        }
    }

    /**
     * Returns a [Cuboid] representing the intersection of this cuboid and [other].
     */
    fun overlapWith(other: Cuboid): Cuboid {
        // Check if either cuboid is empty
        if (isEmpty() || other.isEmpty()) {
            return EMPTY
        }

        // Check if this cuboid is fully contained in other
        if (xRange in other.xRange && yRange in other.yRange && zRange in other.zRange) {
            return this
        }

        // Check if other is fully contained in this cuboid
        if (other.xRange in xRange && other.yRange in yRange && other.zRange in zRange) {
            return other
        }

        // Find coordinate overlaps and check if any is empty
        val xOverlap = xRange.overlapWith(other.xRange)
        val yOverlap = yRange.overlapWith(other.yRange)
        val zOverlap = zRange.overlapWith(other.zRange)
        if (xOverlap.isEmpty() || yOverlap.isEmpty() || zOverlap.isEmpty()) {
            return EMPTY
        }

        // Create a cuboid with the overlapping coordinates
        return Cuboid(
            xRange = xOverlap.toIterableRange(),
            yRange = yOverlap.toIterableRange(),
            zRange = zOverlap.toIterableRange()
        )
    }

    companion object {
        /**
         * An empty [Cuboid], containing no unit cubes.
         */
        val EMPTY = Cuboid(IntRange.EMPTY, IntRange.EMPTY, IntRange.EMPTY)
    }
}
