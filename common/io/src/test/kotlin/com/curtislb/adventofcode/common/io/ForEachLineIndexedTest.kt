package com.curtislb.adventofcode.common.io

import com.curtislb.adventofcode.common.testing.createTempFile
import java.nio.file.Path
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir

/**
 * Tests [forEachLineIndexed].
 */
class ForEachLineIndexedTest {
    private lateinit var linePairs: MutableList<Pair<Int, String>>

    @BeforeEach
    fun setUp() {
        linePairs = mutableListOf()
    }

    @Test
    fun testWithEmptyFile(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile()
        file.forEachLineIndexed { index, line -> linePairs.add(Pair(index, line)) }
        assertTrue(linePairs.isEmpty())
    }

    @Test
    fun testWithSingleLineFile(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "World!")
        file.forEachLineIndexed { index, line -> linePairs.add(Pair(index, line)) }
        assertEquals(listOf(Pair(0, "World!")), linePairs.toList())
    }

    @Test
    fun testWithMultiLineFile(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "Lorem\nipsum \n dolor sit\n\namet.\n")
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
