package com.curtislb.adventofcode.year2021.day17.probe

import com.curtislb.adventofcode.common.number.triangleNumber
import com.curtislb.adventofcode.common.parse.toLongs
import com.curtislb.adventofcode.common.search.bisect
import com.curtislb.adventofcode.common.vector.longVectorOf
import java.io.File

/**
 * A launcher that shoots a probe from a fixed starting position in order to reach a target area.
 *
 * The probe is launched from the 2D position `(0, 0)` with an initial x-velocity and y-velocity.
 *
 * @param target The target area that the probe must pass through.
 */
class ProbeLauncher(private val target: ProbeTarget) {
    /**
     * The maximum initial y-velocity with which the probe can be launched and still pass through
     * the target area without overshooting it.
     */
    private val maxInitialYVelocity: Long by lazy {
        val minOvershootVelocity = bisect(knownFalse = target.minY - 1L) { initialYVelocity ->
            var yPosition = triangleNumber(initialYVelocity)
            var yVelocity = -1L
            while (yPosition > target.maxY) {
                yPosition += yVelocity
                yVelocity--
            }
            yPosition < target.minY
        } ?: 1L
        minOvershootVelocity - 1L
    }

    /**
     * Returns the maximum y-position that the probe can reach while still passing through the
     * target area.
     */
    fun findMaxYPosition(): Long = triangleNumber(maxInitialYVelocity)

    /**
     * Returns the number of distinct combinations of initial x- and y-velocities with which the
     * probe can be launched and pass through the target area.
     */
    fun countOnTargetVelocities(): Int {
        var count = 0
        for (xVelocity in 0L..target.maxX) {
            for (yVelocity in target.minY..maxInitialYVelocity) {
                if (isOnTarget(xVelocity, yVelocity)) {
                    count++
                }
            }
        }
        return count
    }

    /**
     * Returns if a probe launched with the given [initialXVelocity] and [initialYVelocity] will
     * pass through the target area.
     */
    private fun isOnTarget(initialXVelocity: Long, initialYVelocity: Long): Boolean {
        val position = longVectorOf(0L, 0L)
        val velocity = longVectorOf(initialXVelocity, initialYVelocity)
        while (position.x < target.maxX && position.y > target.minY) {
            position.add(velocity)
            if (position.x in target.xRange && position.y in target.yRange) {
                return true
            }

            velocity.x = if (velocity.x > 0L) velocity.x - 1L else 0L
            velocity.y--
        }

        return false
    }

    companion object {
        /**
         * Returns a [ProbeLauncher] with its target area read from the given [file].
         *
         * The file's contents must match `"target area: x=$minX..$maxX, y=$minY..$maxY"`, where
         * `minX..maxX` is the x-coordinate range for the target area, and `minY..maxY` is the
         * y-coordinate range for the target area.
         */
        fun fromFile(file: File): ProbeLauncher {
            val (minX, maxX, minY, maxY) = file.readText().toLongs()
            val target = ProbeTarget(minX..maxX, minY..maxY)
            return ProbeLauncher(target)
        }
    }
}
