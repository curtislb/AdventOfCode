package com.curtislb.adventofcode.common.graph

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests the [WeightedGraph] class.
 */
class WeightedGraphTest {
    @Test
    fun aStarDistance_toSource() {
        val source = 0

        val distance = WeightedGraphImpl.aStarDistance(
            source = source,
            heuristic = { WeightedGraphImpl.heuristic(it, source) },
            isGoal = { it == source }
        )

        assertThat(distance).isEqualTo(0L)
    }

    @Test
    fun aStarDistance_toNonexistentNode() {
        val source = 0
        val goal = 8

        val distance = WeightedGraphImpl.aStarDistance(
            source = source,
            heuristic = { WeightedGraphImpl.heuristic(it, goal) },
            isGoal = { it == goal }
        )

        assertThat(distance).isNull()
    }

    @Test
    fun aStarDistance_toUnreachableNode() {
        val source = 2
        val goal = 0

        val distance = WeightedGraphImpl.aStarDistance(
            source = source,
            heuristic = { WeightedGraphImpl.heuristic(it, goal) },
            isGoal = { it == goal }
        )

        assertThat(distance).isNull()
    }

    @Test
    fun aStarDistance_toReachableNode() {
        val source = 0
        val goal = 6

        val distance = WeightedGraphImpl.aStarDistance(
            source = source,
            heuristic = { WeightedGraphImpl.heuristic(it, goal) },
            isGoal = { it == goal }
        )

        assertThat(distance).isEqualTo(151L)
    }

    @Test
    fun dijkstraDistance_toSource() {
        val source = 0
        val distance = WeightedGraphImpl.dijkstraDistance(source) { it == source }
        assertThat(distance).isEqualTo(0L)
    }

    @Test
    fun dijkstraDistance_toNonexistentNode() {
        val source = 0
        val goal = 8

        val distance = WeightedGraphImpl.dijkstraDistance(source) { it == goal }

        assertThat(distance).isNull()
    }

    @Test
    fun dijkstraDistance_toUnreachableNode() {
        val source = 2
        val goal = 0

        val distance = WeightedGraphImpl.dijkstraDistance(source) { it == goal }

        assertThat(distance).isNull()
    }

    @Test
    fun dijkstraDistance_toReachableNode() {
        val source = 0
        val goal = 6

        val distance = WeightedGraphImpl.dijkstraDistance(source) { it == goal }

        assertThat(distance).isEqualTo(151L)
    }
}

/**
 * Sample implementation of a weighted graph with integer nodes.
 */
private object WeightedGraphImpl : WeightedGraph<Int>() {
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
        if (source == goal) 0L else heuristics[source][goal] ?: 500L
}
