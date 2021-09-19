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
 * @param initialize A function used to initialize the [Intcode] program before the game is played.
 */
class Game(file: File, private val initialize: (intcode: Intcode) -> Unit = {}) {
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
    private val intcode: Intcode = Intcode(file)

    init {
        initialize(intcode)

        var outputCounter = 0
        var tileX = 0
        var tileY = 0
        intcode.onOutput = { output ->
            when (outputCounter) {
                0 -> tileX = output.toInt()
                1 -> tileY = -output.toInt()
                2 -> {
                    // Update the player's score or a board tile, depending on the output.
                    val position = Point(tileX, tileY)
                    if (position == SCORE_POSITION) {
                        score = output
                    } else {
                        board[position] = Tile.from(output)
                    }
                }
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
        initialize(intcode)
        intcode.run()
    }

    override fun toString(): String = "Score: $score\n$board"

    companion object {
        /**
         * A meta-positional argument indicating that the player's score should be updated.
         */
        private val SCORE_POSITION = Point(-1, 0)
    }
}
