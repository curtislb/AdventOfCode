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
 */
class Game(file: File) {
    /**
     * The board on which this game is being played.
     */
    val board: Board = Board()

    /**
     * The [Intcode] program that controls the game.
     */
    private val intcode: Intcode = Intcode(file)

    init {
        var outputCounter = 0
        var tileX = 0
        var tileNegativeY = 0
        intcode.onOutput = { output ->
            when (outputCounter) {
                0 -> tileX = output.toInt()
                1 -> tileNegativeY = output.toInt()
                2 -> board[Point(tileX, -tileNegativeY)] = output
            }
            outputCounter = (outputCounter + 1) % 3
        }
        intcode[0] = BigInteger.TWO
        intcode.run()
    }

    /**
     * Restores the game to its starting state, immediately after initialization.
     */
    fun reset() {
        intcode.reset()
        intcode[0] = BigInteger.TWO
    }

    /**
     * Plays the game according to a given [strategy] and returns the player's final score.
     */
    fun play(strategy: Strategy): BigInteger {
        while (!intcode.isDone) {
            intcode.run()
            intcode.sendInput(strategy.nextMove(board))
        }
        return board.score
    }
}
