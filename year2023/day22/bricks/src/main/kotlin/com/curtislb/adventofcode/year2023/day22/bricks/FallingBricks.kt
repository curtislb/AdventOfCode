package com.curtislb.adventofcode.year2023.day22.bricks

import com.curtislb.adventofcode.common.collection.sortIndicesBy
import com.curtislb.adventofcode.common.geometry.Cuboid
import com.curtislb.adventofcode.common.range.size
import java.io.File

/**
 * A collection of falling bricks that will settle into one or more rigid stacks, where each brick
 * is supported by the ground (at `z=0`) or all bricks directly below it.
 *
 * @property bricks A list of cuboid bricks in 3D space, where the z-axis of each unit cube
 *  represents its initial distance above the ground.
 *
 * @constructor Creates a new instance of [FallingBricks] with the given list of [bricks].
 *
 * @throws IllegalArgumentException If any brick in [bricks] is empty or not fully above ground.
 */
class FallingBricks(private val bricks: List<Cuboid>) {
    init {
        for (brick in bricks) {
            require(!brick.isEmpty()) { "Brick must be non-empty: $brick" }
            require(brick.zRange.first > 0) { "Brick must be fully above ground (z=0): $brick" }
        }
    }

    /**
     * The list of settled bricks after they've fallen into their final positions.
     */
    private val settledBricks: List<SettledBrick> by lazy {
        // Assign each brick an ID equal to its index
        val settledBricks = List(bricks.size) { SettledBrick(it) }

        // Determine the final settled position of each brick and the bricks above/below it
        val arrayHeight = bricks.maxOf { it.yRange.last } + 1
        val arrayWidth = bricks.maxOf { it.xRange.last } + 1
        val floorHeights = Array(arrayHeight) { IntArray(arrayWidth) { 0 } }
        val topBrickIds = Array(arrayHeight) { IntArray(arrayWidth) { -1 } }
        for (brickId in bricks.sortIndicesBy { it.zRange.first }) {
            val brick = bricks[brickId]

            // Find the maximum "floor" height under the current brick
            var prevFloorHeight = 0
            for (rowIndex in brick.yRange) {
                for (colIndex in brick.xRange) {
                    prevFloorHeight = maxOf(prevFloorHeight, floorHeights[rowIndex][colIndex])
                }
            }

            // Add the brick's height to the "floor" and update the bricks above/below it
            val newFloorHeight = prevFloorHeight + brick.zRange.size()
            for (rowIndex in brick.yRange) {
                for (colIndex in brick.xRange) {
                    val topBrickId = topBrickIds[rowIndex][colIndex]
                    if (topBrickId != -1 && floorHeights[rowIndex][colIndex] == prevFloorHeight) {
                        val prevTopBrick = settledBricks[topBrickId]
                        val newTopBrick = settledBricks[brickId]
                        prevTopBrick.bricksAbove.add(newTopBrick)
                        newTopBrick.bricksBelow.add(prevTopBrick)
                    }
                    floorHeights[rowIndex][colIndex] = newFloorHeight
                    topBrickIds[rowIndex][colIndex] = brickId
                }
            }
        }

        settledBricks
    }

    /**
     * Returns the number of settled bricks that can be safely disintegrated, without causing any
     * bricks above them to fall.
     */
    fun countSafeBricks(): Int = settledBricks.count { it.isSafeToRemove() }

    /**
     * Returns the sum, over all settled bricks, of the number of other bricks that would fall if
     * each brick were disintegrated.
     */
    fun sumSupportedBricks(): Int = settledBricks.sumOf { it.countSupportedBricks() }

    companion object {
        /**
         * Regex used to parse the x-, y-, and z-coordinates of a falling brick.
         */
        private val BRICK_REGEX = Regex("""(\d+),(\d+),(\d+)~(\d+),(\d+),(\d+)""")

        /**
         * Returns a new collection of [FallingBricks], with initial brick positions read from the
         * specified [file].
         *
         * Each line of the file, must have the following format, where `(x1,y1,z1)` and
         * `(x2,y2,z2)` are coordinates of opposite-corner unit cubes contained in the brick:
         *
         * ```
         * $x1,$y1,$z1~$x2,$y2,$z2
         * ```
         */
        fun fromFile(file: File): FallingBricks {
            val bricks = mutableListOf<Cuboid>()

            file.forEachLine { line ->
                // Parse coordinates from the current line
                val matchResult = BRICK_REGEX.matchEntire(line)
                require(matchResult != null) { "Malformed line: $line" }

                // Convert strings to unit coordinates
                val x1 = matchResult.groupValues[1].toInt()
                val y1 = matchResult.groupValues[2].toInt()
                val z1 = matchResult.groupValues[3].toInt()
                val x2 = matchResult.groupValues[4].toInt()
                val y2 = matchResult.groupValues[5].toInt()
                val z2 = matchResult.groupValues[6].toInt()

                // Construct brick with the given coordinates
                val brick = Cuboid(
                    xRange = minOf(x1, x2)..maxOf(x1, x2),
                    yRange = minOf(y1, y2)..maxOf(y1, y2),
                    zRange = minOf(z1, z2)..maxOf(z1, z2)
                )
                bricks.add(brick)
            }

            return FallingBricks(bricks)
        }
    }
}
