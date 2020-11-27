package com.curtislb.adventofcode.common.io

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Tests [forEachLineIndexed].
 */
class ForEachLineIndexedTest {
    @get:Rule val temporaryFolder = TemporaryFolder()

    private lateinit var file: File
    private lateinit var linePairs: MutableList<Pair<Int, String>>

    @Before
    fun setUp() {
        file = temporaryFolder.newFile()
        linePairs = mutableListOf()
    }

    @Test
    fun testWithEmptyFile() {
        file.forEachLineIndexed { index, line -> linePairs.add(Pair(index, line)) }
        assertTrue(linePairs.isEmpty())
    }

    @Test
    fun testWithSingleLineFile() {
        file.writeText("World!")
        file.forEachLineIndexed { index, line -> linePairs.add(Pair(index, line)) }
        assertEquals(listOf(Pair(0, "World!")), linePairs.toList())
    }

    @Test
    fun testWithMultiLineFile() {
        file.writeText("Lorem\nipsum \n dolor sit\n\namet.\n")
        file.forEachLineIndexed { index, line -> linePairs.add(Pair(index, line)) }
        assertEquals(
            listOf(
                Pair(0, "Lorem"),
                Pair(1, "ipsum "),
                Pair(2, " dolor sit"),
                Pair(3, ""),
                Pair(4, "amet.")
            ),
            linePairs.toList()
        )
    }
}
