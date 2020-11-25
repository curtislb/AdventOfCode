package com.curtislb.adventofcode.common.io

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Tests [forEachChar].
 */
class ForEachCharTest {
    @get:Rule val temporaryFolder = TemporaryFolder()

    private lateinit var file: File
    private lateinit var chars: MutableList<Char>

    @Before
    fun setUp() {
        file = temporaryFolder.newFile()
        chars = mutableListOf()
    }

    @Test
    fun testWithEmptyFile() {
        file.forEachChar { chars.add(it) }
        assertTrue(chars.isEmpty())
    }

    @Test
    fun testWithSingleLineFile() {
        file.writeText("Hello!")
        file.forEachChar { chars.add(it) }
        assertEquals(listOf('H', 'e', 'l', 'l', 'o', '!'), chars.toList())
    }

    @Test
    fun testWithMultiLineFile() {
        file.writeText("foo\nBar \n")
        file.forEachChar { chars.add(it) }
        assertEquals(listOf('f', 'o', 'o', '\n', 'B', 'a', 'r', ' ', '\n'), chars.toList())
    }
}
