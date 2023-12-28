package com.curtislb.adventofcode.year2023.day11.part2

import java.nio.file.Paths
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2023, day 11, part 2.
 */
class Year2023Day11Part2Test {
    @Test
    fun solve_withRealInput() {
        val solution = solve()
        assertThat(solution).isEqualTo(635832237682L)
    }

    @Test
    fun solve_withTestInput() {
        val solution = solve(
            inputPath = Paths.get("..", "input", "test_input.txt"),
            expansionFactor = 100L
        )
        assertThat(solution).isEqualTo(8410L)
    }
}
