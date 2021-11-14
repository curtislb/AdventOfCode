package com.curtislb.adventofcode.common.collection

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests [forEachNested].
 */
class ForEachNestedTest {
    @Test
    fun testWithEmptyList() {
        val processed = mutableListOf<List<Pair<Int, Any>>>()
        val process = { indexedItems: List<Pair<Int, Any>> ->
            processed.add(indexedItems)
            false
        }

        emptyList<Any>().forEachNested(levelCount = 4, overlapIndices = true, process)
        emptyList<Any>().forEachNested(levelCount = 0, overlapIndices = false, process)

        assertEquals(emptyList(), processed)
    }

    @Test
    fun testWithInvalidLevelCount() {
        assertThrows<IllegalArgumentException> {
            emptyList<Any>().forEachNested(levelCount = 1, overlapIndices = false) { false }
        }
        assertThrows<IllegalArgumentException> {
            listOf("foo").forEachNested(levelCount = 2, overlapIndices = false) { false }
        }
        assertThrows<IllegalArgumentException> {
            listOf("bar", "baz").forEachNested(levelCount = 5, overlapIndices = false) { false }
        }
    }

    @Test
    fun testWithOverlappingIndices() {
        var processed = mutableListOf<List<Pair<Int, String>>>()
        val process = { indexedItems: List<Pair<Int, String>> ->
            processed.add(indexedItems)
            false
        }

        processed = mutableListOf()
        listOf("alpha").forEachNested(levelCount = 1, overlapIndices = true, process)
        assertEquals(listOf(List(1) { 0 to "alpha" }), processed)

        processed = mutableListOf()
        listOf("alpha").forEachNested(levelCount = 4, overlapIndices = true, process)
        assertEquals(listOf(List(4) { 0 to "alpha" }), processed)

        processed = mutableListOf()
        listOf("beta", "gamma").forEachNested(levelCount = 1, overlapIndices = true, process)
        assertEquals(listOf(listOf(0 to "beta"), listOf(1 to "gamma")), processed)

        processed = mutableListOf()
        listOf("beta", "gamma").forEachNested(levelCount = 2, overlapIndices = true, process)
        assertEquals(
            listOf(
                List(2) { 0 to "beta" },
                listOf(0 to "beta", 1 to "gamma"),
                listOf(1 to "gamma", 0 to "beta"),
                List(2) { 1 to "gamma" }
            ),
            processed
        )

        processed = mutableListOf()
        listOf("delta", "sigma").forEachNested(levelCount = 3, overlapIndices = true, process)
        assertEquals(
            listOf(
                List(3) { 0 to "delta" },
                listOf(0 to "delta", 0 to "delta", 1 to "sigma"),
                listOf(0 to "delta", 1 to "sigma", 0 to "delta"),
                listOf(0 to "delta", 1 to "sigma", 1 to "sigma"),
                listOf(1 to "sigma", 0 to "delta", 0 to "delta"),
                listOf(1 to "sigma", 0 to "delta", 1 to "sigma"),
                listOf(1 to "sigma", 1 to "sigma", 0 to "delta"),
                List(3) { 1 to "sigma" }
            ),
            processed
        )

        processed = mutableListOf()
        listOf("delta", "sigma").forEachNested(
            levelCount = 3,
            overlapIndices = true
        ) { indexedItems ->
            process(indexedItems)
            processed.size == 5
        }
        assertEquals(
            listOf(
                List(3) { 0 to "delta" },
                listOf(0 to "delta", 0 to "delta", 1 to "sigma"),
                listOf(0 to "delta", 1 to "sigma", 0 to "delta"),
                listOf(0 to "delta", 1 to "sigma", 1 to "sigma"),
                listOf(1 to "sigma", 0 to "delta", 0 to "delta")
            ),
            processed
        )

        processed = mutableListOf()
        listOf("lorem", "ipsum", "dolor").forEachNested(
            levelCount = 2,
            overlapIndices = true,
            process
        )
        assertEquals(
            listOf(
                List(2) { 0 to "lorem" },
                listOf(0 to "lorem", 1 to "ipsum"),
                listOf(0 to "lorem", 2 to "dolor"),
                listOf(1 to "ipsum", 0 to "lorem"),
                List(2) { 1 to "ipsum" },
                listOf(1 to "ipsum", 2 to "dolor"),
                listOf(2 to "dolor", 0 to "lorem"),
                listOf(2 to "dolor", 1 to "ipsum"),
                List(2) { 2 to "dolor" },
            ),
            processed
        )

        processed = mutableListOf()
        listOf("lorem", "ipsum", "dolor").forEachNested(
            levelCount = 2,
            overlapIndices = true
        ) { indexedItems ->
            process(indexedItems)
            processed.size == 4
        }
        assertEquals(
            listOf(
                List(2) { 0 to "lorem" },
                listOf(0 to "lorem", 1 to "ipsum"),
                listOf(0 to "lorem", 2 to "dolor"),
                listOf(1 to "ipsum", 0 to "lorem"),
            ),
            processed
        )
    }

    @Test
    fun testWithNonOverlappingIndices() {
        var processed = mutableListOf<List<Pair<Int, String>>>()
        val process = { indexedItems: List<Pair<Int, String>> ->
            processed.add(indexedItems)
            false
        }

        processed = mutableListOf()
        listOf("alpha").forEachNested(levelCount = 1, overlapIndices = false, process)
        assertEquals(listOf(List(1) { 0 to "alpha" }), processed)

        processed = mutableListOf()
        listOf("beta", "gamma").forEachNested(levelCount = 1, overlapIndices = false, process)
        assertEquals(listOf(listOf(0 to "beta"), listOf(1 to "gamma")), processed)

        processed = mutableListOf()
        listOf("beta", "gamma").forEachNested(levelCount = 2, overlapIndices = false, process)
        assertEquals(listOf(listOf(0 to "beta", 1 to "gamma")), processed)

        processed = mutableListOf()
        listOf("delta", "sigma", "epsilon").forEachNested(
            levelCount = 2,
            overlapIndices = false,
            process
        )
        assertEquals(
            listOf(
                listOf(0 to "delta", 1 to "sigma"),
                listOf(0 to "delta", 2 to "epsilon"),
                listOf(1 to "sigma", 2 to "epsilon")
            ),
            processed
        )

        processed = mutableListOf()
        listOf("lorem", "ipsum", "dolor", "sit", "amet").forEachNested(
            levelCount = 3,
            overlapIndices = false,
            process
        )
        assertEquals(
            listOf(
                listOf(0 to "lorem", 1 to "ipsum", 2 to "dolor"),
                listOf(0 to "lorem", 1 to "ipsum", 3 to "sit"),
                listOf(0 to "lorem", 1 to "ipsum", 4 to "amet"),
                listOf(0 to "lorem", 2 to "dolor", 3 to "sit"),
                listOf(0 to "lorem", 2 to "dolor", 4 to "amet"),
                listOf(0 to "lorem", 3 to "sit", 4 to "amet"),
                listOf(1 to "ipsum", 2 to "dolor", 3 to "sit"),
                listOf(1 to "ipsum", 2 to "dolor", 4 to "amet"),
                listOf(1 to "ipsum", 3 to "sit", 4 to "amet"),
                listOf(2 to "dolor", 3 to "sit", 4 to "amet"),
            ),
            processed
        )

        processed = mutableListOf()
        listOf("lorem", "ipsum", "dolor", "sit", "amet").forEachNested(
            levelCount = 3,
            overlapIndices = false
        ) { indexedItems ->
            process(indexedItems)
            processed.size == 7
        }
        assertEquals(
            listOf(
                listOf(0 to "lorem", 1 to "ipsum", 2 to "dolor"),
                listOf(0 to "lorem", 1 to "ipsum", 3 to "sit"),
                listOf(0 to "lorem", 1 to "ipsum", 4 to "amet"),
                listOf(0 to "lorem", 2 to "dolor", 3 to "sit"),
                listOf(0 to "lorem", 2 to "dolor", 4 to "amet"),
                listOf(0 to "lorem", 3 to "sit", 4 to "amet"),
                listOf(1 to "ipsum", 2 to "dolor", 3 to "sit"),
            ),
            processed
        )
    }
}