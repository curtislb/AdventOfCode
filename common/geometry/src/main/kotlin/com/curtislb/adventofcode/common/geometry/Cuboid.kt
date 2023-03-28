package com.curtislb.adventofcode.common.geometry

import com.curtislb.adventofcode.common.range.contains
import com.curtislb.adventofcode.common.range.overlap
import com.curtislb.adventofcode.common.range.size
import java.util.Objects

/**
 * A box consisting of unit cubes that are aligned to a 3D grid.
 *
 * @property xRange The x-coordinates of all unit cubes in the cuboid.
 * @property yRange The y-coordinates of all unit cubes in the cuboid.
 * @property zRange The z coordinates of all unit cubes in the cuboid.
 *
 * @constructor Creates a new instance of [Cuboid] with the given coordinate ranges: [xRange],
 *  [yRange], and [zRange].
 */
class Cuboid(val xRange: IntRange, val yRange: IntRange, val zRange: IntRange) {
    /**
     * The volume (or the number of unit cubes) in the cuboid.
     */
    val volume: Long
        get() = if (isEmpty()) {
            0L
        } else {
            xRange.size().toLong() * yRange.size().toLong() * zRange.size().toLong()
        }

    /**
     * Returns `true` if the cuboid is empty (contains no unit cubes).
     */
    fun isEmpty(): Boolean = xRange.isEmpty() || yRange.isEmpty() || zRange.isEmpty()

    /**
     * Returns `true` if [other] is fully contained in this cuboid.
     */
    operator fun contains(other: Cuboid): Boolean =
        other.xRange in xRange && other.yRange in yRange && other.zRange in zRange

    /**
     * Returns a cuboid containing all unit cubes that are in both this cuboid and [other].
     *
     * If this cuboid and [other] don't overlap, this function instead returns an empty cuboid.
     */
    infix fun overlap(other: Cuboid): Cuboid {
        // Check if either cuboid is empty
        if (isEmpty() || other.isEmpty()) {
            return EMPTY
        }

        // Check if either cuboid is contained in the other
        if (this in other) {
            return this
        }
        if (other in this) {
            return other
        }

        // Find coordinate overlaps and check if any is empty
        val xOverlap = (xRange overlap other.xRange).also {
            if (it.isEmpty()) {
                return EMPTY
            }
        }
        val yOverlap = (yRange overlap other.yRange).also {
            if (it.isEmpty()) {
                return EMPTY
            }
        }
        val zOverlap = (zRange overlap other.zRange).also {
            if (it.isEmpty()) {
                return EMPTY
            }
        }

        // Create a cuboid with the overlapping coordinates
        return Cuboid(xRange = xOverlap, yRange = yOverlap, zRange = zOverlap)
    }

    /**
     * Performs the given [action] for each unit cube in the cuboid.
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

    override fun toString(): String = "Cuboid(xRange = $xRange, yRange = $yRange, zRange = $zRange)"

    override fun equals(other: Any?): Boolean = when {
        other !is Cuboid -> false
        isEmpty() && other.isEmpty() -> true
        else -> xRange == other.xRange && yRange == other.yRange && zRange == other.zRange
    }

    override fun hashCode(): Int = if (isEmpty()) -1 else Objects.hash(xRange, yRange, zRange)

    companion object {
        /**
         * An empty [Cuboid], containing no unit cubes.
         */
        val EMPTY: Cuboid = Cuboid(IntRange.EMPTY, IntRange.EMPTY, IntRange.EMPTY)
    }
}
