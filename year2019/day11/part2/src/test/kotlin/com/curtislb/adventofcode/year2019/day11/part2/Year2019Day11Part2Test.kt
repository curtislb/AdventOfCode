package com.curtislb.adventofcode.year2019.day11.part2

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 11, part 2.
 */
class Year2019Day11Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        val expected = """
            | ####  ##   ##  ###  #  # #  # #    ###    
            |    # #  # #  # #  # #  # # #  #    #  #   
            |   #  #    #    #  # #### ##   #    ###    
            |  #   #    # ## ###  #  # # #  #    #  #   
            | #    #  # #  # # #  #  # # #  #    #  #   
            | ####  ##   ### #  # #  # #  # #### ###    
        """.trimMargin()
        assertEquals(expected, solve())
    }
}
