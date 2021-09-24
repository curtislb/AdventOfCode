package com.curtislb.adventofcode.year2019.day10.asteroid

import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.testing.createTempFile
import java.nio.file.Path
import kotlin.test.assertEquals
import kotlin.test.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.io.TempDir

/**
 * Tests [AsteroidField].
 */
class AsteroidFieldTest {
    @Test
    fun testFindBestStationWhenEmpty(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile()
        val asteroidField = AsteroidField(file)
        assertEquals(Pair(null, 0), asteroidField.findBestStation())
        assertEquals(0, asteroidField.size)
    }

    @Test
    fun testFindBestStationWithOneAsteroid(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "#")
        val asteroidField = AsteroidField(file)
        assertEquals(Pair(Point(0, 0), 0), asteroidField.findBestStation())
        assertEquals(1, asteroidField.size)
    }

    @Test
    fun testFindBestStationWithOneAsteroidInEmptyField(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(
            text = """
                ...
                ..#
                ...
            """.trimIndent()
        )
        val asteroidField = AsteroidField(file)
        assertEquals(Pair(Point(2, -1), 0), asteroidField.findBestStation())
        assertEquals(1, asteroidField.size)
    }

    @Test
    fun testFindBestStationWithHorizontalCollinearAsteroids(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(
            text = """
                ....
                ##.#
                ....
            """.trimIndent()
        )
        val asteroidField = AsteroidField(file)
        assertEquals(Pair(Point(1, -1), 2), asteroidField.findBestStation())
        assertEquals(3, asteroidField.size)
    }

    @Test
    fun testFindBestStationWithVerticalCollinearAsteroids(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(
            text = """
                ..#
                ...
                ..#
                ..#
            """.trimIndent()
        )
        val asteroidField = AsteroidField(file)
        assertEquals(Pair(Point(2, -2), 2), asteroidField.findBestStation())
        assertEquals(3, asteroidField.size)
    }

    @Test
    fun testFindBestStationWithDiagonalCollinearAsteroids(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(
            text = """
                ...#
                ....
                .#..
                #...
            """.trimIndent()
        )
        val asteroidField = AsteroidField(file)
        assertEquals(Pair(Point(1, -2), 2), asteroidField.findBestStation())
        assertEquals(3, asteroidField.size)
    }

    @Test
    fun testFindBestStationWithSeveralAsteroids(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(
            text = """
                .#..#
                .....
                #####
                ....#
                ...##
            """.trimIndent()
        )
        val asteroidField = AsteroidField(file)
        assertEquals(Pair(Point(3, -4), 8), asteroidField.findBestStation())
        assertEquals(10, asteroidField.size)
    }

    @Test
    fun testVaporizeAsteroidsWhenEmpty(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile()
        val asteroidField = AsteroidField(file)
        assertThrows<IllegalArgumentException> { asteroidField.vaporizeAsteroids(Point(0, 0), 0) }
    }

    @Test
    fun testVaporizeAsteroidsWithNegativeCount(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "#")
        val asteroidField = AsteroidField(file)
        assertThrows<IllegalArgumentException> { asteroidField.vaporizeAsteroids(Point(0, 0), -1) }
    }

    @Test
    fun testVaporizeAsteroidsWithOneAsteroid(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "#")
        val asteroidField = AsteroidField(file)
        assertNull(asteroidField.vaporizeAsteroids(Point(0, 0), 1))
        assertEquals(1, asteroidField.size)
    }

    @Test
    fun testVaporizeAsteroidsWithTwoAsteroids(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "##")
        val asteroidField = AsteroidField(file)
        assertEquals(Point(1, 0), asteroidField.vaporizeAsteroids(Point(0, 0), 1))
        assertEquals(1, asteroidField.size)
    }

    @Test
    fun testVaporizeAsteroidsWithHorizontalCollinearAsteroids(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(
            text = """
                ....
                ##.#
                ....
            """.trimIndent()
        )
        val asteroidField = AsteroidField(file)

        assertEquals(Point(0, -1), asteroidField.vaporizeAsteroids(Point(3, -1), 2))
        assertEquals(1, asteroidField.size)
    }

    @Test
    fun testVaporizeAsteroidsWithVerticalCollinearAsteroids(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(
            text = """
                ..#
                ...
                ..#
                ..#
            """.trimIndent()
        )
        val asteroidField = AsteroidField(file)
        assertEquals(Point(2, 0), asteroidField.vaporizeAsteroids(Point(2, -3), 2))
        assertEquals(1, asteroidField.size)
    }

    @Test
    fun testVaporizeAsteroidsWithDiagonalCollinearAsteroids(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(
            text = """
                ...#
                ....
                .#..
                #...
            """.trimIndent()
        )
        val asteroidField = AsteroidField(file)
        assertEquals(Point(3, 0), asteroidField.vaporizeAsteroids(Point(0, -3), 2))
        assertEquals(1, asteroidField.size)
    }

    @Test
    fun testVaporizeAsteroidsWithMultipleIterations(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(
            text = """
                .#....#####...#..
                ##...##.#####..##
                ##...#...#.#####.
                ..#.....#...###..
                ..#.#.....#....##
            """.trimIndent()
        )
        val asteroidField = AsteroidField(file)
        assertEquals(37, asteroidField.size)

        assertEquals(Point(15, -1), asteroidField.vaporizeAsteroids(Point(8, -3), 9))
        assertEquals(28, asteroidField.size)

        assertEquals(Point(4, -4), asteroidField.vaporizeAsteroids(Point(8, -3), 12))
        assertEquals(16, asteroidField.size)

        assertEquals(Point(5, -1), asteroidField.vaporizeAsteroids(Point(8, -3), 11))
        assertEquals(5, asteroidField.size)
    }
}
