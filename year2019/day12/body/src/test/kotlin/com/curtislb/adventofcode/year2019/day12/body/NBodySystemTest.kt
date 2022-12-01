package com.curtislb.adventofcode.year2019.day12.body

import com.curtislb.adventofcode.common.testing.createTempFile
import java.nio.file.Path
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir

/**
 * Tests [NBodySystem].
 */
class NBodySystemTest {
    @Test
    fun testConstructWithEmptyFile(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile()
        val system = NBodySystem(file)
        assertEquals(emptyList(), system.bodies)
        assertEquals(0, system.totalEnergy)
    }

    @Test
    fun testSimulateAndReset(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(
            text = """
                <x=-1, y=0, z=2>
                <x=2, y=-10, z=-7>
                <x=4, y=-8, z=8>
                <x=3, y=5, z=-1>
            """.trimIndent()
        )
        val system = NBodySystem(file)
        assertEquals(
            listOf(
                Body(initialPosition = Triple(-1, 0, 2)),
                Body(initialPosition = Triple(2, -10, -7)),
                Body(initialPosition = Triple(4, -8, 8)),
                Body(initialPosition = Triple(3, 5, -1))
            ),
            system.bodies
        )
        assertEquals(0, system.totalEnergy)

        system.simulate()
        assertEquals(
            listOf(
                Body(initialPosition = Triple(2, -1, 1), initialVelocity = Triple(3, -1, -1)),
                Body(initialPosition = Triple(3, -7, -4), initialVelocity = Triple(1, 3, 3)),
                Body(initialPosition = Triple(1, -7, 5), initialVelocity = Triple(-3, 1, -3)),
                Body(initialPosition = Triple(2, 2, 0), initialVelocity = Triple(-1, -3, 1))
            ),
            system.bodies
        )
        assertEquals(229, system.totalEnergy)

        system.simulate(steps = 9)
        assertEquals(
            listOf(
                Body(initialPosition = Triple(2, 1, -3), initialVelocity = Triple(-3, -2, 1)),
                Body(initialPosition = Triple(1, -8, 0), initialVelocity = Triple(-1, 1, 3)),
                Body(initialPosition = Triple(3, -6, 1), initialVelocity = Triple(3, 2, -3)),
                Body(initialPosition = Triple(2, 0, 4), initialVelocity = Triple(1, -1, -1))
            ),
            system.bodies
        )
        assertEquals(179, system.totalEnergy)

        system.reset()
        assertEquals(
            listOf(
                Body(initialPosition = Triple(-1, 0, 2)),
                Body(initialPosition = Triple(2, -10, -7)),
                Body(initialPosition = Triple(4, -8, 8)),
                Body(initialPosition = Triple(3, 5, -1))
            ),
            system.bodies
        )
        assertEquals(0, system.totalEnergy)
    }

    @Test
    fun testFindAxialPeriodicityWithSingleStationaryBody(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "<x=-1, y=0, z=2>")
        val system = NBodySystem(file)
        assertEquals(Triple(1, 1, 1), system.findAxialPeriodicity())
    }

    @Test
    fun testFindAxialPeriodicityWithMultipleBodies(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(
            text = """
                <x=-1, y=0, z=2>
                <x=2, y=-10, z=-7>
                <x=4, y=-8, z=8>
                <x=3, y=5, z=-1>
            """.trimIndent()
        )
        val system = NBodySystem(file)
        assertEquals(Triple(18, 28, 44), system.findAxialPeriodicity())
    }
}
