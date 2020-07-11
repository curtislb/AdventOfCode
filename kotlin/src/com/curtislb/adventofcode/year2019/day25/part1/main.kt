/*
--- Day 25: Cryostasis ---

As you approach Santa's ship, your sensors report two important details:

First, that you might be too late: the internal temperature is -40 degrees.

Second, that one faint life signature is somewhere on the ship.

The airlock door is locked with a code; your best option is to send in a small droid to investigate the situation. You
attach your ship to Santa's, break a small hole in the hull, and let the droid run in before you seal it up again.
Before your ship starts freezing, you detach your ship and set it to automatically stay within range of Santa's ship.

This droid can follow basic instructions and report on its surroundings; you can communicate with it through an Intcode
program (your puzzle input) running on an ASCII-capable computer.

As the droid moves through its environment, it will describe what it encounters. When it says Command?, you can give it
a single instruction terminated with a newline (ASCII code 10). Possible instructions are:

  - Movement via north, south, east, or west.
  - To take an item the droid sees in the environment, use the command take <name of item>. For example, if the droid
    reports seeing a red ball, you can pick it up with take red ball.
  - To drop an item the droid is carrying, use the command drop <name of item>. For example, if the droid is carrying a
    green ball, you can drop it with drop green ball.
  - To get a list of all of the items the droid is currently carrying, use the command inv (for "inventory").

Extra spaces or other characters aren't allowed - instructions must be provided precisely.

Santa's ship is a Reindeer-class starship; these ships use pressure-sensitive floors to determine the identity of
droids and crew members. The standard configuration for these starships is for all droids to weigh exactly the same
amount to make them easier to detect. If you need to get past such a sensor, you might be able to reach the correct
weight by carrying items from the environment.

Look around the ship and see if you can find the password for the main airlock.
*/

package com.curtislb.adventofcode.year2019.day25.part1

import com.curtislb.adventofcode.common.grid.Direction
import com.curtislb.adventofcode.common.io.pathToInput
import com.curtislb.adventofcode.year2019.day25.rescue.Droid
import com.curtislb.adventofcode.year2019.day25.rescue.command.Move
import com.curtislb.adventofcode.year2019.day25.rescue.command.Take
import java.nio.file.Path

/**
 * Returns the solution to the puzzle for 2019, day 25, part 1.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = pathToInput(year = 2019, day = 25)): String? {
    // Send a list of predetermined commands to the droid.
    val droid = Droid(inputPath.toFile())
    droid.start()
    val commands = listOf(
        Move(Direction.LEFT),
        Take("ornament"),
        Move(Direction.LEFT),
        Take("astrolabe"),
        Move(Direction.UP),
        Take("fuel cell"),
        Move(Direction.DOWN),
        Move(Direction.DOWN),
        Take("hologram"),
        Move(Direction.UP),
        Move(Direction.RIGHT),
        Move(Direction.RIGHT),
        Move(Direction.RIGHT),
        Move(Direction.DOWN),
        Move(Direction.LEFT),
        Move(Direction.UP),
        Move(Direction.LEFT),
        Move(Direction.UP),
        Move(Direction.LEFT),
        Move(Direction.UP)
    )
    commands.forEach { droid.sendCommand(it) }

    // Extract the password from the last line of program output.
    val line = droid.lastLine
    return if (line != null) Regex("""(\d+)""").find(line)?.groupValues?.get(1) else null
}

fun main() = when (val solution = solve()) {
    null -> println("No password found.")
    else -> println(solution)
}
