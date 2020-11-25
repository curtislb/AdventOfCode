package com.curtislb.adventofcode.common.graph

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

/**
 * Tests [dijkstraShortestDistance].
 */
class DijkstraTest {
    @Test
    fun testShortestDistanceToSource() {
        for (source in 1..8) {
            val distance = dijkstraShortestDistance(source, isGoal = { it == source }, getEdges = ::getEdges)
            assertEquals(0L, distance)
        }
    }

    @Test
    fun testShortestDistanceToNonexistentNode() {
        for (source in 1..8) {
            val distance = dijkstraShortestDistance(source, isGoal = { it == 9 }, getEdges = ::getEdges)
            assertNull(distance)
        }
    }

    @Test
    fun testShortestDistanceToUnreachableNode() {
        val distance = dijkstraShortestDistance(source = 5, isGoal = { it == 7 }, getEdges = ::getEdges)
        assertNull(distance)
    }

    @Test
    fun testShortestDistanceToReachableNodes() {
        val source = 1
        assertEquals(9L, dijkstraShortestDistance(source, isGoal = { it == 2 }, getEdges = ::getEdges))
        assertEquals(32L, dijkstraShortestDistance(source, isGoal = { it == 3 }, getEdges = ::getEdges))
        assertEquals(45L, dijkstraShortestDistance(source, isGoal = { it == 4 }, getEdges = ::getEdges))
        assertEquals(34L, dijkstraShortestDistance(source, isGoal = { it == 5 }, getEdges = ::getEdges))
        assertEquals(14L, dijkstraShortestDistance(source, isGoal = { it == 6 }, getEdges = ::getEdges))
        assertEquals(15L, dijkstraShortestDistance(source, isGoal = { it == 7 }, getEdges = ::getEdges))
        assertEquals(50L, dijkstraShortestDistance(source, isGoal = { it == 8 }, getEdges = ::getEdges))
    }

    /**
     * Returns all finite, weighted edges from [node] as a finite sequence.
     */
    private fun getEdges(node: Int): Sequence<DirectedEdge<Int>> = when (node) {
        1 -> sequenceOf(DirectedEdge(2, 9L), DirectedEdge(6, 14L), DirectedEdge(7, 15L))
        2 -> sequenceOf(DirectedEdge(3, 24L))
        3 -> sequenceOf(DirectedEdge(5, 2L), DirectedEdge(8, 19L))
        4 -> sequenceOf(DirectedEdge(3, 6L), DirectedEdge(8, 6L))
        5 -> sequenceOf(DirectedEdge(4, 11L), DirectedEdge(8, 16L))
        6 -> sequenceOf(DirectedEdge(3, 18L), DirectedEdge(5, 30L), DirectedEdge(7, 5L))
        7 -> sequenceOf(DirectedEdge(5, 20L), DirectedEdge(8, 44L))
        8 -> emptySequence()
        else -> throw IllegalArgumentException("Unexpected node: $node")
    }
}
