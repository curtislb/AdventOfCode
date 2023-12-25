package com.curtislb.adventofcode.year2023.day10.part1

import java.nio.file.Paths
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2023, day 10, part 1.
 */
class Year2023Day10Part1Test {
    @Test
    fun solve_withRealInput() {
        val solution = solve()
        assertThat(solution).isEqualTo(6831)
    }

    @Test
    fun solve_withTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "part1", "test_input.txt"))
        assertThat(solution).isEqualTo(8)
    }
}
