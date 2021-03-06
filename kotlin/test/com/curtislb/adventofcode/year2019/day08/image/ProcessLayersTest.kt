package com.curtislb.adventofcode.year2019.day08.image

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File
import kotlin.test.assertEquals

/**
 * Tests [processLayers].
 */
class ProcessLayersTest {
    @get:Rule val temporaryFolder = TemporaryFolder()

    private lateinit var file: File

    @Before
    fun setUp() {
        file = temporaryFolder.newFile()
    }

    @Test
    fun testProcessLayersForEmptyFile() {
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
    fun testProcessLayersForOneLayerOnePixelImage() {
        file.writeText("1")
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
    fun testProcessLayersWithOnDigitCallbackOnly() {
        file.writeText("1")
        val calls = mutableListOf<String>()
        processLayers(
            file,
            imageArea = 1,
            onDigit = { indexInLayer, digit -> calls.add("onDigit($indexInLayer, $digit)") }
        )
        assertEquals(listOf("onDigit(0, 1)"), calls)
    }

    @Test
    fun testProcessLayersWithOnLayerFinishedCallbackOnly() {
        file.writeText("1")
        val calls = mutableListOf<String>()
        processLayers(file, imageArea = 1, onLayerFinished = { calls.add("onLayerFinished") })
        assertEquals(listOf("onLayerFinished"), calls)
    }

    @Test
    fun testProcessLayersForOneLayerImage() {
        file.writeText("010222110201")
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
    fun testProcessLayersForTwoLayerOnePixelImage() {
        file.writeText("21")
        val calls = mutableListOf<String>()
        processLayers(
            file,
            imageArea = 1,
            onDigit = { indexInLayer, digit -> calls.add("onDigit($indexInLayer, $digit)") },
            onLayerFinished = { calls.add("onLayerFinished") }
        )
        assertEquals(listOf("onDigit(0, 2)", "onLayerFinished", "onDigit(0, 1)", "onLayerFinished"), calls)
    }
}
