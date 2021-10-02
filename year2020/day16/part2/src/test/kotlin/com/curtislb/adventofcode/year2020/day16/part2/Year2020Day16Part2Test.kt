package com.curtislb.adventofcode.year2020.day16.part2

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2020, day 16, part 2.
 */
class Year2020Day16Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(1069784384303L, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(
            inputPath = Paths.get("..", "input", "part2", "test_input.txt"),
            fieldRegex = Regex("""[a-z]{4,5}""")
        )
        assertEquals(156L, solution)
    }
}
