package com.curtislb.adventofcode.year2019.day11.part2

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for day 11, part 2.
 */
class TestSolution {
    @Test fun solveWithRealInput() {
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
