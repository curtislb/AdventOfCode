package com.curtislb.adventofcode.year2021.day25.cucumber

import com.curtislb.adventofcode.common.io.forEachLineIndexed
import java.io.File

/**
 * A 2D grid containing one or more herds of sea cucumbers that attempt to move in sequence during
 * each step of movement.
 *
 * @param herds Collections of sea cucumbers that move simultaneously. The order of herds in this
 *  list matches the order in which each herd will move during each step.
 */
class SeaCucumberField(private val herds: List<SeaCucumberHerd>) {
    /**
     * Returns the number of steps after which the sea cucumbers in all herds will stop moving.
     */
    fun countStepsUntilNoMovement(): Int {
        var stepCount = 1
        while (true) {
            // Attempt to move all sea cucumber herds in order
            var isMovement = false
            for (herd in herds) {
                isMovement = herd.move(herds) || isMovement
            }

            // Return if no sea cucumbers moved during this step
            if (!isMovement) {
                return stepCount
            }

            stepCount++
        }
    }

    companion object {
        /**
         * Returns a [SeaCucumberField] with the initial positions of sea cucumbers in east- and
         * west-facing herds read from the given [file].
         *
         * The [file] must contain a rectangular grid, where each (non-newline) character is one of
         * the following:
         * - `>` - A sea cucumber in the east herd, which moves one space to the right per step
         * - `v` - A sea cucumber in the south heard, which moves one space down per step
         * - `.` - An empty grid space, containing no sea cucumber
         */
        fun fromFile(file: File): SeaCucumberField {
            val eastHerdPositions = mutableSetOf<Pair<Int, Int>>()
            val southHerdPositions = mutableSetOf<Pair<Int, Int>>()
            var gridHeight = 0
            var gridWidth = 0
            file.forEachLineIndexed { rowIndex, line ->
                // Determine the width and height of the grid
                gridHeight++
                gridWidth = line.length

                // Read the positions of east- and west-facing sea cucumbers
                line.forEachIndexed { colIndex, char ->
                    when (char) {
                        '>' -> eastHerdPositions.add(Pair(rowIndex, colIndex))
                        'v' -> southHerdPositions.add(Pair(rowIndex, colIndex))
                        else -> {}
                    }
                }
            }

            // Create a sea cucumber field, in which the east herd moves before the south herd
            val herds = listOf(
                SeaCucumberHerd(eastHerdPositions, gridHeight, gridWidth, colsPerStep = 1),
                SeaCucumberHerd(southHerdPositions, gridHeight, gridWidth, rowsPerStep = 1)
            )
            return SeaCucumberField(herds)
        }
    }
}
