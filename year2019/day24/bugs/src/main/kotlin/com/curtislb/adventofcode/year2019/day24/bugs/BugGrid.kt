package com.curtislb.adventofcode.year2019.day24.bugs

import java.io.File

/**
 * A fixed-size grid containing bugs that may die off or infest other spaces over time.
 *
 * @param biodiversity A number that uniquely identifies the positions of all bugs in the grid.
 */
inline class BugGrid(val biodiversity: Int) {
    /**
     * Returns the grid that results after waiting one minute for bugs to die off and infest other spaces.
     *
     * The next grid is determined by applying the following rules to each space simultaneously:
     * - A bug dies (becoming an empty space) unless there is exactly one bug adjacent to it.
     * - An empty space becomes infested with a bug if exactly one or two bugs are adjacent to it.
     *
     * If [upperLevel] is non-null, this grid is treated as replacing its center space, with bugs on the exterior of
     * this grid considered adjacent to spaces in [upperLevel] and vice versa.
     *
     * If [lowerLevel] is non-null, it is treated as replacing this grid's center space, with bugs on the exterior of
     * [lowerLevel] considered adjacent to spaces in this grid and vice versa.
     */
    fun next(upperLevel: BugGrid? = null, lowerLevel: BugGrid? = null): BugGrid {
        var newBiodiversity = 0
        for (spaceOffset in 0 until GRID_SPACE_COUNT) {
            // Don't update the center space if lowerLevel is non-null.
            if (spaceOffset == SPACE_OFFSET_CENTER && lowerLevel != null) {
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
     * When non-null, [upperLevel] and [lowerLevel] represent grid levels above and below the current grid,
     * respectively, for the purpose of determining adjacent spaces.
     *
     * @see [next]
     */
    private fun countAdjacentBugs(spaceOffset: Int, upperLevel: BugGrid?, lowerLevel: BugGrid?): Int {
        var count = 0

        // Convert spaceOffset to grid coordinates.
        val rowIndex = spaceOffset / GRID_SIZE
        val colIndex = spaceOffset % GRID_SIZE

        // Count adjacent bugs within the current grid.
        if (rowIndex > 0 && isBugAt(spaceOffset - GRID_SIZE)) {
            count++
        }
        if (rowIndex < GRID_SIZE - 1 && isBugAt(spaceOffset + GRID_SIZE)) {
            count++
        }
        if (colIndex > 0 && isBugAt(spaceOffset - 1)) {
            count++
        }
        if (colIndex < GRID_SIZE - 1 && isBugAt(spaceOffset + 1)) {
            count++
        }

        // Count any adjacent bugs from the outer grid.
        if (upperLevel != null) {
            if (rowIndex == 0 && upperLevel.isBugAt(SPACE_OFFSET_TOP_INTERIOR)) {
                count++
            }
            if (rowIndex == GRID_SIZE - 1 && upperLevel.isBugAt(SPACE_OFFSET_BOTTOM_INTERIOR)) {
                count++
            }
            if (colIndex == 0 && upperLevel.isBugAt(SPACE_OFFSET_LEFT_INTERIOR)) {
                count++
            }
            if (colIndex == GRID_SIZE - 1 && upperLevel.isBugAt(SPACE_OFFSET_RIGHT_INTERIOR)) {
                count++
            }
        }

        // Count any adjacent bugs from the inner grid.
        if (lowerLevel != null) {
            when (spaceOffset) {
                SPACE_OFFSET_TOP_INTERIOR -> count += lowerLevel.countBugs(SPACE_MASK_TOP_EXTERIOR)
                SPACE_OFFSET_BOTTOM_INTERIOR -> count += lowerLevel.countBugs(SPACE_MASK_BOTTOM_EXTERIOR)
                SPACE_OFFSET_LEFT_INTERIOR -> count += lowerLevel.countBugs(SPACE_MASK_LEFT_EXTERIOR)
                SPACE_OFFSET_RIGHT_INTERIOR -> count += lowerLevel.countBugs(SPACE_MASK_RIGHT_EXTERIOR)
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
        private const val GRID_SIZE = 5

        /**
         * The total number of spaces in a grid.
         */
        private const val GRID_SPACE_COUNT = GRID_SIZE * GRID_SIZE

        /**
         * The offset for the center grid space.
         */
        private const val SPACE_OFFSET_CENTER = (GRID_SIZE + 1) * (GRID_SIZE / 2)

        /**
         * The offset for the grid space that is adjacent to all top-row spaces of a lower-level grid.
         */
        private const val SPACE_OFFSET_TOP_INTERIOR = GRID_SIZE * (GRID_SIZE / 2 - 1) + (GRID_SIZE / 2)

        /**
         * The offset for the grid space that is adjacent to all bottom-row spaces of a lower-level grid.
         */
        private const val SPACE_OFFSET_BOTTOM_INTERIOR = SPACE_OFFSET_TOP_INTERIOR + GRID_SIZE * 2

        /**
         * The offset for the grid space that is adjacent to all leftmost-column spaces of a lower-level grid.
         */
        private const val SPACE_OFFSET_LEFT_INTERIOR = GRID_SIZE * (GRID_SIZE / 2) + (GRID_SIZE / 2) - 1

        /**
         * The offset for the grid space that is adjacent to all rightmost-column spaces of a lower-level grid.
         */
        private const val SPACE_OFFSET_RIGHT_INTERIOR = SPACE_OFFSET_LEFT_INTERIOR + 2

        /**
         * A bit mask for selecting spaces in the top row of the grid.
         */
        private const val SPACE_MASK_TOP_EXTERIOR = (1 shl GRID_SIZE) - 1

        /**
         * A bit mask for selecting spaces in the bottom row of the grid.
         */
        private const val SPACE_MASK_BOTTOM_EXTERIOR = SPACE_MASK_TOP_EXTERIOR shl (GRID_SPACE_COUNT - GRID_SIZE)

        /**
         * A bit mask for selecting spaces in the leftmost column of the grid.
         */
        private val SPACE_MASK_LEFT_EXTERIOR = (0 until GRID_SPACE_COUNT step GRID_SIZE).sumOf { 1 shl it }

        /**
         * A bit mask for selecting spaces in the rightmost column of the grid.
         */
        private val SPACE_MASK_RIGHT_EXTERIOR = SPACE_MASK_LEFT_EXTERIOR shl (GRID_SIZE - 1)

        /**
         * Returns a grid matching the layout given by an input [file].
         *
         * Each line of [file] should contain a row of characters, with `'.'` representing an empty space and `'#'`
         * representing a space that contains a bug.
         */
        fun from(file: File): BugGrid {
            var biodiversity = 0
            var spaceFlag = 1
            file.forEachLine { line ->
                line.forEach { char ->
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
