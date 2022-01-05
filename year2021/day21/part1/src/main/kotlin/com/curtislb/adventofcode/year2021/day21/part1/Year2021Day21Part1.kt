/*
--- Day 21: Dirac Dice ---

There's not much to do as you slowly descend to the bottom of the ocean. The submarine computer
challenges you to a nice game of Dirac Dice.

This game consists of a single die, two pawns, and a game board with a circular track containing ten
spaces marked 1 through 10 clockwise. Each player's starting space is chosen randomly (your puzzle
input). Player 1 goes first.

Players take turns moving. On each player's turn, the player rolls the die three times and adds up
the results. Then, the player moves their pawn that many times forward around the track (that is,
moving clockwise on spaces in order of increasing value, wrapping back around to 1 after 10). So, if
a player is on space 7 and they roll 2, 2, and 1, they would move forward 5 times, to spaces 8, 9,
10, 1, and finally stopping on 2.

After each player moves, they increase their score by the value of the space their pawn stopped on.
Players' scores start at 0. So, if the first player starts on space 7 and rolls a total of 5, they
would stop on space 2 and add 2 to their score (for a total score of 2). The game immediately ends
as a win for any player whose score reaches at least 1000.

Since the first game is a practice game, the submarine opens a compartment labeled deterministic
dice and a 100-sided die falls out. This die always rolls 1 first, then 2, then 3, and so on up to
100, after which it starts over at 1 again. Play using this die.

For example, given these starting positions:

```
Player 1 starting position: 4
Player 2 starting position: 8
```

This is how the game would go:

- Player 1 rolls 1+2+3 and moves to space 10 for a total score of 10.
- Player 2 rolls 4+5+6 and moves to space 3 for a total score of 3.
- Player 1 rolls 7+8+9 and moves to space 4 for a total score of 14.
- Player 2 rolls 10+11+12 and moves to space 6 for a total score of 9.
- Player 1 rolls 13+14+15 and moves to space 6 for a total score of 20.
- Player 2 rolls 16+17+18 and moves to space 7 for a total score of 16.
- Player 1 rolls 19+20+21 and moves to space 6 for a total score of 26.
- Player 2 rolls 22+23+24 and moves to space 6 for a total score of 22.

...after many turns...

- Player 2 rolls 82+83+84 and moves to space 6 for a total score of 742.
- Player 1 rolls 85+86+87 and moves to space 4 for a total score of 990.
- Player 2 rolls 88+89+90 and moves to space 3 for a total score of 745.
- Player 1 rolls 91+92+93 and moves to space 10 for a final score, 1000.

Since player 1 has at least 1000 points, player 1 wins and the game ends. At this point, the losing
player had 745 points and the die had been rolled a total of 993 times; 745 * 993 = 739785.

Play a practice game using the deterministic 100-sided die. The moment either player wins, what do
you get if you multiply the score of the losing player by the number of times the die was rolled
during the game?
*/

package com.curtislb.adventofcode.year2021.day21.part1

import com.curtislb.adventofcode.common.io.forEachLineIndexed
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2021, day 21, part 1.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt")): Int? {
    val spaces = IntArray(2)
    inputPath.toFile().forEachLineIndexed { index, line ->
        spaces[index] = line.substringAfter(':').trim().toInt() - 1
    }

    val scores = IntArray(2)
    var rollCount = 0
    var player = 0
    while (scores.all { it < 1000 }) {
        val moveDistance = roll(rollCount + 1) + roll(rollCount + 2) + roll(rollCount + 3)

        spaces[player] = (spaces[player] + moveDistance) % 10
        scores[player] += spaces[player] + 1

        rollCount += 3
        player = (player + 1) % 2
    }

    return scores.minOrNull()?.let { it * rollCount }
}

fun roll(rollCount: Int) = when (val modRoll = rollCount % 100) {
    0 -> 100
    else -> modRoll
}

fun main() = when (val solution = solve()) {
    null -> println("No solution found")
    else -> println(solution)
}
