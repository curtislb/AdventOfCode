package com.curtislb.adventofcode.year2019.day24.bugs

import java.io.File

/**
 * An infinite recursive grid in which each level is represented by a [BugGrid].
 *
 * @param file A file containing the layout for the initial grid level. Each line should contain a row of characters,
 *  with `'.'` representing an empty space and `'#'` representing a space that contains a bug.
 */
class RecursiveBugGrid(file: File) {
    /**
     * The layout of level zero in the recursive grid.
     */
    private var baseGrid: BugGrid = BugGrid.from(file)

    /**
     * The layout of each non-empty negative level in the recursive grid, in decreasing depth order.
     */
    private val upperLevels: MutableList<BugGrid> = mutableListOf(BugGrid.EMPTY)

    /**
     * The layout of each non-empty positive level in the recursive grid, in increasing depth order.
     */
    private val lowerLevels: MutableList<BugGrid> = mutableListOf(BugGrid.EMPTY)

    /**
     * Updates the state of the recursive grid to a given number of [minutes] in the future.
     *
     * Each space at each layer of the grid is updated according to the rules given by [BugGrid.next] for each minute.
     */
    fun update(minutes: Int = 1) {
        for (minute in 1..minutes) {
            // Add new upper- and lower-level grids that may become infested with bugs.
            upperLevels.add(BugGrid.EMPTY)
            lowerLevels.add(BugGrid.EMPTY)

            // Apply the update rules to all grid levels.
            var prevLevel: BugGrid? = null
            var currLevel: BugGrid
            var nextLevel: BugGrid?
            val minDepth = -upperLevels.size
            val maxDepth = lowerLevels.size
            for (index in minDepth..maxDepth) {
                currLevel = getLevel(index)
                nextLevel = if (index < maxDepth) getLevel(index + 1) else null
                setLevel(index, currLevel.next(prevLevel, nextLevel))
                prevLevel = currLevel
            }

            // Remove any trailing upper- or lower-level empty grids.
            upperLevels.dropLastWhile { it == BugGrid.EMPTY }
            lowerLevels.dropLastWhile { it == BugGrid.EMPTY }
        }
    }

    /**
     * Returns the total number of bugs in all levels of the recursive grid.
     */
    fun countBugs(): Int {
        return baseGrid.countBugs() + upperLevels.sumBy { it.countBugs() } + lowerLevels.sumBy { it.countBugs() }
    }

    /**
     * Returns the layout of the level at a given [depth] in the recursive grid.
     */
    private fun getLevel(depth: Int): BugGrid = when {
        depth == 0 -> baseGrid
        depth < 0 -> upperLevels[-depth - 1]
        else -> lowerLevels[depth - 1]
    }

    /**
     * Updates the layout of the level at a given [depth] in the recursive grid to match [grid].
     */
    private fun setLevel(depth: Int, grid: BugGrid) {
        when {
            depth == 0 -> baseGrid = grid
            depth < 0 -> upperLevels[-depth - 1] = grid
            else -> lowerLevels[depth - 1] = grid
        }
    }
}
