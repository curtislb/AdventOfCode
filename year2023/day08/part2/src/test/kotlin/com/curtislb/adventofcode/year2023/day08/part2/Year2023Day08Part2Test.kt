package com.curtislb.adventofcode.year2023.day08.part2

import java.nio.file.Paths
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2023, day 8, part 2.
 */
class Year2023Day08Part2Test {
    @Test
    fun solve_withRealInput() {
        val solution = solve()
        assertThat(solution).isEqualTo(12357789728873L)
    }

    @Test
    fun solve_withTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "part2", "test_input.txt"))
        assertThat(solution).isEqualTo(6L)
    }
}
