package com.curtislb.adventofcode.common.graph

import kotlin.test.assertEquals
import kotlin.test.assertNull
import org.junit.jupiter.api.Test

/**
 * Tests [Dijkstra].
 */
class DijkstraTest {
    @Test
    fun testShortestDistanceToSource() {
        for (source in 1..8) {
            val distance = SampleDijkstra.findShortestDistance(source) { it == source }
            assertEquals(0L, distance)
        }
    }

    @Test
    fun testShortestDistanceToNonexistentNode() {
        for (source in 1..8) {
            val distance = SampleDijkstra.findShortestDistance(source) { it == 9 }
            assertNull(distance)
        }
    }

    @Test
    fun testShortestDistanceToUnreachableNode() {
        val distance = SampleDijkstra.findShortestDistance(source = 5) { it == 7 }
        assertNull(distance)
    }

    @Test
    fun testShortestDistanceToReachableNodes() {
        val source = 1
        assertEquals(9L, SampleDijkstra.findShortestDistance(source) { it == 2 })
        assertEquals(32L, SampleDijkstra.findShortestDistance(source) { it == 3 })
        assertEquals(45L, SampleDijkstra.findShortestDistance(source) { it == 4 })
        assertEquals(34L, SampleDijkstra.findShortestDistance(source) { it == 5 })
        assertEquals(14L, SampleDijkstra.findShortestDistance(source) { it == 6 })
        assertEquals(15L, SampleDijkstra.findShortestDistance(source) { it == 7 })
        assertEquals(50L, SampleDijkstra.findShortestDistance(source) { it == 8 })
    }

    /**
     * Implementation using a sample graph with weighted edges for testing.
     */
    private object SampleDijkstra : Dijkstra<Int>() {
        override fun getEdges(node: Int): Iterable<DirectedEdge<Int>> = when (node) {
            1 -> listOf(DirectedEdge(2, 9L), DirectedEdge(6, 14L), DirectedEdge(7, 15L))
            2 -> listOf(DirectedEdge(3, 24L))
            3 -> listOf(DirectedEdge(5, 2L), DirectedEdge(8, 19L))
            4 -> listOf(DirectedEdge(3, 6L), DirectedEdge(8, 6L))
            5 -> listOf(DirectedEdge(4, 11L), DirectedEdge(8, 16L))
            6 -> listOf(DirectedEdge(3, 18L), DirectedEdge(5, 30L), DirectedEdge(7, 5L))
            7 -> listOf(DirectedEdge(5, 20L), DirectedEdge(8, 44L))
            8 -> emptyList()
            else -> throw IllegalArgumentException("Unexpected node: $node")
        }
    }
}
