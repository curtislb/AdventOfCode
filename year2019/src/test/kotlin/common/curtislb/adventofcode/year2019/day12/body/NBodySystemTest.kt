package com.curtislb.adventofcode.year2019.day12.body

import com.curtislb.adventofcode.common.collection.MutableIntVector
import org.junit.jupiter.api.BeforeEach
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.junit.rules.TemporaryFolder
import java.io.File
import kotlin.test.assertEquals

/**
 * Tests [NBodySystem].
 */
class NBodySystemTest {
    @get:Rule val temporaryFolder = TemporaryFolder()

    private lateinit var file: File

    @Before
    fun setUp() {
        file = temporaryFolder.newFile()
    }

    @Test
    fun testConstructWithEmptyFile() {
        val system = NBodySystem(file)
        assertEquals(emptyList(), system.bodies)
        assertEquals(0, system.totalEnergy)
    }

    @Test
    fun testSimulateAndReset() {
        file.writeText("""
            <x=-1, y=0, z=2>
            <x=2, y=-10, z=-7>
            <x=4, y=-8, z=8>
            <x=3, y=5, z=-1>
        """.trimIndent())
        val system = NBodySystem(file)
        assertEquals(
            listOf(
                Body(MutableIntVector(-1, 0, 2)),
                Body(MutableIntVector(2, -10, -7)),
                Body(MutableIntVector(4, -8, 8)),
                Body(MutableIntVector(3, 5, -1))
            ),
            system.bodies
        )
        assertEquals(0, system.totalEnergy)

        system.simulate()
        assertEquals(
            listOf(
                Body(position = MutableIntVector(2, -1, 1), velocity = MutableIntVector(3, -1, -1)),
                Body(position = MutableIntVector(3, -7, -4), velocity = MutableIntVector(1, 3, 3)),
                Body(position = MutableIntVector(1, -7, 5), velocity = MutableIntVector(-3, 1, -3)),
                Body(position = MutableIntVector(2,  2, 0), velocity = MutableIntVector(-1, -3, 1))
            ),
            system.bodies
        )
        assertEquals(229, system.totalEnergy)

        system.simulate(steps = 9)
        assertEquals(
            listOf(
                Body(position = MutableIntVector(2, 1, -3), velocity = MutableIntVector(-3, -2, 1)),
                Body(position = MutableIntVector(1, -8, 0), velocity = MutableIntVector(-1, 1, 3)),
                Body(position = MutableIntVector(3, -6, 1), velocity = MutableIntVector(3, 2, -3)),
                Body(position = MutableIntVector(2, 0, 4), velocity = MutableIntVector(1, -1, -1)),
            ),
            system.bodies
        )
        assertEquals(179, system.totalEnergy)

        system.reset()
        assertEquals(
            listOf(
                Body(MutableIntVector(-1, 0, 2)),
                Body(MutableIntVector(2, -10, -7)),
                Body(MutableIntVector(4, -8, 8)),
                Body(MutableIntVector(3, 5, -1))
            ),
            system.bodies
        )
        assertEquals(0, system.totalEnergy)
    }

    @Test
    fun testFindAxialPeriodicityWithSingleStationaryBody() {
        file.writeText("<x=-1, y=0, z=2>")
        val system = NBodySystem(file)
        assertEquals(MutableIntVector(1, 1, 1), system.findAxialPeriodicity())
    }

    @Test
    fun testFindAxialPeriodicityWithMultipleBodies() {
        file.writeText("""
            <x=-1, y=0, z=2>
            <x=2, y=-10, z=-7>
            <x=4, y=-8, z=8>
            <x=3, y=5, z=-1>
        """.trimIndent())
        val system = NBodySystem(file)
        assertEquals(MutableIntVector(18, 28, 44), system.findAxialPeriodicity())
    }
}
