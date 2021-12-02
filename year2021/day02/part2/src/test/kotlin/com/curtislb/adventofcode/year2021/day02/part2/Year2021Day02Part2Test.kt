package com.curtislb.adventofcode.year2021.day02.part2

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2021, day 02, part 2.
 */
class Year2021Day02Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(1960569556, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "test_input.txt"))
        assertEquals(900, solution)
    }
}
