package com.curtislb.adventofcode.year2021.day13.part1

import com.curtislb.adventofcode.year2021.day13.part2.solve
import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2021, day 13, part 2.
 */
class Year2021Day13Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(
            """
                  ##  ##   ##    ## #### #### #  # #  #
                   # #  # #  #    # #    #    # #  #  #
                   # #    #  #    # ###  ###  ##   #  #
                   # # ## ####    # #    #    # #  #  #
                #  # #  # #  # #  # #    #    # #  #  #
                 ##   ### #  #  ##  #### #    #  #  ## 
            """.trimIndent(),
            solve()
        )
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "test_input.txt"))
        assertEquals(
            """
                #####
                #   #
                #   #
                #   #
                #####
            """.trimIndent(),
            solution
        )
    }
}
