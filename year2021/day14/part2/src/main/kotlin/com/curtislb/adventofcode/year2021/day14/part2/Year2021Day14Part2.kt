/*
TODO: Description goes here
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
            section.forEach { line ->
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
        patternCounts.entriesWithPositiveCount.forEach { (pattern, count) ->
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
    patternCounts.entriesWithPositiveCount.forEach { (pattern, count) ->
        letterCounts[pattern.first()] += count
    }

    val maxValue = letterCounts.entriesWithPositiveCount.maxOfOrNull { it.value }!!
    val minValue = letterCounts.entriesWithPositiveCount.minOfOrNull { it.value }!!
    return maxValue - minValue + 1
}

fun main() {
    println(solve())
}
