package com.curtislb.adventofcode.year2019.day08.image

import com.curtislb.adventofcode.common.testing.createTempFile
import java.nio.file.Path
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir

/**
 * Tests [processLayers].
 */
class ProcessLayersTest {
    @Test
    fun testProcessLayersForEmptyFile(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile()
        val calls = mutableListOf<String>()
        processLayers(
            file,
            imageArea = 1,
            onDigit = { indexInLayer, digit -> calls.add("onDigit($indexInLayer, $digit)") },
            onLayerFinished = { calls.add("onLayerFinished") }
        )
        assertEquals(emptyList(), calls.toList())
    }

    @Test
    fun testProcessLayersForOneLayerOnePixelImage(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "1")
        val calls = mutableListOf<String>()
        processLayers(
            file,
            imageArea = 1,
            onDigit = { indexInLayer, digit -> calls.add("onDigit($indexInLayer, $digit)") },
            onLayerFinished = { calls.add("onLayerFinished") }
        )
        assertEquals(listOf("onDigit(0, 1)", "onLayerFinished"), calls)
    }

    @Test
    fun testProcessLayersWithOnDigitCallbackOnly(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "1")
        val calls = mutableListOf<String>()
        processLayers(
            file,
            imageArea = 1,
            onDigit = { indexInLayer, digit -> calls.add("onDigit($indexInLayer, $digit)") }
        )
        assertEquals(listOf("onDigit(0, 1)"), calls)
    }

    @Test
    fun testProcessLayersWithOnLayerFinishedCallbackOnly(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "1")
        val calls = mutableListOf<String>()
        processLayers(file, imageArea = 1, onLayerFinished = { calls.add("onLayerFinished") })
        assertEquals(listOf("onLayerFinished"), calls)
    }

    @Test
    fun testProcessLayersForOneLayerImage(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "010222110201")
        val calls = mutableListOf<String>()
        processLayers(
            file,
            imageArea = 6,
            onDigit = { indexInLayer, digit -> calls.add("onDigit($indexInLayer, $digit)") },
            onLayerFinished = { calls.add("onLayerFinished") }
        )
        assertEquals(
            listOf(
                "onDigit(0, 0)",
                "onDigit(1, 1)",
                "onDigit(2, 0)",
                "onDigit(3, 2)",
                "onDigit(4, 2)",
                "onDigit(5, 2)",
                "onLayerFinished",
                "onDigit(0, 1)",
                "onDigit(1, 1)",
                "onDigit(2, 0)",
                "onDigit(3, 2)",
                "onDigit(4, 0)",
                "onDigit(5, 1)",
                "onLayerFinished"
            ),
            calls.toList()
        )
    }

    @Test
    fun testProcessLayersForTwoLayerOnePixelImage(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "21")
        val calls = mutableListOf<String>()
        processLayers(
            file,
            imageArea = 1,
            onDigit = { indexInLayer, digit -> calls.add("onDigit($indexInLayer, $digit)") },
            onLayerFinished = { calls.add("onLayerFinished") }
        )
        assertEquals(
            listOf("onDigit(0, 2)", "onLayerFinished", "onDigit(0, 1)", "onLayerFinished"),
            calls
        )
    }
}
