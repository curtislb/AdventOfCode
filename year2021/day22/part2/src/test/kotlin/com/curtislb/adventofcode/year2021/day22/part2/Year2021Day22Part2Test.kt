package com.curtislb.adventofcode.year2021.day22.part2

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2021, day 22, part 2.
 */
class Year2021Day22Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(1267133912086024L, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "part2", "test_input.txt"))
        assertEquals(2758514936282235L, solution)
    }
}
