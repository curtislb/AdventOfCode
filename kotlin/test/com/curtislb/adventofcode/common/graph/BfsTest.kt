package com.curtislb.adventofcode.common.graph

import com.curtislb.adventofcode.common.testing.assertContainsExactly
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

/**
 * Tests [bfsApply] and [bfsDistance].
 */
class BfsTest {
    @Test fun testApplyToSourceOnly() {
        for (source in 0..7) {
            val appliedList = mutableListOf<Pair<Int, Long>>()
            bfsApply(source, this::getNeighbors) { node, distance ->
                appliedList.add(Pair(node, distance))
                true // Stop searching.
            }
            assertEquals(listOf(Pair(source, 0L)), appliedList)
        }
    }

    @Test fun testApplyToSourceAndNeighbors() {
        val appliedList = mutableListOf<Pair<Int, Long>>()
        bfsApply(2, this::getNeighbors) { node, distance ->
            if (distance <= 1L) {
                appliedList.add(Pair(node, distance))
                false // Don't stop searching.
            } else {
                true // Stop searching.
            }
        }

        val expected = listOf(Pair(2, 0L), Pair(3, 1L), Pair(6, 1L))
        assertContainsExactly(expected, appliedList)
    }

    @Test fun testApplyToAllInSubgraph() {
        val appliedList = mutableListOf<Pair<Int, Long>>()
        bfsApply(2, this::getNeighbors) { node, distance ->
            appliedList.add(Pair(node, distance))
            false // Don't stop searching.
        }

        val expected = listOf(Pair(2, 0L), Pair(3, 1L), Pair(5, 2L), Pair(6, 1L), Pair(7, 2L))
        assertContainsExactly(expected, appliedList)
    }

    @Test fun testApplyToAllNodes() {
        val appliedList = mutableListOf<Pair<Int, Long>>()
        bfsApply(0, this::getNeighbors) { node, distance ->
            appliedList.add(Pair(node, distance))
            false // Don't stop searching.
        }

        val expected = listOf(
            Pair(0, 0L),
            Pair(1, 1L),
            Pair(2, 2L),
            Pair(3, 3L),
            Pair(4, 2L),
            Pair(5, 2L),
            Pair(6, 3L),
            Pair(7, 4L)
        )
        assertContainsExactly(expected, appliedList)
    }

    @Test fun testDistanceToSource() {
        for (source in 0..7) {
            val distance = bfsDistance(
                source,
                isGoal = { it == source },
                getNeighbors = this::getNeighbors
            )
            assertEquals(0L, distance)
        }
    }

    @Test fun testDistanceToNonexistentNode() {
        for (source in 0..7) {
            val distance = bfsDistance(
                source,
                isGoal = { it == 8 },
                getNeighbors = this::getNeighbors
            )
            assertNull(distance)
        }
    }

    @Test fun testDistanceToUnreachableNode() {
        val distance = bfsDistance(
            source = 2,
            isGoal = { it == 1 },
            getNeighbors = this::getNeighbors
        )
        assertNull(distance)
    }

    @Test fun testDistanceToReachableNode() {
        val distance = bfsDistance(
            source = 0,
            isGoal = { it == 6 },
            getNeighbors = this::getNeighbors
        )
        assertEquals(3L, distance)
    }

    /**
     * Returns all neighbors of [node] in the test graph as a finite sequence.
     */
    private fun getNeighbors(node: Int): Sequence<Int> = when (node) {
        0 -> sequenceOf(1)
        1 -> sequenceOf(2, 4, 5)
        2 -> sequenceOf(3, 6)
        3 -> sequenceOf(2, 7)
        4 -> sequenceOf(0, 5)
        5 -> sequenceOf(6)
        6 -> sequenceOf(5)
        7 -> sequenceOf(3, 6)
        else -> throw IllegalArgumentException("Unexpected node: $node")
    }
}
