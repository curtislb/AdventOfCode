package com.curtislb.adventofcode.common.graph

import com.curtislb.adventofcode.common.testing.assertContainsExactly
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests [Dfs].
 */
class DfsTest {
    @Test
    fun testFindAllPathsWithNoGoals() {
        for (source in 0..3) {
            assertEquals(emptyMap(), SampleDfs.findAllPaths(source) { false })
        }
    }

    @Test
    fun testFindAllPathsWithSourceAsGoal() {
        for (source in 0..3) {
            val paths = SampleDfs.findAllPaths(source) { it == source }
            assertEquals(mapOf(source to listOf(emptyList())), paths)
        }
    }

    @Test
    fun testFindAllPathsInSubgraph() {
        val paths = SampleDfs.findAllPaths(1) { true }
        assertEquals(mapOf(1 to listOf(emptyList()), 3 to listOf(listOf(3))), paths)
    }

    @Test
    fun testFindAllPathsInFullGraph() {
        val paths = SampleDfs.findAllPaths(2) { it == 3 }
        assertEquals(1, paths.size)

        val expectedPaths = listOf(listOf(0, 3), listOf(0, 1, 3), listOf(1, 3))
        val (actualGoal, actualPaths) = paths.entries.first()
        assertEquals(3, actualGoal)
        assertContainsExactly(expectedPaths, actualPaths)
    }

    /**
     * Implementation using a sample graph with directed edges for testing.
     */
    private object SampleDfs : Dfs<Int>() {
        override fun getNeighbors(node: Int): Sequence<Int> = when (node) {
            0 -> sequenceOf(1, 2, 3)
            1 -> sequenceOf(3)
            2 -> sequenceOf(0, 1)
            3 -> emptySequence()
            else -> throw IllegalArgumentException("Unexpected node: $node")
        }
    }
}
