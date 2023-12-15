package com.curtislb.adventofcode.year2023.day09.part1

import java.nio.file.Paths
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2023, day 9, part 1.
 */
class Year2023Day09Part1Test {
    @Test
    fun solve_withRealInput() {
        val solution = solve()
        assertThat(solution).isEqualTo(2075724761)
    }

    @Test
    fun solve_withTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "test_input.txt"))
        assertThat(solution).isEqualTo(114)
    }
}
