package com.curtislb.adventofcode.common.io

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File
import kotlin.test.assertEquals

/**
 * Tests [readIntRange].
 */
class ReadIntRangeTest {
    @get:Rule val temporaryFolder = TemporaryFolder()

    private lateinit var file: File

    @Before fun setUp() {
        file = temporaryFolder.newFile()
    }

    @Test fun testWithDefaultSeparator() {
        file.writeText("66-932")
        assertEquals(66..932, file.readIntRange())
    }

    @Test fun testWithCustomSeparator() {
        file.writeText("-733<=>821\n")
        assertEquals(-733..821, file.readIntRange(separator = "<=>"))
    }
}
