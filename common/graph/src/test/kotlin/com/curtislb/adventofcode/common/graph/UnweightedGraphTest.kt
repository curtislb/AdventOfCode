package com.curtislb.adventofcode.common.graph

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests the [UnweightedGraph] class.
 */
class UnweightedGraphTest {
    @Test
    fun bfsApply_toSourceOnly() {
        val source = 0
        val pairs = mutableListOf<Pair<Int, Long>>()

        UnweightedGraphImpl.bfsApply(source) { node, distance ->
            pairs.add(Pair(node, distance))
            true // Stop searching
        }

        assertThat(pairs).containsExactly(Pair(source, 0L))
    }

    @Test
    fun bfsApply_toSourceAndNeighbors() {
        val source = 0
        val pairs = mutableListOf<Pair<Int, Long>>()

        UnweightedGraphImpl.bfsApply(source) { node, distance ->
            if (distance <= 1L) {
                pairs.add(Pair(node, distance))
                false // Keep searching
            } else {
                true // Stop searching
            }
        }

        assertThat(pairs).containsExactlyInAnyOrder(Pair(0, 0L), Pair(1, 1L), Pair(5, 1L))
    }

    @Test
    fun bfsApply_toAllNodes_inSubgraph() {
        val source = 0
        val pairs = mutableListOf<Pair<Int, Long>>()

        UnweightedGraphImpl.bfsApply(source) { node, distance ->
            pairs.add(Pair(node, distance))
            false // Keep searching
        }
        
        assertThat(pairs).containsExactlyInAnyOrder(
            Pair(0, 0L),
            Pair(1, 1L),
            Pair(5, 1L),
            Pair(4, 2L),
            Pair(2, 3L),
            Pair(3, 3L)
        )
    }

    @Test
    fun bfsApply_toAllNodes_inFullGraph() {
        val source = 7
        val pairs = mutableListOf<Pair<Int, Long>>()
        
        UnweightedGraphImpl.bfsApply(source) { node, distance ->
            pairs.add(Pair(node, distance))
            false // Keep searching
        }

        assertThat(pairs).containsExactlyInAnyOrder(
            Pair(7, 0L),
            Pair(6, 1L),
            Pair(9, 1L),
            Pair(0, 2L),
            Pair(4, 2L),
            Pair(8, 2L),
            Pair(10, 2L),
            Pair(11, 2L),
            Pair(1, 3L),
            Pair(2, 3L),
            Pair(3, 3L),
            Pair(5, 3L),
            Pair(12, 3L)
        )
    }

    @Test
    fun bfsDistance_toSource() {
        val source = 0
        val distance = UnweightedGraphImpl.bfsDistance(source) { it == source }
        assertThat(distance).isEqualTo(0L)
    }

    @Test
    fun bfsDistance_toNonexistentNode() {
        val source = 0
        val goal = 13
        val distance = UnweightedGraphImpl.bfsDistance(source) { it == goal }
        assertThat(distance).isNull()
    }

    @Test
    fun bfsDistance_toUnreachableNode() {
        val source = 0
        val goal = 6
        val distance = UnweightedGraphImpl.bfsDistance(source) { it == goal }
        assertThat(distance).isNull()
    }

    @Test
    fun bfsDistance_toReachableNode() {
        val source = 7
        val goal = 2
        val distance = UnweightedGraphImpl.bfsDistance(source) { it == goal }
        assertThat(distance).isEqualTo(3L)
    }

    @Test
    fun dfsPaths_toSource() {
        val source = 0
        val paths = UnweightedGraphImpl.dfsPaths(source) { it == source }
        assertThat(paths).containsExactlyEntriesOf(mapOf(source to listOf(emptyList())))
    }

    @Test
    fun dfsPaths_toNonexistentNode() {
        val source = 0
        val goal = 13
        val paths = UnweightedGraphImpl.dfsPaths(source) { it == goal }
        assertThat(paths).isEmpty()
    }

    @Test
    fun dfsPaths_toUnreachableNode() {
        val source = 0
        val goal = 6
        val paths = UnweightedGraphImpl.dfsPaths(source) { it == goal }
        assertThat(paths).isEmpty()
    }

    @Test
    fun dfsPaths_toAllNodes_inSubgraph() {
        val source = 0
        val paths = UnweightedGraphImpl.dfsPaths(source) { true }
        assertThat(paths.keys).containsExactlyInAnyOrder(0, 1, 2, 3, 4, 5)
        assertThat(paths[0]).containsExactly(emptyList())
        assertThat(paths[1]).containsExactly(listOf(1))
        assertThat(paths[2]).containsExactlyInAnyOrder(listOf(5, 4, 2), listOf(5, 4, 3, 2))
        assertThat(paths[3]).containsExactlyInAnyOrder(listOf(5, 4, 3), listOf(5, 4, 2, 3))
        assertThat(paths[4]).containsExactly(listOf(5, 4))
        assertThat(paths[5]).containsExactly(listOf(5))
    }

    @Test
    fun dfsPaths_toAllNodes_inFullGraph() {
        val source = 7
        val paths = UnweightedGraphImpl.dfsPaths(source) { true }
        assertThat(paths.keys).containsExactlyInAnyOrder(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
        assertThat(paths[0]).containsExactlyInAnyOrder(
            listOf(6, 0),
            listOf(6, 4, 2, 0),
            listOf(6, 4, 3, 2, 0),
            listOf(6, 9, 11, 4, 2, 0),
            listOf(6, 9, 11, 4, 3, 2, 0),
            listOf(9, 11, 4, 2, 0),
            listOf(9, 11, 4, 3, 2, 0)
        )
        assertThat(paths[1]).containsExactlyInAnyOrder(
            listOf(6, 0, 1),
            listOf(6, 4, 2, 0, 1),
            listOf(6, 4, 3, 2, 0, 1),
            listOf(6, 9, 11, 4, 2, 0, 1),
            listOf(6, 9, 11, 4, 3, 2, 0, 1),
            listOf(9, 11, 4, 2, 0, 1),
            listOf(9, 11, 4, 3, 2, 0, 1)
        )
        assertThat(paths[2]).containsExactlyInAnyOrder(
            listOf(6, 0, 5, 4, 2),
            listOf(6, 0, 5, 4, 3, 2),
            listOf(6, 4, 2),
            listOf(6, 4, 3, 2),
            listOf(6, 9, 11, 4, 2),
            listOf(6, 9, 11, 4, 3, 2),
            listOf(9, 11, 4, 2),
            listOf(9, 11, 4, 3, 2)
        )
        assertThat(paths[3]).containsExactlyInAnyOrder(
            listOf(6, 0, 5, 4, 2, 3),
            listOf(6, 0, 5, 4, 3),
            listOf(6, 4, 2, 3),
            listOf(6, 4, 3),
            listOf(6, 9, 11, 4, 2, 3),
            listOf(6, 9, 11, 4, 3),
            listOf(9, 11, 4, 2, 3),
            listOf(9, 11, 4, 3)
        )
        assertThat(paths[4]).containsExactlyInAnyOrder(
            listOf(6, 0, 5, 4),
            listOf(6, 4),
            listOf(6, 9, 11, 4),
            listOf(9, 11, 4)
        )
        assertThat(paths[5]).containsExactlyInAnyOrder(
            listOf(6, 0, 5),
            listOf(6, 4, 2, 0, 5),
            listOf(6, 4, 2, 3, 5),
            listOf(6, 4, 3, 2, 0, 5),
            listOf(6, 4, 3, 5),
            listOf(6, 9, 11, 4, 2, 0, 5),
            listOf(6, 9, 11, 4, 2, 3, 5),
            listOf(6, 9, 11, 4, 3, 2, 0, 5),
            listOf(6, 9, 11, 4, 3, 5),
            listOf(9, 11, 4, 2, 0, 5),
            listOf(9, 11, 4, 2, 3, 5),
            listOf(9, 11, 4, 3, 2, 0, 5),
            listOf(9, 11, 4, 3, 5)
        )
        assertThat(paths[6]).containsExactly(listOf(6))
        assertThat(paths[7]).containsExactly(emptyList())
        assertThat(paths[8]).containsExactly(listOf(6, 8))
        assertThat(paths[9]).containsExactlyInAnyOrder(listOf(6, 9), listOf(9))
        assertThat(paths[10]).containsExactlyInAnyOrder(listOf(6, 9, 10), listOf(9, 10))
        assertThat(paths[11]).containsExactlyInAnyOrder(listOf(6, 9, 11), listOf(9, 11))
        assertThat(paths[12]).containsExactlyInAnyOrder(
            listOf(6, 9, 10, 12),
            listOf(6, 9, 11, 12),
            listOf(9, 10, 12),
            listOf(9, 11, 12)
        )
    }
}

/**
 * Sample implementation of an unweighted graph with integer nodes.
 */
private object UnweightedGraphImpl : UnweightedGraph<Int>() {
    private val adjacencyList = listOf(
        listOf(1, 5),       // 0
        emptyList(),        // 1
        listOf(0, 3),       // 2
        listOf(2, 5),       // 3
        listOf(2, 3),       // 4
        listOf(4),          // 5
        listOf(0, 4, 8, 9), // 6
        listOf(6, 9),       // 7
        listOf(6),          // 8
        listOf(10, 11),     // 9
        listOf(12),         // 10
        listOf(4, 12),      // 11
        listOf(9)           // 12
    )

    override fun getNeighbors(node: Int): Iterable<Int> = adjacencyList[node]
}
