/*
--- Part Two ---

On the other hand, it might be wise to try a different strategy: let the giant squid win.

You aren't sure how many bingo boards a giant squid could play at once, so rather than waste time
counting its arms, the safe thing to do is to figure out which board will win last and choose that
one. That way, no matter which boards it picks, it will win for sure.

In the above example, the second board is the last to win, which happens after 13 is eventually
called and its middle column is completely marked. If you were to keep playing until this point, the
second board would have a sum of unmarked numbers equal to 148 for a final score of 148 * 13 = 1924.

Figure out which board will win last. Once it wins, what would its final score be?
*/

package com.curtislb.adventofcode.year2021.day04.part2

import com.curtislb.adventofcode.common.io.forEachSection
import com.curtislb.adventofcode.common.parse.toInts
import com.curtislb.adventofcode.year2021.day04.bingo.BingoBoard
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2021, day 4, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt")): Int? {
    var numbers: List<Int> = emptyList()
    val boards = mutableSetOf<BingoBoard>()
    inputPath.toFile().forEachSection { lines ->
        if (numbers.isEmpty()) {
            numbers = lines.first().toInts()
        } else {
            boards.add(BingoBoard(lines))
        }
    }

    // Remove boards as they win, returning the score of the last winning board
    numbers.forEach { number ->
        val boardsToRemove = mutableSetOf<BingoBoard>()
        boards.forEach { board ->
            board.mark(number)
            if (board.isWinning) {
                if (boards.size == 1) {
                    return board.winningScore
                }
                boardsToRemove.add(board)
            }
        }
        boards.removeAll(boardsToRemove)
    }

    // All numbers were called without the final board winning
    return null
}

fun main() = when (val solution = solve()) {
    null -> println("No solution found.")
    else -> println(solution)
}
