package com.curtislb.adventofcode.common.graph

import com.curtislb.adventofcode.common.testing.assertContainsExactly
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests [dfsPaths].
 */
class DfsTest {
    @Test fun testPathsWithNoGoals() {
        for (source in 0..3) {
            assertEquals(emptyMap(), dfsPaths(source, isGoal = { false }, getNeighbors = this::getNeighbors))
        }
    }

    @Test fun testPathsWithSourceAsGoal() {
        for (source in 0..3) {
            val paths = dfsPaths(source, isGoal = { it == source }, getNeighbors = this::getNeighbors)
            assertEquals(mapOf(Pair(source, listOf(emptyList()))), paths)
        }
    }

    @Test fun testPathsInSubgraph() {
        val paths = dfsPaths(1, isGoal = { true }, getNeighbors = this::getNeighbors)
        assertEquals(mapOf(Pair(1, listOf(emptyList())), Pair(3, listOf(listOf(3)))), paths)
    }

    @Test fun testPathsInFullGraph() {
        val paths = dfsPaths(2, isGoal = { it == 3 }, getNeighbors = this::getNeighbors)
        assertEquals(1, paths.size)

        val expectedPaths = listOf(
            listOf(0, 3),
            listOf(0, 1, 3),
            listOf(1, 3)
        )
        val (actualGoal, actualPaths) = paths.entries.first()
        assertEquals(3, actualGoal)
        assertContainsExactly(expectedPaths, actualPaths)
    }

    /**
     * Returns all neighbors of [node] in the test graph as a finite sequence.
     */
    private fun getNeighbors(node: Int): Sequence<Int> = when (node) {
        0 -> sequenceOf(1, 2, 3)
        1 -> sequenceOf(3)
        2 -> sequenceOf(0, 1)
        3 -> emptySequence()
        else -> throw IllegalArgumentException("Unexpected node: $node")
    }
}
