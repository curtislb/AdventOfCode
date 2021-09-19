package com.curtislb.adventofcode.common.io

import com.curtislb.adventofcode.common.testing.createTempFile
import java.nio.file.Path
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir

/**
 * Tests [forEachChar].
 */
class ForEachCharTest {
    private lateinit var chars: MutableList<Char>

    @BeforeEach
    fun setUp() {
        chars = mutableListOf()
    }

    @Test
    fun testWithEmptyFile(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile()
        file.forEachChar { chars.add(it) }
        assertTrue(chars.isEmpty())
    }

    @Test
    fun testWithSingleLineFile(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "Hello!")
        file.forEachChar { chars.add(it) }
        assertEquals(listOf('H', 'e', 'l', 'l', 'o', '!'), chars.toList())
    }

    @Test
    fun testWithMultiLineFile(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "foo\nBar \n")
        file.forEachChar { chars.add(it) }
        assertEquals(listOf('f', 'o', 'o', '\n', 'B', 'a', 'r', ' ', '\n'), chars.toList())
    }
}
