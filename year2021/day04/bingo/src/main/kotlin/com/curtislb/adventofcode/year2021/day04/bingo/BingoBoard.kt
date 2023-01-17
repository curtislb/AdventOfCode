package com.curtislb.adventofcode.year2021.day04.bingo

import com.curtislb.adventofcode.common.grid.Grid
import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.grid.addRowWith
import com.curtislb.adventofcode.common.grid.forEachPointValue
import com.curtislb.adventofcode.common.grid.mutableGridOf
import com.curtislb.adventofcode.common.parse.toInts

/**
 * A bingo game board, represented by a grid of numeric spaces that can be marked.
 *
 * @param spaceGrid The grid of bingo spaces that make up the board.
 */
class BingoBoard private constructor(private val spaceGrid: Grid<BingoSpace>) {
    /**
     * A list containing how many spaces have been marked in each row of the board.
     */
    private val rowMarkedCounts: MutableList<Int> = MutableList(spaceGrid.height) { 0 }

    /**
     * A list containing how many spaces have been marked in each column of the board.
     */
    private val columnMarkedCounts: MutableList<Int> = MutableList(spaceGrid.width) { 0 }

    /**
     * The sum of all currently unmarked numbers on the board.
     */
    private var unmarkedSum: Int = 0

    /**
     * A map from each number on the board to its point position.
     */
    private val numberPositionMap: Map<Int, Point> = mutableMapOf<Int, Point>().apply {
        spaceGrid.forEachPointValue { position, space ->
            unmarkedSum += space.number
            require(space.number !in this) { "Duplicate number: ${space.number}" }
            this[space.number] = position
        }
    }

    /**
     * The winning score of this board, or `null` if this is not a winning board.
     */
    var winningScore: Int? = null
        private set

    /**
     * Whether this is a winning board, meaning all spaces in any row or column are marked.
     */
    val isWinning: Boolean get() = winningScore != null

    /**
     * A bingo game board, represented by a grid of numeric spaces that can be marked.
     *
     * @param rowStrings Strings of separated decimal integers, representing each row of the board.
     *
     * @throws IllegalArgumentException If not all [rowStrings] contain the same number of integers.
     */
    constructor(rowStrings: List<String>) : this(
        mutableGridOf<BingoSpace>().apply {
            for (rowString in rowStrings) {
                addRowWith {
                    for (number in rowString.toInts()) {
                        add(BingoSpace(number))
                    }
                }
            }
        }
    )

    /**
     * Marks the given [number] on the board, returning `true` if the number is present and was not
     * previously marked, or `false` otherwise.
     */
    fun mark(number: Int): Boolean {
        val position = numberPositionMap[number]
        val space = position?.let { spaceGrid[it] }
        if (position == null || space == null || space.marked) {
            // Space does not exist or is already marked
            return false
        }

        // Increment the marked counts for this row and column
        val (rowIndex, colIndex) = position.toMatrixCoordinates()
        rowMarkedCounts[rowIndex]++
        columnMarkedCounts[colIndex]++

        // Decrease the sum of unmarked numbers
        unmarkedSum -= number

        // If this is the first bingo for the board, calculate the winning score
        val isWinningRow = rowMarkedCounts[rowIndex] == spaceGrid.width
        val isWinningColumn = columnMarkedCounts[colIndex] == spaceGrid.height
        if (!isWinning && (isWinningRow || isWinningColumn)) {
            winningScore = unmarkedSum * number
        }

        // Mark the space and indicate success
        space.marked = true
        return true
    }

    override fun toString(): String = spaceGrid.toString()
}
