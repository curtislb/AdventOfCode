package com.curtislb.adventofcode.year2019.day13.game.strategy

import com.curtislb.adventofcode.year2019.day13.game.Board
import java.math.BigInteger

/**
 * A method for selecting the player's next input, given the current state of a game.
 */
interface Strategy {
    /**
     * Returns an integer representing the player's next move, based on the current [board] state.
     *
     * Values that may be returned by this method and their corresponding moves are as follows:
     *
     * - 0: Leave joystick in the neutral position.
     * - -1: Tilt joystick to the left.
     * - 1: Tilt joystick to the right.
     */
    fun nextMove(board: Board): BigInteger
}
