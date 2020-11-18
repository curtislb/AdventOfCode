package com.curtislb.adventofcode.year2019.day13.game

import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.intcode.Intcode
import com.curtislb.adventofcode.year2019.day13.game.strategy.Strategy
import java.io.File
import java.math.BigInteger

/**
 * An interactive game, represented by a [Board] and powered by an [Intcode] computer.
 *
 * @param file A file containing the [Intcode] program that controls the game.
 * @param init A function used to initialize the [Intcode] program before the game is played.
 */
class Game(file: File, private val init: (intcode: Intcode) -> Unit = {}) {
    /**
     * The board on which this game is being played.
     */
    private val board: Board = Board()

    /**
     * The player's current score.
     */
    private var score: BigInteger = BigInteger.ZERO

    /**
     * The [Intcode] program that controls the game.
     */
    private val intcode: Intcode

    init {
        var outputCounter = 0
        var tileX = 0
        var tileY = 0
        intcode = Intcode(file)
        init(intcode)
        intcode.onOutput = { output ->
            when (outputCounter) {
                0 -> tileX = output.toInt()
                1 -> tileY = -output.toInt()
                2 -> update(Point(tileX, tileY), output)
            }
            outputCounter = (outputCounter + 1) % 3
        }
        intcode.run()
    }

    /**
     * Returns the positions of all tiles of the given [type] on the game board.
     */
    fun findAllTiles(type: Tile): List<Point> = board.findAll(type)

    /**
     * Plays the game according to a given [strategy] and returns the player's final score.
     */
    fun play(strategy: Strategy): BigInteger {
        while (!intcode.isDone) {
            intcode.sendInput(strategy.nextMove(board))
            intcode.run()
        }
        return score
    }

    /**
     * Restores the game to its starting state, immediately after initialization.
     */
    fun reset() {
        intcode.resetState()
        init(intcode)
        intcode.run()
    }

    override fun toString(): String = "Score: $score\n$board"

    /**
     * Updates the tile at a given [position] on the board, or the player's score, to match [value].
     *
     * If [SCORE_POSITION] is given as an argument for [position], the player's score is updated to [value]. Any other
     * [position] is interpreted as a point on the board, and the tile at that point is changed to the [Tile] type
     * corresponding to [value].
     */
    private fun update(position: Point, value: BigInteger) {
        if (position == SCORE_POSITION) {
            score = value
        } else {
            board[position] = Tile.from(value)
        }
    }

    companion object {
        /**
         * A meta-positional argument indicating that the player's score should be updated.
         */
        private val SCORE_POSITION = Point(-1, 0)
    }
}
