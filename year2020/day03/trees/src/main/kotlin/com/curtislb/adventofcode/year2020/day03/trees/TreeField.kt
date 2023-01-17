package com.curtislb.adventofcode.year2020.day03.trees

import com.curtislb.adventofcode.common.grid.Grid
import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.geometry.Ray
import com.curtislb.adventofcode.common.grid.toGrid
import com.curtislb.adventofcode.common.number.Fraction

/**
 * A collection of trees arranged in a 2D grid, consisting of a base pattern repeated infinitely to
 * the right.
 *
 * @param treePattern A string representing the base pattern for this tree field. Each line should
 *  contain a row of characters, with each representing an empty grid space (`.`) or a tree (`#`).
 */
class TreeField(treePattern: String) {
    /**
     * A boolean grid representing the base pattern for this tree field. Contains `true` at each
     * tree position.
     */
    private val patternGrid: Grid<Boolean> = treePattern.trim().lines().map { line ->
        line.trim().map { char -> char == '#' }
    }.toGrid()

    /**
     * The height (in grid units) of this tree field.
     */
    val height = patternGrid.height

    /**
     * Checks if there is a tree at the given [position] in the grid.
     */
    fun isTreeAt(position: Point): Boolean {
        val (rowIndex, colIndex) = position.toMatrixCoordinates()
        return patternGrid[rowIndex, colIndex % patternGrid.width]
    }

    /**
     * Returns the number of trees intersected by a line with the given [slope], starting from the
     * top-left position.
     *
     * A [slope] of `null` corresponds to a vertical line (i.e. one with infinite slope).
     *
     * @throws IllegalArgumentException If [slope] is not negative or `null`.
     */
    fun countTreesAlongSlope(slope: Fraction?): Int {
        // Convert slope to a ray originating from the top-left corner.
        require(slope == null || slope.numerator < 0L) { "Slope must be negative or null: $slope" }
        val ray = Ray(Point.ORIGIN, slope, directionParity = slope != null)

        // Count trees at each point intersected by the ray.
        var count = 0
        for (point in ray.points()) {
            // Stop after reaching the bottom of the pattern.
            if (-point.y >= height) {
                break
            }

            // Check for a tree at the current point.
            if (isTreeAt(point)) {
                count++
            }
        }
        return count
    }
}
