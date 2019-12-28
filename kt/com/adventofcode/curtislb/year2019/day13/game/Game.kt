package com.adventofcode.curtislb.year2019.day13.game

import com.adventofcode.curtislb.common.intcode.Intcode
import com.adventofcode.curtislb.year2019.day13.game.board.Board
import com.adventofcode.curtislb.year2019.day13.game.strategy.Strategy
import java.io.File
import java.math.BigInteger

/**
 * An interactive game, represented by a [Board] and powered by an [Intcode] computer.
 */
class Game(file: File) {
    /**
     * A [Board] representing the current state of the [Game].
     */
    val board: Board = Board()

    /**
     * The [Intcode] computer used to run the [Game].
     */
    private val intcode: Intcode = Intcode(file)

    /**
     * A counter for keeping track of how each [Intcode] output should be interpreted.
     */
    private var outputCounter: Int = 0

    /**
     * The x-coordinate of the tile currently being drawn.
     */
    private var tileX: Int = 0

    /**
     * The y-coordinate of the tile currently being drawn.
     */
    private var tileY: Int = 0

    init {
        intcode.onOutput = {
            when (outputCounter) {
                0 -> tileX = it.toInt()
                1 -> tileY = it.toInt()
                2 -> board[tileX, tileY] = it
            }
            outputCounter = (outputCounter + 1) % 3
        }
    }

    /**
     * Allows the [Game] to run until it either ends or pauses to wait for player input.
     */
    fun runUntilIdle() { intcode.run() }

    /**
     * Resets the [Game] to its starting state, immediately after initialization.
     */
    fun reset() { intcode.reset() }

    /**
     * Plays the [Game] according to a particular [Strategy], after inserting a number of tokens.
     * @param strategy The [Strategy] that will be used to select a move at each step of the [Game].
     * @param tokens The number of tokens to insert before the [Game] is played.
     * @return The player's final score after the game has ended.
     */
    fun play(strategy: Strategy, tokens: Int = 2): BigInteger {
        intcode[0] = tokens.toBigInteger()
        while (!intcode.isDone) {
            intcode.run()
            intcode.sendInput(strategy.nextMove(board))
        }
        return board.score
    }
}
