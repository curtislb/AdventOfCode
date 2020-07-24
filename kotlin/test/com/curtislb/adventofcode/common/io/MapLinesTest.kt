package com.curtislb.adventofcode.common.io

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Tests [mapLines].
 */
class MapLinesTest {
    @get:Rule val temporaryFolder = TemporaryFolder()

    private lateinit var file: File

    @Before fun setUp() {
        file = temporaryFolder.newFile()
    }

    @Test fun testWithEmptyFile() {
        file.mapLines { it.toUpperCase() }
        val mappedLines = file.mapLines { it.toUpperCase() }
        assertEquals(emptyList(), mappedLines)
    }

    @Test fun testWithSingleLineFile() {
        file.writeText("Lorem ipsum")
        val mappedLines = file.mapLines { it.toUpperCase() }
        assertEquals(listOf("LOREM IPSUM"), mappedLines)
    }

    @Test fun testWithMultiLineFile() {
        file.writeText("dolor \n Sit amet.\n")
        val mappedLines = file.mapLines { it.toUpperCase() }
        assertEquals(listOf("DOLOR ", " SIT AMET."), mappedLines)
    }
}
