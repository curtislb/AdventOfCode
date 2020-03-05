/*
--- Day 13: Care Package ---

As you ponder the solitude of space and the ever-increasing three-hour roundtrip for messages between you and Earth,
you notice that the Space Mail Indicator Light is blinking. To help keep you sane, the Elves have sent you a care
package.

It's a new game for the ship's arcade cabinet! Unfortunately, the arcade is all the way on the other end of the ship.
Surely, it won't be hard to build your own - the care package even comes with schematics.

The arcade cabinet runs Intcode software like the game the Elves sent (your puzzle input). It has a primitive screen
capable of drawing square tiles on a grid. The software draws tiles to the screen with output instructions: every three
output instructions specify the x position (distance from the left), y position (distance from the top), and tile id.
The tile id is interpreted as follows:

  - 0 is an empty tile. No game object appears in this tile.
  - 1 is a wall tile. Walls are indestructible barriers.
  - 2 is a block tile. Blocks can be broken by the ball.
  - 3 is a horizontal paddle tile. The paddle is indestructible.
  - 4 is a ball tile. The ball moves diagonally and bounces off objects.

For example, a sequence of output values like 1,2,3,6,5,4 would draw a horizontal paddle tile (1 tile from the left and
2 tiles from the top) and a ball tile (6 tiles from the left and 5 tiles from the top).

Start the game. How many block tiles are on the screen when the game exits?
*/

package com.curtislb.adventofcode.year2019.day13.part1

import com.curtislb.adventofcode.common.io.pathToInput
import com.curtislb.adventofcode.year2019.day13.game.Game
import com.curtislb.adventofcode.year2019.day13.game.Tile
import java.nio.file.Path

/**
 * Returns the solution to the puzzle for day 13, part 1.
 *
 * @param inputPath The path to the input file for this puzzle.
 * @param tileType The type of tile to count occurrences of on the game board.
 */
fun solve(inputPath: Path = pathToInput(year = 2019, day = 13), tileType: Tile = Tile.BLOCK): Int {
    val game = Game(inputPath.toFile())
    return game.board.findAll(tileType).size
}

// Answer: 286
fun main() {
    println(solve())
}
