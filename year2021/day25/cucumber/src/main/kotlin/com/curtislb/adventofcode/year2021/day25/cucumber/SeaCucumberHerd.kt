package com.curtislb.adventofcode.year2021.day25.cucumber

/**
 * A collection of sea cucumbers, all of which move at the same time and in the same direction.
 *
 * @param initialPositions The initial `(row, col)` positions of all sea cucumbers in the herd.
 * @param gridHeight The number of rows in the grid in which the herd is located.
 * @param gridWidth The number of columns in the grid in which the herd is located.
 * @param rowsPerStep The number of rows downward sea cucumbers in the herd move per step.
 * @param colsPerStep The number of columns downward sea cucumbers in the herd move per step.
 */
class SeaCucumberHerd(
    initialPositions: Set<Pair<Int, Int>>,
    private val gridHeight: Int,
    private val gridWidth: Int,
    private val rowsPerStep: Int = 0,
    private val colsPerStep: Int = 0
) {
    /**
     * The current `(row, col)` positions of all sea cucumbers in the herd.
     */
    private var positions: Set<Pair<Int, Int>> = initialPositions

    /**
     * Returns `true` if there is a sea cucumber at the given [rowCol] position in the herd.
     */
    operator fun contains(rowCol: Pair<Int, Int>): Boolean = rowCol in positions

    /**
     * Updates the positions of sea cucumbers in the herd after they simultaneously  attempt to move
     * forward by one step, remaining in place if the position to which they would move is occupied
     * by a sea cucumber in any of the given [blockingHerds]. Returns `true` if at least one sea
     * cucumber in the herd moved during this step.
     *
     * A sea cucumber that moves outside the predefined grid range automatically wraps around to the
     * opposite side of the grid and continues moving from that position.
     */
    fun move(blockingHerds: Iterable<SeaCucumberHerd>): Boolean {
        var isMovement = false

        // Try to move all sea cucumbers in the herd
        val newPositions = mutableSetOf<Pair<Int, Int>>()
        for (rowCol in positions) {
            // Find the target position for this sea cucumber
            val (rowIndex, colIndex) = rowCol
            val newRow = (rowIndex + rowsPerStep).mod(gridHeight)
            val newCol = (colIndex + colsPerStep).mod(gridWidth)
            val newRowCol = Pair(newRow, newCol)

            // Only move if the target position isn't blocked
            if (blockingHerds.none { newRowCol in it }) {
                isMovement = true
                newPositions.add(newRowCol)
            } else {
                newPositions.add(rowCol)
            }
        }

        positions = newPositions
        return isMovement
    }
}
