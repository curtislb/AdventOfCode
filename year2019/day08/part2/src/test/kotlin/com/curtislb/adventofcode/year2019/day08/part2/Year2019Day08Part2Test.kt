package com.curtislb.adventofcode.year2019.day08.part2

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 8, part 2.
 */
class Year2019Day08Part2Test {
    @Test
    fun testSolutionWithRealInput() {
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
