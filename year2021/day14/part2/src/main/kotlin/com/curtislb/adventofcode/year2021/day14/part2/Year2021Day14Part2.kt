/*
--- Part Two ---

The resulting polymer isn't nearly strong enough to reinforce the submarine. You'll need to run more
steps of the pair insertion process; a total of 40 steps should do it.

In the above example, the most common element is `B` (occurring 2192039569602 times) and the least
common element is `H` (occurring 3849876073 times); subtracting these produces 2188189693529.

Apply 40 steps of pair insertion to the polymer template and find the most and least common elements
in the result. What do you get if you take the quantity of the most common element and subtract the
quantity of the least common element?
*/

package com.curtislb.adventofcode.year2021.day14.part2

import com.curtislb.adventofcode.common.collection.Counter
import com.curtislb.adventofcode.common.io.forEachSection
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2021, day 14, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt")): Long {
    var template: String? = null
    val rules = mutableMapOf<String, String>()
    inputPath.toFile().forEachSection { section ->
        if (template == null) {
            template = section.first().trim()
        } else {
            for (line in section) {
                val (pattern, insertion) = line.trim().split(" -> ")
                rules[pattern] = insertion
            }
        }
    }

    val polymer = template!!
    var patternCounts = Counter<String>()
    for (i in 0 until polymer.lastIndex) {
        patternCounts[polymer.substring(i..(i + 1))]++
    }

    repeat(40) {
        val newPatternCounts = Counter<String>()
        for ((pattern, count) in patternCounts.entriesWithPositiveCount) {
            val insertion = rules[pattern]
            if (insertion != null) {
                newPatternCounts["${pattern.first()}$insertion"] += count
                newPatternCounts["$insertion${pattern.last()}"] += count
            } else {
                newPatternCounts[pattern] += count
            }
        }
        patternCounts = newPatternCounts
    }

    val letterCounts = Counter<Char>()
    for ((pattern, count) in patternCounts.entriesWithPositiveCount) {
        letterCounts[pattern.first()] += count
    }

    val maxValue = letterCounts.entriesWithPositiveCount.maxOfOrNull { it.value }!!
    val minValue = letterCounts.entriesWithPositiveCount.minOfOrNull { it.value }!!
    return maxValue - minValue + 1
}

fun main() {
    println(solve())
}
