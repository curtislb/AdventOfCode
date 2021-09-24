package com.curtislb.adventofcode.year2019.day17.part2

import java.math.BigInteger
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 17, part 2.
 */
class Year2019Day17Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(BigInteger("1681189"), solve())
    }
}
