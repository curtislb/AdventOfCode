package com.curtislb.adventofcode.year2019.day10.asteroid

import com.curtislb.adventofcode.common.grid.Point
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import kotlin.test.assertEquals
import kotlin.test.assertNull

/**
 * Tests [AsteroidField].
 */
class AsteroidFieldTest {
    @get:Rule val temporaryFolder = TemporaryFolder()

    @Test fun testFindBestStationWhenEmpty() {
        val file = temporaryFolder.newFile()
        val asteroidField = AsteroidField(file)
        assertEquals(Pair(null, 0), asteroidField.findBestStation())
        assertEquals(0, asteroidField.size)
    }

    @Test fun testFindBestStationWithOneAsteroid() {
        val file = temporaryFolder.newFile().apply { writeText("#") }
        val asteroidField = AsteroidField(file)
        assertEquals(Pair(Point(0, 0), 0), asteroidField.findBestStation())
        assertEquals(1, asteroidField.size)
    }

    @Test fun testFindBestStationWithOneAsteroidInEmptyField() {
        val file = temporaryFolder.newFile().apply {
            writeText("""
                ...
                ..#
                ...
            """.trimIndent())
        }
        val asteroidField = AsteroidField(file)
        assertEquals(Pair(Point(2, -1), 0), asteroidField.findBestStation())
        assertEquals(1, asteroidField.size)
    }

    @Test fun testFindBestStationWithHorizontalCollinearAsteroids() {
        val file = temporaryFolder.newFile().apply {
            writeText("""
                ....
                ##.#
                ....
            """.trimIndent())
        }
        val asteroidField = AsteroidField(file)
        assertEquals(Pair(Point(1, -1), 2), asteroidField.findBestStation())
        assertEquals(3, asteroidField.size)
    }

    @Test fun testFindBestStationWithVerticalCollinearAsteroids() {
        val file = temporaryFolder.newFile().apply {
            writeText("""
                ..#
                ...
                ..#
                ..#
            """.trimIndent())
        }
        val asteroidField = AsteroidField(file)
        assertEquals(Pair(Point(2, -2), 2), asteroidField.findBestStation())
        assertEquals(3, asteroidField.size)
    }

    @Test fun testFindBestStationWithDiagonalCollinearAsteroids() {
        val file = temporaryFolder.newFile().apply {
            writeText("""
                ...#
                ....
                .#..
                #...
            """.trimIndent())
        }
        val asteroidField = AsteroidField(file)
        assertEquals(Pair(Point(1, -2), 2), asteroidField.findBestStation())
        assertEquals(3, asteroidField.size)
    }

    @Test fun testFindBestStationWithSeveralAsteroids() {
        val file = temporaryFolder.newFile().apply {
            writeText("""
                .#..#
                .....
                #####
                ....#
                ...##
            """.trimIndent())
        }
        val asteroidField = AsteroidField(file)
        assertEquals(Pair(Point(3, -4), 8), asteroidField.findBestStation())
        assertEquals(10, asteroidField.size)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testVaporizeAsteroidsWhenEmpty() {
        val file = temporaryFolder.newFile()
        val asteroidField = AsteroidField(file)
        asteroidField.vaporizeAsteroids(Point(0, 0), 0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testVaporizeAsteroidsWithNegativeCount() {
        val file = temporaryFolder.newFile().apply { writeText("#") }
        val asteroidField = AsteroidField(file)
        asteroidField.vaporizeAsteroids(Point(0, 0), -1)
    }

    @Test fun testVaporizeAsteroidsWithOneAsteroid() {
        val file = temporaryFolder.newFile().apply { writeText("#") }
        val asteroidField = AsteroidField(file)
        assertNull(asteroidField.vaporizeAsteroids(Point(0, 0), 1))
        assertEquals(1, asteroidField.size)
    }

    @Test fun testVaporizeAsteroidsWithTwoAsteroids() {
        val file = temporaryFolder.newFile().apply { writeText("##") }
        val asteroidField = AsteroidField(file)
        assertEquals(Point(1, 0), asteroidField.vaporizeAsteroids(Point(0, 0), 1))
        assertEquals(1, asteroidField.size)
    }

    @Test fun testVaporizeAsteroidsWithHorizontalCollinearAsteroids() {
        val file = temporaryFolder.newFile().apply {
            writeText("""
                ....
                ##.#
                ....
            """.trimIndent())
        }
        val asteroidField = AsteroidField(file)

        assertEquals(Point(0, -1), asteroidField.vaporizeAsteroids(Point(3, -1), 2))
        assertEquals(1, asteroidField.size)
    }

    @Test fun testVaporizeAsteroidsWithVerticalCollinearAsteroids() {
        val file = temporaryFolder.newFile().apply {
            writeText("""
                ..#
                ...
                ..#
                ..#
            """.trimIndent())
        }
        val asteroidField = AsteroidField(file)
        assertEquals(Point(2, 0), asteroidField.vaporizeAsteroids(Point(2, -3), 2))
        assertEquals(1, asteroidField.size)
    }

    @Test fun testVaporizeAsteroidsWithDiagonalCollinearAsteroids() {
        val file = temporaryFolder.newFile().apply {
            writeText("""
                ...#
                ....
                .#..
                #...
            """.trimIndent())
        }
        val asteroidField = AsteroidField(file)
        assertEquals(Point(3, 0), asteroidField.vaporizeAsteroids(Point(0, -3), 2))
        assertEquals(1, asteroidField.size)
    }

    @Test fun testVaporizeAsteroidsWithMultipleIterations() {
        val file = temporaryFolder.newFile().apply {
            writeText("""
                .#....#####...#..
                ##...##.#####..##
                ##...#...#.#####.
                ..#.....#...###..
                ..#.#.....#....##
            """.trimIndent())
        }
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
