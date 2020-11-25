package com.curtislb.adventofcode.year2019.day12.body

import com.curtislb.adventofcode.common.collection.MutableVector
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import kotlin.test.assertEquals

/**
 * Tests [NBodySystem].
 */
class NBodySystemTest {
    @get:Rule val temporaryFolder = TemporaryFolder()

    @Test
    fun testConstructFromEmptyFile() {
        val file = temporaryFolder.newFile()
        val system = NBodySystem(file)
        assertEquals(emptyList(), system.bodies)
        assertEquals(0, system.totalEnergy)
    }

    @Test
    fun testSimulateAndReset() {
        val file = temporaryFolder.newFile().apply {
            writeText("""
                <x=-1, y=0, z=2>
                <x=2, y=-10, z=-7>
                <x=4, y=-8, z=8>
                <x=3, y=5, z=-1>
            """.trimIndent())
        }
        val system = NBodySystem(file)
        assertEquals(
            listOf(
                Body(MutableVector(-1, 0, 2)),
                Body(MutableVector(2, -10, -7)),
                Body(MutableVector(4, -8, 8)),
                Body(MutableVector(3, 5, -1))
            ),
            system.bodies
        )
        assertEquals(0, system.totalEnergy)

        system.simulate()
        assertEquals(
            listOf(
                Body(position = MutableVector(2, -1, 1), velocity = MutableVector(3, -1, -1)),
                Body(position = MutableVector(3, -7, -4), velocity = MutableVector(1, 3, 3)),
                Body(position = MutableVector(1, -7, 5), velocity = MutableVector(-3, 1, -3)),
                Body(position = MutableVector(2,  2, 0), velocity = MutableVector(-1, -3, 1))
            ),
            system.bodies
        )
        assertEquals(229, system.totalEnergy)

        system.simulate(steps = 9)
        assertEquals(
            listOf(
                Body(position = MutableVector(2, 1, -3), velocity = MutableVector(-3, -2, 1)),
                Body(position = MutableVector(1, -8, 0), velocity = MutableVector(-1, 1, 3)),
                Body(position = MutableVector(3, -6, 1), velocity = MutableVector(3, 2, -3)),
                Body(position = MutableVector(2, 0, 4), velocity = MutableVector(1, -1, -1)),
            ),
            system.bodies
        )
        assertEquals(179, system.totalEnergy)

        system.reset()
        assertEquals(
            listOf(
                Body(MutableVector(-1, 0, 2)),
                Body(MutableVector(2, -10, -7)),
                Body(MutableVector(4, -8, 8)),
                Body(MutableVector(3, 5, -1))
            ),
            system.bodies
        )
        assertEquals(0, system.totalEnergy)
    }

    @Test
    fun testFindAxialPeriodicityWithSingleStationaryBody() {
        val file = temporaryFolder.newFile().apply { writeText("<x=-1, y=0, z=2>") }
        val system = NBodySystem(file)
        assertEquals(MutableVector(1, 1, 1), system.findAxialPeriodicity())
    }

    @Test
    fun testFindAxialPeriodicityWithMultipleBodies() {
        val file = temporaryFolder.newFile().apply {
            writeText("""
                <x=-1, y=0, z=2>
                <x=2, y=-10, z=-7>
                <x=4, y=-8, z=8>
                <x=3, y=5, z=-1>
            """.trimIndent())
        }
        val system = NBodySystem(file)
        assertEquals(MutableVector(18, 28, 44), system.findAxialPeriodicity())
    }
}
