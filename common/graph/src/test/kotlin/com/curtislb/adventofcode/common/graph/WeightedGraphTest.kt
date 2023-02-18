package com.curtislb.adventofcode.common.graph

import kotlin.test.assertEquals
import kotlin.test.assertNull
import org.junit.jupiter.api.Test

/**
 * Tests [WeightedGraph].
 */
class WeightedGraphTest {
    /**
     * Sample implementation of a weighted graph with integer nodes.
     */
    object SampleGraph : WeightedGraph<Int>() {
        private val edges = listOf(
            listOf(Edge(2, 26L), Edge(4, 38L)),
            listOf(Edge(3, 29L)),
            listOf(Edge(7, 34L)),
            listOf(Edge(6, 52L)),
            listOf(Edge(5, 35L), Edge(7, 37L)),
            listOf(Edge(1, 32L), Edge(4, 35L), Edge(7, 28L)),
            listOf(Edge(2, 40L), Edge(4, 93L)),
            listOf(Edge(3, 39L), Edge(5, 28L))
        )

        private val heuristics = listOf(
            mapOf(1 to 75L, 2 to 25L, 3 to 75L, 4 to 25L, 5 to 50L, 6 to 100L, 7 to 50L),
            mapOf(2 to 75L, 3 to 25L, 4 to 75L, 5 to 100L, 6 to 50L, 7 to 100L),
            mapOf(1 to 75L, 3 to 50L, 4 to 75L, 5 to 50L, 6 to 75L, 7 to 25L),
            mapOf(1 to 100L, 2 to 50L, 4 to 50L, 5 to 75L, 6 to 25L, 7 to 75L),
            mapOf(1 to 50L, 2 to 100L, 3 to 50L, 5 to 25L, 6 to 75L, 7 to 25L),
            mapOf(1 to 25L, 2 to 100L, 3 to 50L, 4 to 25L, 6 to 75L, 7 to 25L),
            mapOf(1 to 75L, 2 to 25L, 3 to 75L, 4 to 25L, 5 to 50L, 7 to 50L),
            mapOf(1 to 50L, 2 to 75L, 3 to 25L, 4 to 50L, 5 to 25L, 6 to 50L),
        )

        override fun getEdges(node: Int): Iterable<Edge<Int>> = edges[node]

        /**
         * Returns an admissible heuristic value for the distance from [source] to [goal].
         */
        fun heuristic(source: Int, goal: Int): Long =
            if (source == goal) 0 else heuristics[source][goal] ?: 500L
    }

    @Test
    fun aStarDistance_toSource() {
        for (source in 0..7) {
            val distance = SampleGraph.aStarDistance(
                source = source,
                heuristic = { SampleGraph.heuristic(it, source) },
                isGoal = { it == source }
            )
            assertEquals(0L, distance)
        }
    }

    @Test
    fun aStarDistance_toNonexistentNode() {
        for (source in 0..7) {
            val distance = SampleGraph.aStarDistance(
                source = source,
                heuristic = { SampleGraph.heuristic(it, 8) },
                isGoal = { it == 8 }
            )
            assertNull(distance)
        }
    }

    @Test
    fun aStarDistance_toUnreachableNode() {
        val distance = SampleGraph.aStarDistance(
            source = 2,
            heuristic = { SampleGraph.heuristic(it, 0) },
            isGoal = { it == 0 }
        )
        assertNull(distance)
    }

    @Test
    fun aStarDistance_toReachableNode() {
        val distance = SampleGraph.aStarDistance(
            source = 0,
            heuristic = { SampleGraph.heuristic(it, 6) },
            isGoal = { it == 6 }
        )
        assertEquals(151L, distance)
    }

    @Test
    fun dijkstraDistance_toSource() {
        for (source in 0..7) {
            val distance = SampleGraph.dijkstraDistance(source) { it == source }
            assertEquals(0L, distance)
        }
    }

    @Test
    fun dijkstraDistance_toNonexistentNode() {
        for (source in 0..7) {
            val distance = SampleGraph.dijkstraDistance(source) { it == 8 }
            assertNull(distance)
        }
    }

    @Test
    fun dijkstraDistance_toUnreachableNode() {
        val distance = SampleGraph.dijkstraDistance(source = 2) { it == 0 }
        assertNull(distance)
    }

    @Test
    fun dijkstraDistance_toReachableNode() {
        val distance = SampleGraph.dijkstraDistance(source = 0) { it == 6 }
        assertEquals(151L, distance)
    }
}
