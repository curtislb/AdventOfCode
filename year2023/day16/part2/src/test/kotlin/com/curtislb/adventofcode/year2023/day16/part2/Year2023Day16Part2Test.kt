package com.curtislb.adventofcode.year2023.day16.part2

import java.nio.file.Paths
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2023, day 16, part 2.
 */
class Year2023Day16Part2Test {
    @Test
    fun solve_withRealInput() {
        val solution = solve()
        assertThat(solution).isEqualTo(8383)
    }

    @Test
    fun solve_withTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "test_input.txt"))
        assertThat(solution).isEqualTo(51)
    }
}
