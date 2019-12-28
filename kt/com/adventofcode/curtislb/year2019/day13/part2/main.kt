/*
--- Part Two ---

The game didn't run because you didn't put in any quarters. Unfortunately, you did not bring any quarters. Memory
address 0 represents the number of quarters that have been inserted; set it to 2 to play for free.

The arcade cabinet has a joystick that can move left and right. The software reads the position of the joystick with
input instructions:

  - If the joystick is in the neutral position, provide 0.
  - If the joystick is tilted to the left, provide -1.
  - If the joystick is tilted to the right, provide 1.

The arcade cabinet also has a segment display capable of showing a single number that represents the player's current
score. When three output instructions specify X=-1, Y=0, the third output instruction is not a tile; the value instead
specifies the new score to show in the segment display. For example, a sequence of output values like -1,0,12345 would
show 12345 as the player's current score.

Beat the game by breaking all the blocks. What is your score after the last block is broken?
*/

package com.adventofcode.curtislb.year2019.day13.part2

import com.adventofcode.curtislb.common.io.pathToInput
import com.adventofcode.curtislb.year2019.day13.game.Game
import com.adventofcode.curtislb.year2019.day13.game.strategy.GreedyStrategy

private val INPUT_PATH = pathToInput(year = 2019, day = 13, fileName = "input.txt")

// Answer: 14538
fun main() {
    val game = Game(INPUT_PATH.toFile())
    println(game.play(GreedyStrategy))
}
