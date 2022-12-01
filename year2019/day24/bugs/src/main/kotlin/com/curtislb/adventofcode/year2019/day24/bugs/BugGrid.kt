package com.curtislb.adventofcode.year2019.day24.bugs

import java.io.File

/**
 * A fixed-size grid containing bugs that may die off or infest other spaces over time.
 *
 * @param biodiversity A number that uniquely identifies the positions of all bugs in the grid.
 */
@JvmInline
value class BugGrid(val biodiversity: Int) {
    /**
     * Returns the grid that results from waiting one minute for bugs in the current grid to die off
     * and/or infest empty spaces.
     *
     * The next grid is determined by applying the following rules to each space simultaneously:
     *
     * - A bug dies (becoming an empty space) unless there is exactly one bug adjacent to it.
     * - An empty space becomes infested with a bug if exactly one or two bugs are adjacent to it.
     *
     * If [upperLevel] is non-null, this grid is treated as replacing its center space, with bugs on
     * the exterior of this grid considered adjacent to spaces in [upperLevel] and vice versa.
     *
     * If [lowerLevel] is non-null, it is treated as replacing this grid's center space, with bugs
     * on the exterior of [lowerLevel] considered adjacent to spaces in this grid and vice versa.
     */
    fun next(upperLevel: BugGrid? = null, lowerLevel: BugGrid? = null): BugGrid {
        var newBiodiversity = 0
        for (spaceOffset in 0 until GRID_SPACES) {
            // Don't update the center space if lowerLevel is non-null.
            if (spaceOffset == OFFSET_CENTER && lowerLevel != null) {
                continue
            }

            // Apply the update rules to each space.
            val spaceFlag = 1 shl spaceOffset
            val bugFlag = biodiversity and spaceFlag
            val adjacentBugCount = countAdjacentBugs(spaceOffset, upperLevel, lowerLevel)
            newBiodiversity = when {
                bugFlag != 0 && adjacentBugCount != 1 -> newBiodiversity and spaceFlag.inv()
                bugFlag == 0 && adjacentBugCount in 1..2 -> newBiodiversity or spaceFlag
                else -> newBiodiversity or bugFlag
            }
        }
        return BugGrid(newBiodiversity)
    }

    /**
     * Returns the number of bugs in the grid that occupy spaces matching [spaceMask].
     *
     * If no [spaceMask] is provided, returns the count of all bugs in the grid.
     */
    fun countBugs(spaceMask: Int = 0.inv()): Int = Integer.bitCount(biodiversity and spaceMask)

    /**
     * Returns the number of bugs adjacent to the space at [spaceOffset] in the grid.
     *
     * When non-null, [upperLevel] and [lowerLevel] represent grid levels above and below the
     * current grid, respectively, for the purpose of determining adjacent spaces.
     *
     * @see [next]
     */
    private fun countAdjacentBugs(
        spaceOffset: Int,
        upperLevel: BugGrid?,
        lowerLevel: BugGrid?
    ): Int {
        var count = 0

        // Convert spaceOffset to grid coordinates.
        val rowIndex = spaceOffset / GRID_SIDE
        val colIndex = spaceOffset % GRID_SIDE

        // Count adjacent bugs within the current grid.
        if (rowIndex > 0 && isBugAt(spaceOffset - GRID_SIDE)) {
            count++
        }
        if (rowIndex < GRID_SIDE - 1 && isBugAt(spaceOffset + GRID_SIDE)) {
            count++
        }
        if (colIndex > 0 && isBugAt(spaceOffset - 1)) {
            count++
        }
        if (colIndex < GRID_SIDE - 1 && isBugAt(spaceOffset + 1)) {
            count++
        }

        // Count any adjacent bugs from the outer grid.
        if (upperLevel != null) {
            if (rowIndex == 0 && upperLevel.isBugAt(OFFSET_TOP_INTERIOR)) {
                count++
            }
            if (rowIndex == GRID_SIDE - 1 && upperLevel.isBugAt(OFFSET_BOTTOM_INTERIOR)) {
                count++
            }
            if (colIndex == 0 && upperLevel.isBugAt(OFFSET_LEFT_INTERIOR)) {
                count++
            }
            if (colIndex == GRID_SIDE - 1 && upperLevel.isBugAt(OFFSET_RIGHT_INTERIOR)) {
                count++
            }
        }

        // Count any adjacent bugs from the inner grid.
        if (lowerLevel != null) {
            when (spaceOffset) {
                OFFSET_TOP_INTERIOR -> count += lowerLevel.countBugs(MASK_TOP_EXTERIOR)
                OFFSET_BOTTOM_INTERIOR -> count += lowerLevel.countBugs(MASK_BOTTOM_EXTERIOR)
                OFFSET_LEFT_INTERIOR -> count += lowerLevel.countBugs(MASK_LEFT_EXTERIOR)
                OFFSET_RIGHT_INTERIOR -> count += lowerLevel.countBugs(MASK_RIGHT_EXTERIOR)
            }
        }

        return count
    }

    /**
     * Returns `true` if there is bug at [spaceOffset] in the grid, or `false` otherwise.
     */
    private fun isBugAt(spaceOffset: Int): Boolean = biodiversity and (1 shl spaceOffset) != 0

    companion object {
        /**
         * A grid containing no bugs.
         */
        val EMPTY = BugGrid(0)

        /**
         * The width and height of a grid, in number of spaces.
         */
        private const val GRID_SIDE = 5

        /**
         * The total number of spaces in a grid.
         */
        private const val GRID_SPACES = GRID_SIDE * GRID_SIDE

        /**
         * A bit mask for selecting spaces in the top row of the grid.
         */
        private const val MASK_TOP_EXTERIOR = (1 shl GRID_SIDE) - 1

        /**
         * A bit mask for selecting spaces in the bottom row of the grid.
         */
        private const val MASK_BOTTOM_EXTERIOR = MASK_TOP_EXTERIOR shl (GRID_SPACES - GRID_SIDE)

        /**
         * A bit mask for selecting spaces in the leftmost column of the grid.
         */
        private val MASK_LEFT_EXTERIOR = (0 until GRID_SPACES step GRID_SIDE).sumOf { 1 shl it }

        /**
         * A bit mask for selecting spaces in the rightmost column of the grid.
         */
        private val MASK_RIGHT_EXTERIOR = MASK_LEFT_EXTERIOR shl (GRID_SIDE - 1)

        /**
         * The offset for the center grid space.
         */
        private const val OFFSET_CENTER = (GRID_SIDE + 1) * (GRID_SIDE / 2)

        /**
         * The offset for the grid space that is adjacent to all top-row spaces of a lower grid.
         */
        private const val OFFSET_TOP_INTERIOR = GRID_SIDE * (GRID_SIDE / 2 - 1) + (GRID_SIDE / 2)

        /**
         * The offset for the grid space that is adjacent to all bottom-row spaces of a lower grid.
         */
        private const val OFFSET_BOTTOM_INTERIOR = OFFSET_TOP_INTERIOR + GRID_SIDE * 2

        /**
         * The offset for the grid space that is adjacent to all leftmost spaces of a lower grid.
         */
        private const val OFFSET_LEFT_INTERIOR = GRID_SIDE * (GRID_SIDE / 2) + (GRID_SIDE / 2) - 1

        /**
         * The offset for the grid space that is adjacent to all rightmost spaces of a lower grid.
         */
        private const val OFFSET_RIGHT_INTERIOR = OFFSET_LEFT_INTERIOR + 2

        /**
         * Returns a grid matching the layout given by an input [file].
         *
         * Each line of [file] should contain a row of characters, with `.` representing an empty
         * space and `#` representing a space that contains a bug.
         */
        fun from(file: File): BugGrid {
            var biodiversity = 0
            var spaceFlag = 1
            file.forEachLine { line ->
                for (char in line.trim()) {
                    if (char == '#') {
                        biodiversity = biodiversity or spaceFlag
                    }
                    spaceFlag = spaceFlag shl 1
                }
            }
            return BugGrid(biodiversity)
        }
    }
}
