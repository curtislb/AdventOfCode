package com.curtislb.adventofcode.year2021.day21.part2

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2021, day 21, part 2.
 */
class Year2021Day21Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(110271560863819L, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "test_input.txt"))
        assertEquals(444356092776315L, solution)
    }
}
