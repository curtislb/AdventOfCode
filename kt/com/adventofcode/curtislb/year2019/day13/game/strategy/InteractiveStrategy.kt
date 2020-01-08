package com.adventofcode.curtislb.year2019.day13.game.strategy

import com.adventofcode.curtislb.year2019.day13.game.board.Board
import java.math.BigInteger

/**
 * A [Strategy] that prints the current [Board] state and waits for the next move to be selected via standard input.
 *
 * Valid inputs are as follows:
 * - `.` (default) - Leave joystick in the neutral position
 * - `<` - Tilt joystick to the left
 * - `>` - Tilt joystick to the right
 */
object InteractiveStrategy : Strategy {
    override fun nextMove(board: Board): BigInteger {
        println(board)
        print("Input (< [.] >): ")
        return when (val input = readLine()) {
            "<" -> BigInteger("-1")
            ".", "" -> BigInteger.ZERO
            ">" -> BigInteger.ONE
            else -> throw IllegalArgumentException("Invalid input: $input.")
        }
    }
}
