package com.curtislb.adventofcode.common.io

import com.curtislb.adventofcode.common.testing.createTempFile
import java.nio.file.Path
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir

/**
 * Tests [mapLines].
 */
class MapLinesTest {
    @Test
    fun testWithEmptyFile(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile()
        val mappedLines = file.mapLines { it.uppercase() }
        assertEquals(emptyList(), mappedLines)
    }

    @Test
    fun testWithSingleLineFile(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "Lorem ipsum")
        val mappedLines = file.mapLines { it.uppercase() }
        assertEquals(listOf("LOREM IPSUM"), mappedLines)
    }

    @Test
    fun testWithMultiLineFile(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "dolor \n Sit amet.\n")
        val mappedLines = file.mapLines { it.uppercase() }
        assertEquals(listOf("DOLOR ", " SIT AMET."), mappedLines)
    }
}
