/*
--- Part Two ---

Now that you've identified which tickets contain invalid values, discard those tickets entirely. Use the remaining valid
tickets to determine which field is which.

Using the valid ranges for each field, determine what order the fields appear on the tickets. The order is consistent
between all tickets: if seat is the third field, it is the third field on every ticket, including your ticket.

For example, suppose you have the following notes:

class: 0-1 or 4-19
row: 0-5 or 8-19
seat: 0-13 or 16-19

your ticket:
11,12,13

nearby tickets:
3,9,18
15,1,5
5,14,9

Based on the nearby tickets in the above example, the first position must be row, the second position must be class, and
the third position must be seat; you can conclude that in your ticket, class is 12, row is 11, and seat is 13.

Once you work out which field is which, look for the six fields on your ticket that start with the word departure. What
do you get if you multiply those six values together?
*/

package com.curtislb.adventofcode.year2020.day16.part2

import com.curtislb.adventofcode.common.io.forEachSection
import com.curtislb.adventofcode.common.math.product
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2020, day 16, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(
    inputPath: Path = Paths.get("..", "input", "input.txt"),
    fieldRegex: Regex = Regex("""departure.*""")
): Long {
    val file = inputPath.toFile()
    var sectionIndex = 0
    val fieldNames = mutableListOf<String>()
    val fieldRanges = mutableListOf<List<IntRange>>()
    val validTickets = mutableListOf<List<Int>>()
    val nearbyTickets = mutableListOf<List<Int>>()
    file.forEachSection { lines ->
        when (sectionIndex) {
            0 -> parseFieldsSection(lines).forEach { (field, ranges) ->
                fieldNames.add(field)
                fieldRanges.add(ranges)
            }

            1 -> validTickets.add(parseYourTicketSection(lines))

            2 -> nearbyTickets.addAll(parseNearbyTicketsSection(lines))
        }
        sectionIndex++
    }

    val validRanges = fieldRanges.flatten()
    nearbyTickets.forEach { ticketValues ->
        var isValidTicket = true
        for (value in ticketValues) {
            var isValidValue = false
            for (range in validRanges) {
                if (value in range) {
                    isValidValue = true
                    break
                }
            }
            if (!isValidValue) {
                isValidTicket = false
                break
            }
        }
        if (isValidTicket) {
            validTickets.add(ticketValues)
        }
    }

    val initialIndices = List(fieldNames.size) { fieldNames.indices.toSet() }
    val possibleFields = validTickets.fold(initialIndices) { possibleIndices, ticketValues ->
        val currentPossibleIndices = ticketValues.map { value ->
            fieldNames.indices.filter { fieldIndex ->
                fieldRanges[fieldIndex].any { range -> value in range }
            }.toSet()
        }
        possibleIndices.mapIndexed { index, fieldIndices -> fieldIndices intersect currentPossibleIndices[index] }
    }.map { indices -> indices.map { index -> fieldNames[index] }.toMutableSet() }

    val fieldAssignments = mutableMapOf<String, Int>()
    while (fieldAssignments.size < fieldNames.size) {
        for (index in possibleFields.indices) {
            if (possibleFields[index].size == 1) {
                val field = possibleFields[index].first()
                fieldAssignments[field] = index
                possibleFields.forEach { it.remove(field) }
            }
        }
    }

    return fieldAssignments.filter { (field, _) -> fieldRegex.matches(field) }.map { (_, index) ->
        validTickets[0][index].toLong()
    }.product()
}

private fun parseFieldsSection(lines: List<String>): List<Pair<String, List<IntRange>>> {
    return lines.map { line ->
        val fieldName = line.takeWhile { it != ':' }
        val matchResults = Regex("""\d+-\d+""").findAll(line).toList()
        Pair(fieldName, matchResults.map { result -> result.value.toIntRange(separator = "-") })
    }
}

private fun String.toIntRange(separator: String = ".."): IntRange {
    val (minValue, maxValue) = trim().split(separator).map { it.trim().toInt() }
    return minValue..maxValue
}

private fun parseYourTicketSection(lines: List<String>): List<Int> {
    return lines[1].trim().split(',').map { token -> token.toInt() }
}

private fun parseNearbyTicketsSection(lines: List<String>): List<List<Int>> {
    return lines.subList(1, lines.size).map { line -> line.trim().split(',').map { token -> token.toInt() } }
}

fun main() {
    println(solve())
}
