package com.curtislb.adventofcode.year2020.day03.trees

import com.curtislb.adventofcode.common.grid.Direction
import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.math.Fraction

/**
 * A collection of trees arranged in a 2D grid, consisting of a base pattern repeated infinitely to the right.
 *
 * @param patternString A string representing the base pattern for this tree field. Each line should contain a row of
 *  characters, with each representing an empty grid space (`'.'`) or a tree (`'#'`).
 */
class TreeField(patternString: String) {
    /**
     * A boolean grid representing the base pattern for this tree field. Contains `true` at each tree position.
     */
    private val treePattern: List<List<Boolean>> = patternString.trim().lines().map { line ->
        line.trim().map { char -> char == '#' }
    }

    /**
     * The height (in grid units) of this tree field.
     */
    val height = treePattern.size

    /**
     * The width (in grid units) of the base pattern for this tree field.
     */
    private val patternWidth = treePattern.getOrNull(0)?.size ?: 0

    /**
     * Checks if there is a tree at the given [position] in the grid.
     */
    fun isTreeAt(position: Point): Boolean {
        val (rowIndex, colIndex) = position.toMatrixCoordinates()
        return treePattern[rowIndex][colIndex % patternWidth]
    }

    /**
     * Returns the number of trees intersected by a line with the given [slope], starting from the top-left position.
     *
     * A [slope] of `null` corresponds to a vertical line (i.e. one with infinite slope).
     *
     * @throws IllegalArgumentException If [slope] is not negative or `null`.
     */
    fun countTreesAlongSlope(slope: Fraction?): Int {
        // Convert slope to integer x and y step values.
        require(slope == null || slope.numerator < 0L) { "Slope must be negative or null: $slope" }
        val deltaX = slope?.denominator?.toInt() ?: 0
        val deltaY = slope?.numerator?.toInt() ?: -1

        // Count trees at each possible position, incrementing by the given step sizes.
        var count = 0
        var position = Point(deltaX, deltaY)
        while (-position.y < height) {
            if (isTreeAt(position)) {
                count++
            }
            position = position.move(Direction.RIGHT, deltaX).move(Direction.UP, deltaY)
        }
        return count
    }
}
