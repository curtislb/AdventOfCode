package com.curtislb.adventofcode.common.graph

import com.curtislb.adventofcode.common.testing.assertContainsExactly
import kotlin.test.assertEquals
import kotlin.test.assertNull
import org.junit.jupiter.api.Test

/**
 * Tests [UnweightedGraph].
 */
class UnweightedGraphTest {
    @Test
    fun bfsApply_withSourceOnly() {
        for (source in 0..12) {
            val appliedList = mutableListOf<Pair<Int, Long>>()
            UnweightedGraphImpl.bfsApply(source) { node, distance ->
                appliedList.add(Pair(node, distance))
                true // Stop searching
            }
            assertEquals(listOf(Pair(source, 0L)), appliedList)
        }
    }

    @Test
    fun bfsApply_withSourceAndNeighbors() {
        val appliedList = mutableListOf<Pair<Int, Long>>()
        UnweightedGraphImpl.bfsApply(source = 0) { node, distance ->
            if (distance <= 1L) {
                appliedList.add(Pair(node, distance))
                false // Keep searching
            } else {
                true // Stop searching
            }
        }

        val expected = listOf(Pair(0, 0L), Pair(1, 1L), Pair(5, 1L))
        assertContainsExactly(expected, appliedList)
    }

    @Test
    fun bfsApply_withAllNodes_inSubgraph() {
        val appliedList = mutableListOf<Pair<Int, Long>>()
        UnweightedGraphImpl.bfsApply(source = 0) { node, distance ->
            appliedList.add(Pair(node, distance))
            false // Don't stop searching
        }

        val expected = listOf(
            Pair(0, 0L),
            Pair(1, 1L),
            Pair(5, 1L),
            Pair(4, 2L),
            Pair(2, 3L),
            Pair(3, 3L),
        )
        assertContainsExactly(expected, appliedList)
    }

    @Test
    fun bfsApply_withAllNodes_inFullGraph() {
        val appliedList = mutableListOf<Pair<Int, Long>>()
        UnweightedGraphImpl.bfsApply(source = 7) { node, distance ->
            appliedList.add(Pair(node, distance))
            false // Don't stop searching
        }

        val expected = listOf(
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
            Pair(12, 3L),
        )
        assertContainsExactly(expected, appliedList)
    }

    @Test
    fun bfsDistance_toSource() {
        for (source in 0..12) {
            val distance = UnweightedGraphImpl.bfsDistance(source) { it == source }
            assertEquals(0L, distance)
        }
    }

    @Test
    fun bfsDistance_toNonexistentNode() {
        for (source in 0..12) {
            val distance = UnweightedGraphImpl.bfsDistance(source) { it == 13 }
            assertNull(distance)
        }
    }

    @Test
    fun bfsDistance_toUnreachableNode() {
        val distance = UnweightedGraphImpl.bfsDistance(source = 0) { it == 6 }
        assertNull(distance)
    }

    @Test
    fun bfsDistance_toReachableNode() {
        val distance = UnweightedGraphImpl.bfsDistance(source = 7) { it == 2 }
        assertEquals(3L, distance)
    }

    @Test
    fun dfsPaths_toSource() {
        for (source in 0..12) {
            val paths = UnweightedGraphImpl.dfsPaths(source) { it == source }
            assertEquals(mapOf(source to listOf(emptyList())), paths)
        }
    }

    @Test
    fun dfsDistance_toNonexistentNode() {
        for (source in 0..12) {
            val paths = UnweightedGraphImpl.dfsPaths(source) { it == 13 }
            assertEquals(emptyMap(), paths)
        }
    }

    @Test
    fun dfsPaths_toAllNodes_inSubgraph() {
        val paths = UnweightedGraphImpl.dfsPaths(source = 0) { true }
        assertContainsExactly((0..5).toList(), paths.keys)
        assertEquals(listOf(emptyList()), paths[0]!!)
        assertEquals(listOf(listOf(1)), paths[1]!!)
        assertContainsExactly(listOf(listOf(5, 4, 2), listOf(5, 4, 3, 2)), paths[2]!!)
        assertContainsExactly(listOf(listOf(5, 4, 3), listOf(5, 4, 2, 3)), paths[3]!!)
        assertEquals(listOf(listOf(5, 4)), paths[4]!!)
        assertEquals(listOf(listOf(5)), paths[5]!!)
    }

    @Test
    fun dfsPaths_toAllNodes_inFullGraph() {
        val paths = UnweightedGraphImpl.dfsPaths(source = 7) { true }
        assertContainsExactly((0..12).toList(), paths.keys)
        assertContainsExactly(
            listOf(
                listOf(6, 0),
                listOf(6, 4, 2, 0),
                listOf(6, 4, 3, 2, 0),
                listOf(6, 9, 11, 4, 2, 0),
                listOf(6, 9, 11, 4, 3, 2, 0),
                listOf(9, 11, 4, 2, 0),
                listOf(9, 11, 4, 3, 2, 0)
            ),
            paths[0]!!
        )
        assertContainsExactly(
            listOf(
                listOf(6, 0, 1),
                listOf(6, 4, 2, 0, 1),
                listOf(6, 4, 3, 2, 0, 1),
                listOf(6, 9, 11, 4, 2, 0, 1),
                listOf(6, 9, 11, 4, 3, 2, 0, 1),
                listOf(9, 11, 4, 2, 0, 1),
                listOf(9, 11, 4, 3, 2, 0, 1)
            ),
            paths[1]!!
        )
        assertContainsExactly(
            listOf(
                listOf(6, 0, 5, 4, 2),
                listOf(6, 0, 5, 4, 3, 2),
                listOf(6, 4, 2),
                listOf(6, 4, 3, 2),
                listOf(6, 9, 11, 4, 2),
                listOf(6, 9, 11, 4, 3, 2),
                listOf(9, 11, 4, 2),
                listOf(9, 11, 4, 3, 2)
            ),
            paths[2]!!
        )
        assertContainsExactly(
            listOf(
                listOf(6, 0, 5, 4, 2, 3),
                listOf(6, 0, 5, 4, 3),
                listOf(6, 4, 2, 3),
                listOf(6, 4, 3),
                listOf(6, 9, 11, 4, 2, 3),
                listOf(6, 9, 11, 4, 3),
                listOf(9, 11, 4, 2, 3),
                listOf(9, 11, 4, 3)
            ),
            paths[3]!!
        )
        assertContainsExactly(
            listOf(
                listOf(6, 0, 5, 4),
                listOf(6, 4),
                listOf(6, 9, 11, 4),
                listOf(9, 11, 4),
            ),
            paths[4]!!
        )
        assertContainsExactly(
            listOf(
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
            ),
            paths[5]!!
        )
        assertEquals(listOf(listOf(6)), paths[6]!!)
        assertEquals(listOf(emptyList()), paths[7]!!)
        assertEquals(listOf(listOf(6, 8)), paths[8]!!)
        assertContainsExactly(listOf(listOf(6, 9), listOf(9)), paths[9]!!)
        assertContainsExactly(listOf(listOf(6, 9, 10), listOf(9, 10)), paths[10]!!)
        assertContainsExactly(listOf(listOf(6, 9, 11), listOf(9, 11)), paths[11]!!)
        assertContainsExactly(
            listOf(
                listOf(6, 9, 10, 12),
                listOf(6, 9, 11, 12),
                listOf(9, 10, 12),
                listOf(9, 11, 12)
            ),
            paths[12]!!
        )
    }
}

/**
 * Sample implementation of an unweighted graph with integer nodes.
 */
private object UnweightedGraphImpl : UnweightedGraph<Int>() {
    private val adjacencyList = listOf(
        listOf(1, 5),
        emptyList(),
        listOf(0, 3),
        listOf(2, 5),
        listOf(2, 3),
        listOf(4),
        listOf(0, 4, 8, 9),
        listOf(6, 9),
        listOf(6),
        listOf(10, 11),
        listOf(12),
        listOf(4, 12),
        listOf(9),
    )

    override fun getNeighbors(node: Int): Iterable<Int> = adjacencyList[node]
}
