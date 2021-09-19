package com.curtislb.adventofcode.common.io

import com.curtislb.adventofcode.common.testing.createTempFile
import java.nio.file.Path
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir

/**
 * Tests [readIntRange].
 */
class ReadIntRangeTest {
    @Test
    fun testWithCharSeparator(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "66-932")
        assertEquals(66..932, file.readIntRange())
    }

    @Test
    fun testWithSpaceSeparator(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "-24 19")
        assertEquals(-24..19, file.readIntRange())
    }

    @Test
    fun testWithStringSeparator(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "-733<=>821\n")
        assertEquals(-733..821, file.readIntRange())
    }
}
