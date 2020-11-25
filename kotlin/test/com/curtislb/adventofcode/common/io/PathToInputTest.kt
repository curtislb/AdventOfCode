package com.curtislb.adventofcode.common.io

import org.junit.Test
import java.nio.file.Paths
import kotlin.test.assertEquals

/**
 * Tests [pathToInput].
 */
class PathToInputTest {
    @Test
    fun testWithoutPart() {
        assertEquals(Paths.get("input", "year1984", "day02", "input.txt"), pathToInput(year = 1984, day = 2))
        assertEquals(
            Paths.get("input", "year101", "day23", "foo.bar"),
            pathToInput(year = 101, day = 23, fileName = "foo.bar")
        )
        assertEquals(
            Paths.get("input", "year31850", "day00", "myFile"),
            pathToInput(year = 31850, day = 0, fileName = "myFile")
        )
    }

    @Test
    fun testWithPart() {
        assertEquals(
            Paths.get("input", "year2008", "day05", "part3", "input.txt"),
            pathToInput(year = 2008, day = 5, part = 3)
        )
        assertEquals(
            Paths.get("input", "year47276", "day00", "part10", "lorem.ipsum"),
            pathToInput(year = 47276, day = 0, part = 10, fileName = "lorem.ipsum")
        )
        assertEquals(
            Paths.get("input", "year641", "day17", "part0", "test"),
            pathToInput(year = 641, day = 17, part = 0, fileName = "test")
        )
    }
}
