package com.curtislb.adventofcode.common.graph

import com.curtislb.adventofcode.common.testing.assertContainsExactly
import kotlin.test.assertEquals
import kotlin.test.assertNull
import org.junit.jupiter.api.Test

/**
 * Tests [Bfs].
 */
class BfsTest {
    @Test
    fun testForEachNodeWithSourceOnly() {
        for (source in 0..7) {
            val appliedList = mutableListOf<Pair<Int, Long>>()
            SampleBfs.forEachNode(source) { node, distance ->
                appliedList.add(Pair(node, distance))
                true // Stop searching
            }
            assertEquals(listOf(Pair(source, 0L)), appliedList)
        }
    }

    @Test
    fun testForEachNodeWithSourceAndNeighbors() {
        val appliedList = mutableListOf<Pair<Int, Long>>()
        SampleBfs.forEachNode(source = 2) { node, distance ->
            if (distance <= 1L) {
                appliedList.add(Pair(node, distance))
                false // Don't stop searching
            } else {
                true // Stop searching
            }
        }

        val expected = listOf(Pair(2, 0L), Pair(3, 1L), Pair(6, 1L))
        assertContainsExactly(expected, appliedList)
    }

    @Test
    fun testForEachNodeWithAllNodesInSubgraph() {
        val appliedList = mutableListOf<Pair<Int, Long>>()
        SampleBfs.forEachNode(source = 2) { node, distance ->
            appliedList.add(Pair(node, distance))
            false // Don't stop searching
        }

        val expected = listOf(Pair(2, 0L), Pair(3, 1L), Pair(5, 2L), Pair(6, 1L), Pair(7, 2L))
        assertContainsExactly(expected, appliedList)
    }

    @Test
    fun testForEachNodeWithAllNodes() {
        val appliedList = mutableListOf<Pair<Int, Long>>()
        SampleBfs.forEachNode(source = 0) { node, distance ->
            appliedList.add(Pair(node, distance))
            false // Don't stop searching
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

    @Test
    fun testFindShortestDistanceToSource() {
        for (source in 0..7) {
            val distance = SampleBfs.findShortestDistance(source) { it == source }
            assertEquals(0L, distance)
        }
    }

    @Test
    fun testFindShortestDistanceToNonexistentNode() {
        for (source in 0..7) {
            val distance = SampleBfs.findShortestDistance(source) { it == 8 }
            assertNull(distance)
        }
    }

    @Test
    fun testFindShortestDistanceToUnreachableNode() {
        val distance = SampleBfs.findShortestDistance(source = 2) { it == 1 }
        assertNull(distance)
    }

    @Test
    fun testDistanceToReachableNode() {
        val distance = SampleBfs.findShortestDistance(source = 0) { it == 6 }
        assertEquals(3L, distance)
    }

    /**
     * Implementation using a sample graph with directed edges for testing.
     */
    private object SampleBfs : Bfs<Int>() {
        override fun getNeighbors(node: Int): List<Int> = when (node) {
            0 -> listOf(1)
            1 -> listOf(2, 4, 5)
            2 -> listOf(3, 6)
            3 -> listOf(2, 7)
            4 -> listOf(0, 5)
            5 -> listOf(6)
            6 -> listOf(5)
            7 -> listOf(3, 6)
            else -> throw IllegalArgumentException("Unexpected node: $node")
        }
    }
}
