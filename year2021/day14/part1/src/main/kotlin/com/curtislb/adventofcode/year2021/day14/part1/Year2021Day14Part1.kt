/*
TODO: Description goes here
*/

package com.curtislb.adventofcode.year2021.day14.part1

import com.curtislb.adventofcode.common.collection.Counter
import com.curtislb.adventofcode.common.io.forEachSection
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2021, day 14, part 1.
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

    var polymer = template!!
//    println(polymer)
    repeat(10) {
        polymer = buildString {
            for (index in 0 until polymer.lastIndex) {
                val substring = polymer.substring(index..(index + 1))
                append(substring[0])
                if (substring in rules) {
                    append(rules[substring])
                }
            }
            append(polymer.last())
        }
//        println(polymer)
    }

    val counter = Counter<Char>()
    polymer.forEach { counter[it]++ }
    val maxValue = counter.entriesWithPositiveCount.maxOfOrNull { it.value }!!
    val minValue = counter.entriesWithPositiveCount.minOfOrNull { it.value }!!

    return maxValue - minValue
}

fun main() {
    println(solve())
}
