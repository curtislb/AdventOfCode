package com.curtislb.adventofcode.year2019.day06.orbits

import com.curtislb.adventofcode.common.testing.createTempFile
import java.nio.file.Path
import kotlin.test.assertEquals
import kotlin.test.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir

/**
 * Tests [Universe].
 */
class UniverseTest {
    @Test
    fun testWhenEmpty() {
        val universe = Universe()
        assertEquals(0, universe.countOrbits("A"))
        assertEquals(0, universe.countOrbits("B"))
        assertNull(universe.findOrbitalTransferDistance("A", "A"))
        assertNull(universe.findOrbitalTransferDistance("A", "B"))
        assertNull(universe.findOrbitalTransferDistance("B", "A"))
        assertNull(universe.findOrbitalTransferDistance("B", "B"))
    }

    @Test
    fun testWithOneOrbit() {
        val universe = Universe()
        universe.addOrbit("A", "B")
        assertEquals(1, universe.countOrbits("A"))
        assertEquals(0, universe.countOrbits("B"))
        assertEquals(0, universe.countOrbits("C"))
        assertEquals(0, universe.findOrbitalTransferDistance("A", "A"))
        assertNull(universe.findOrbitalTransferDistance("A", "B"))
        assertNull(universe.findOrbitalTransferDistance("A", "C"))
        assertNull(universe.findOrbitalTransferDistance("B", "A"))
        assertEquals(0, universe.findOrbitalTransferDistance("B", "B"))
        assertNull(universe.findOrbitalTransferDistance("B", "C"))
        assertNull(universe.findOrbitalTransferDistance("C", "A"))
        assertNull(universe.findOrbitalTransferDistance("C", "B"))
        assertNull(universe.findOrbitalTransferDistance("C", "C"))
    }

    @Test
    fun testWithTwoOrbitsAroundCenter() {
        val universe = Universe()
        universe.addOrbit("A", "B")
        universe.addOrbit("A", "C")
        assertEquals(2, universe.countOrbits("A"))
        assertEquals(0, universe.countOrbits("B"))
        assertEquals(0, universe.countOrbits("C"))
        assertEquals(0, universe.findOrbitalTransferDistance("A", "A"))
        assertNull(universe.findOrbitalTransferDistance("A", "B"))
        assertNull(universe.findOrbitalTransferDistance("A", "C"))
        assertNull(universe.findOrbitalTransferDistance("B", "A"))
        assertEquals(0, universe.findOrbitalTransferDistance("B", "B"))
        assertEquals(0, universe.findOrbitalTransferDistance("B", "C"))
        assertNull(universe.findOrbitalTransferDistance("C", "A"))
        assertEquals(0, universe.findOrbitalTransferDistance("C", "B"))
        assertEquals(0, universe.findOrbitalTransferDistance("C", "C"))
    }

    @Test
    fun testWithTwoOrbitChain() {
        val universe = Universe()
        universe.addOrbit("A", "B")
        universe.addOrbit("B", "C")
        assertEquals(3, universe.countOrbits("A"))
        assertEquals(1, universe.countOrbits("B"))
        assertEquals(0, universe.countOrbits("C"))
        assertEquals(0, universe.findOrbitalTransferDistance("A", "A"))
        assertNull(universe.findOrbitalTransferDistance("A", "B"))
        assertNull(universe.findOrbitalTransferDistance("A", "C"))
        assertNull(universe.findOrbitalTransferDistance("B", "A"))
        assertEquals(0, universe.findOrbitalTransferDistance("B", "B"))
        assertEquals(1, universe.findOrbitalTransferDistance("B", "C"))
        assertNull(universe.findOrbitalTransferDistance("C", "A"))
        assertEquals(1, universe.findOrbitalTransferDistance("C", "B"))
        assertEquals(0, universe.findOrbitalTransferDistance("C", "C"))
    }

    @Test
    fun testWithFourPlanets() {
        val universe = Universe()
        universe.addOrbit("A", "B")
        universe.addOrbit("B", "C")
        universe.addOrbit("A", "D")
        assertEquals(4, universe.countOrbits("A"))
        assertEquals(1, universe.countOrbits("B"))
        assertEquals(0, universe.countOrbits("C"))
        assertEquals(0, universe.countOrbits("D"))
        assertEquals(0, universe.findOrbitalTransferDistance("A", "A"))
        assertNull(universe.findOrbitalTransferDistance("A", "B"))
        assertNull(universe.findOrbitalTransferDistance("A", "C"))
        assertNull(universe.findOrbitalTransferDistance("A", "D"))
        assertNull(universe.findOrbitalTransferDistance("B", "A"))
        assertEquals(0, universe.findOrbitalTransferDistance("B", "B"))
        assertEquals(1, universe.findOrbitalTransferDistance("B", "C"))
        assertEquals(0, universe.findOrbitalTransferDistance("B", "D"))
        assertNull(universe.findOrbitalTransferDistance("C", "A"))
        assertEquals(1, universe.findOrbitalTransferDistance("C", "B"))
        assertEquals(0, universe.findOrbitalTransferDistance("C", "C"))
        assertEquals(1, universe.findOrbitalTransferDistance("C", "D"))
        assertNull(universe.findOrbitalTransferDistance("D", "A"))
        assertEquals(0, universe.findOrbitalTransferDistance("D", "B"))
        assertEquals(1, universe.findOrbitalTransferDistance("D", "C"))
        assertEquals(0, universe.findOrbitalTransferDistance("D", "D"))
    }

    @Test
    fun testConstructFromEmptyFile(@TempDir tempDir: Path) {
        val universe = Universe(tempDir.createTempFile())
        assertEquals(0, universe.countOrbits("A"))
        assertEquals(0, universe.countOrbits("B"))
        assertNull(universe.findOrbitalTransferDistance("A", "A"))
        assertNull(universe.findOrbitalTransferDistance("A", "B"))
        assertNull(universe.findOrbitalTransferDistance("B", "A"))
        assertNull(universe.findOrbitalTransferDistance("B", "B"))
    }

    @Test
    fun testConstructFromFileWithFourPlanets(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(
            text = """
                A)B
                B)C
                A)D
            """.trimIndent()
        )

        val universe = Universe(file)
        assertEquals(4, universe.countOrbits("A"))
        assertEquals(1, universe.countOrbits("B"))
        assertEquals(0, universe.countOrbits("C"))
        assertEquals(0, universe.countOrbits("D"))
        assertEquals(0, universe.findOrbitalTransferDistance("A", "A"))
        assertNull(universe.findOrbitalTransferDistance("A", "B"))
        assertNull(universe.findOrbitalTransferDistance("A", "C"))
        assertNull(universe.findOrbitalTransferDistance("A", "D"))
        assertNull(universe.findOrbitalTransferDistance("B", "A"))
        assertEquals(0, universe.findOrbitalTransferDistance("B", "B"))
        assertEquals(1, universe.findOrbitalTransferDistance("B", "C"))
        assertEquals(0, universe.findOrbitalTransferDistance("B", "D"))
        assertNull(universe.findOrbitalTransferDistance("C", "A"))
        assertEquals(1, universe.findOrbitalTransferDistance("C", "B"))
        assertEquals(0, universe.findOrbitalTransferDistance("C", "C"))
        assertEquals(1, universe.findOrbitalTransferDistance("C", "D"))
        assertNull(universe.findOrbitalTransferDistance("D", "A"))
        assertEquals(0, universe.findOrbitalTransferDistance("D", "B"))
        assertEquals(1, universe.findOrbitalTransferDistance("D", "C"))
        assertEquals(0, universe.findOrbitalTransferDistance("D", "D"))
    }
}
