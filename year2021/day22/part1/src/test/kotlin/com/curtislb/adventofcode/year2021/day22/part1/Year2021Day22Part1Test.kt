package com.curtislb.adventofcode.year2021.day22.part1

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2021, day 22, part 1.
 */
class Year2021Day22Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(607573, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "part1", "test_input.txt"))
        assertEquals(590784, solution)
    }
}
