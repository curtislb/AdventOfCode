package com.curtislb.adventofcode.year2019.day12.body

import com.curtislb.adventofcode.common.testing.createTempFile
import com.curtislb.adventofcode.common.vector.intVectorOf
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
        assertEquals(0, system.totalEnergy())
    }

    @Test
    fun testSimulate(@TempDir tempDir: Path) {
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
                Body(initialPosition = intVectorOf(-1, 0, 2)),
                Body(initialPosition = intVectorOf(2, -10, -7)),
                Body(initialPosition = intVectorOf(4, -8, 8)),
                Body(initialPosition = intVectorOf(3, 5, -1))
            ),
            system.bodies
        )
        assertEquals(0, system.totalEnergy())

        system.simulate()
        assertEquals(
            listOf(
                Body(
                    initialPosition = intVectorOf(2, -1, 1),
                    initialVelocity = intVectorOf(3, -1, -1)
                ),
                Body(
                    initialPosition = intVectorOf(3, -7, -4),
                    initialVelocity = intVectorOf(1, 3, 3)
                ),
                Body(
                    initialPosition = intVectorOf(1, -7, 5),
                    initialVelocity = intVectorOf(-3, 1, -3)
                ),
                Body(
                    initialPosition = intVectorOf(2, 2, 0),
                    initialVelocity = intVectorOf(-1, -3, 1)
                )
            ),
            system.bodies
        )
        assertEquals(229, system.totalEnergy())

        system.simulate(steps = 9)
        assertEquals(
            listOf(
                Body(
                    initialPosition = intVectorOf(2, 1, -3),
                    initialVelocity = intVectorOf(-3, -2, 1)
                ),
                Body(
                    initialPosition = intVectorOf(1, -8, 0),
                    initialVelocity = intVectorOf(-1, 1, 3)
                ),
                Body(
                    initialPosition = intVectorOf(3, -6, 1),
                    initialVelocity = intVectorOf(3, 2, -3)
                ),
                Body(
                    initialPosition = intVectorOf(2, 0, 4),
                    initialVelocity = intVectorOf(1, -1, -1)
                )
            ),
            system.bodies
        )
        assertEquals(179, system.totalEnergy())
    }

    @Test
    fun testFindAxialPeriodicityWithSingleStationaryBody(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "<x=-1, y=0, z=2>")
        val system = NBodySystem(file)
        assertEquals(intVectorOf(1, 1, 1), system.findPerAxisPeriods())
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
        assertEquals(intVectorOf(18, 28, 44), system.findPerAxisPeriods())
    }
}
