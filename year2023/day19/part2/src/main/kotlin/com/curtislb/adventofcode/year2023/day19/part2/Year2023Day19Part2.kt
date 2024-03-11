/*
--- Part Two ---

Even with your help, the sorting process still isn't fast enough.

One of the Elves comes up with a new plan: rather than sort parts individually through all of these
workflows, maybe you can figure out in advance which combinations of ratings will be accepted or
rejected.

Each of the four ratings (`x`, `m`, `a`, `s`) can have an integer value ranging from a minimum of 1
to a maximum of 4000. Of all possible distinct combinations of ratings, your job is to figure out
which ones will be accepted.

In the above example, there are 167409079868000 distinct combinations of ratings that will be
accepted.

Consider only your list of workflows; the list of part ratings that the Elves wanted you to sort is
no longer relevant. How many distinct combinations of ratings will be accepted by the Elves'
workflows?
*/

package com.curtislb.adventofcode.year2023.day19.part2

import com.curtislb.adventofcode.common.io.forEachSection
import com.curtislb.adventofcode.year2023.day19.workflow.SortingProcess
import com.curtislb.adventofcode.year2023.day19.workflow.Workflow
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2023, day 19, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(
    inputPath: Path = Paths.get("..", "input", "input.txt"),
    ratingRange: IntRange = 1..4000,
    startWorkflow: String = "in"
): Long {
    // Parse workflows from the input file
    val workflows = mutableListOf<Workflow>()
    inputPath.toFile().forEachSection { lines ->
        if (workflows.isEmpty()) {
            for (line in lines) {
                workflows.add(Workflow.fromString(line))
            }
        }
    }

    // Count all possible parts accepted by the sorting process
    val process = SortingProcess(workflows)
    return process.countAccepted(ratingRange, startWorkflow)
}

fun main() {
    println(solve())
}
