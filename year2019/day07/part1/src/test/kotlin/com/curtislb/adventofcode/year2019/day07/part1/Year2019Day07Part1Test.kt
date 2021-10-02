package com.curtislb.adventofcode.year2019.day07.part1

import java.math.BigInteger
import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 7, part 1.
 */
class Year2019Day07Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(BigInteger("272368"), solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "part1", "test_input.txt"))
        assertEquals(BigInteger("65210"), solution)
    }
}
