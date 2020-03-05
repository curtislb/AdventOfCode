package com.curtislb.adventofcode.year2019.day08.part2

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for day 8, part 2.
 */
class TestSolution {
    @Test fun solveWithRealInput() {
        val expected = """
            #### #  # #  #  ##    ## 
               # #  # # #  #  #    # 
              #  #  # ##   #       # 
             #   #  # # #  #       # 
            #    #  # # #  #  # #  # 
            ####  ##  #  #  ##   ##  
        """.trimIndent()
        assertEquals(expected, solve())
    }
}
