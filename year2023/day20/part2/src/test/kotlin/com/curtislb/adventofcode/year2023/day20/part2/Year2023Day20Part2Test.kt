package com.curtislb.adventofcode.year2023.day20.part2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2023, day 20, part 2.
 */
class Year2023Day20Part2Test {
    @Test
    fun solve_withRealInput() {
        val solution = solve()
        assertThat(solution).isEqualTo(243037165713371L)
    }
}
