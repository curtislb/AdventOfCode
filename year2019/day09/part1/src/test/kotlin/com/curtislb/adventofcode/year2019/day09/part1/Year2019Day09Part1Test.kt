package com.curtislb.adventofcode.year2019.day09.part1

import java.math.BigInteger
import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 9, part 1.
 */
class Year2019Day09Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(BigInteger("3780860499"), solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "test_input.txt"))
        assertEquals(BigInteger("1219070632396864"), solution)
    }
}
