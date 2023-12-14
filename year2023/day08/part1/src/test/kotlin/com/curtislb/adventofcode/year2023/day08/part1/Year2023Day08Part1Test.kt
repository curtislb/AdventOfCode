package com.curtislb.adventofcode.year2023.day08.part1

import java.nio.file.Paths
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2023, day 8, part 1.
 */
class Year2023Day08Part1Test {
    @Test
    fun solve_withRealInput() {
        val solution = solve()
        assertThat(solution).isEqualTo(24253L)
    }

    @Test
    fun solve_withTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "part1", "test_input.txt"))
        assertThat(solution).isEqualTo(6L)
    }
}
