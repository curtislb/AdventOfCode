package com.curtislb.adventofcode.year2021.day17.probe

import com.curtislb.adventofcode.common.io.readLongs
import com.curtislb.adventofcode.common.search.bisectIndex
import java.io.File

/**
 * TODO
 */
class ProbeLauncher(private val target: ProbeTarget) {
    /**
     * TODO
     */
    private val maxInitialVelocityY: Long by lazy {
        val minOvershootVelocity = bisectIndex(knownFalse = target.minY) { initialVelocity ->
            var position = 0L
            var velocity = initialVelocity
            do {
                position += velocity
                velocity--
            } while (position > target.maxY)
            position < target.minY
        }
        if (minOvershootVelocity != null) minOvershootVelocity - 1L else 0L
    }

//    private fun isOnTarget(initialVelocityX: Long, initialVelocityY: Long): Boolean {
//
//    }

    companion object {
        /**
         * TODO
         */
        fun fromFile(file: File): ProbeLauncher {
            val (minX, maxX, minY, maxY) = file.readLongs()
            val target = ProbeTarget(minX, maxX, minY, maxY)
            return ProbeLauncher(target)
        }
    }
}
