package com.curtislb.adventofcode.year2023.day13.mirrors

import com.curtislb.adventofcode.common.grid.Grid

/**
 * A valley of mirrors, consisting of a grid of characters that may be reflected across either a
 * horizontal or a vertical axis.
 *
 * @property grid A grid of characters that represents the mirror valley.
 *
 * @constructor Creates a new instance of [MirrorValley] with the given character [grid].
 */
class MirrorValley(private val grid: Grid<Char>) {
    /**
     * Returns an integer that represents the horizontal or vertical line of reflection for the
     * mirror valley grid, assuming there is at most one such line:
     *
     * - If there is a horizontal line of reflection, returns the number of rows above that line
     *   multiplied by [rowFactor].
     * - If there is a vertical line of reflection, returns the number of columns to the left of
     *   that line.
     * - Otherwise, returns 0.
     *
     * The number of mismatched character pairs on either side of a line must equal [mismatchCount]
     * for the grid to be considered symmetric across that line.
     *
     * @throws IllegalArgumentException If [mismatchCount] is negative.
     */
    fun summarizeReflectionInfo(rowFactor: Int, mismatchCount: Int = 0): Int {
        val reflectRow = findReflectionRow(mismatchCount)
        return if (reflectRow != 0) rowFactor * reflectRow else findReflectionColumn(mismatchCount)
    }

    /**
     * Returns the number of rows above the first horizontal line of reflection in the grid, or 0 if
     * there is no such line.
     *
     * The number of mismatched character pairs above and below a line must equal [mismatchCount]
     * for the grid to be considered symmetric across that line.
     *
     * @throws IllegalArgumentException If [mismatchCount] is negative.
     */
    private fun findReflectionRow(mismatchCount: Int): Int {
        require(mismatchCount >= 0) { "Mismatch count must be non-negative: $mismatchCount" }

        // Check each possible horizontal line of reflection
        for (reflectIndex in 1..grid.lastRowIndex) {
            // Compare reflected row pairs while tracking mismatches
            var mismatchesSeen = 0
            var aboveRowIndex = reflectIndex - 1
            var belowRowIndex = reflectIndex
            while (
                mismatchesSeen <= mismatchCount &&
                aboveRowIndex >= 0 &&
                belowRowIndex <= grid.lastRowIndex
            ) {
                // Compare characters between the reflected rows
                for (colIndex in grid.columnIndices) {
                    if (grid[aboveRowIndex, colIndex] != grid[belowRowIndex, colIndex]) {
                        mismatchesSeen++

                        // Too many mismatched characters
                        if (mismatchesSeen > mismatchCount) {
                            break
                        }
                    }
                }

                // Check the next row pair (moving outward)
                aboveRowIndex--
                belowRowIndex++
            }

            // Return this line of reflection if valid
            if (mismatchesSeen == mismatchCount) {
                return reflectIndex
            }
        }

        // No horizontal line of reflection found
        return 0
    }

    /**
     * Returns the number of columns to the left of the first vertical line of reflection in the
     * grid, or 0 if there is no such line.
     *
     * The number of mismatched character pairs to the left and right of a line must equal
     * [mismatchCount] for the grid to be considered symmetric across that line.
     *
     * @throws IllegalArgumentException If [mismatchCount] is negative.
     */
    private fun findReflectionColumn(mismatchCount: Int): Int {
        require(mismatchCount >= 0) { "Mismatch count must be non-negative: $mismatchCount" }

        // Check each possible vertical line of reflection
        for (reflectIndex in 1..grid.lastColumnIndex) {
            // Compare reflected column pairs while tracking mismatches
            var mismatchesSeen = 0
            var leftColIndex = reflectIndex - 1
            var rightColIndex = reflectIndex
            while (
                mismatchesSeen <= mismatchCount &&
                leftColIndex >= 0 &&
                rightColIndex <= grid.lastColumnIndex
            ) {
                // Compare characters between the reflected columns
                for (rowIndex in grid.rowIndices) {
                    if (grid[rowIndex, leftColIndex] != grid[rowIndex, rightColIndex]) {
                        mismatchesSeen++

                        // Too many mismatched characters
                        if (mismatchesSeen > mismatchCount) {
                            break
                        }
                    }
                }

                // Check the next row pair (moving outward)
                leftColIndex--
                rightColIndex++
            }

            // Return this line of reflection if valid
            if (mismatchesSeen == mismatchCount) {
                return reflectIndex
            }
        }

        // No vertical line of reflection found
        return 0
    }
}
