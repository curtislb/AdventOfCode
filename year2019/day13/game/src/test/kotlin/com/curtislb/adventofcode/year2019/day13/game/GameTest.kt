package com.curtislb.adventofcode.year2019.day13.game

import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.common.testing.assertContainsExactly
import com.curtislb.adventofcode.common.testing.createTempFile
import com.curtislb.adventofcode.year2019.day13.game.strategy.CustomStrategy
import java.math.BigInteger
import java.nio.file.Path
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir

/**
 * Tests [Game].
 */
class GameTest {
    @Test
    fun testFindAllTilesWithEmptyBoard(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "99")
        val game = Game(file)
        assertEquals(emptyList(), game.findAllTiles(Tile.EMPTY))
        assertEquals(emptyList(), game.findAllTiles(Tile.WALL))
        assertEquals(emptyList(), game.findAllTiles(Tile.BLOCK))
        assertEquals(emptyList(), game.findAllTiles(Tile.PADDLE))
    }

    @Test
    fun testFindAllTilesWithNonEmptyBoard(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(
            text = "104,0,104,0,104,1,104,1,104,1,104,1,104,0,104,2,104,2,99"
        )
        val game = Game(file)
        assertContainsExactly(
            listOf(Point(1, 0), Point(0, -1), Point(1, -2)),
            game.findAllTiles(Tile.EMPTY)
        )
        assertContainsExactly(listOf(Point.ORIGIN, Point(1, -1)), game.findAllTiles(Tile.WALL))
        assertContainsExactly(listOf(Point(0, -2)), game.findAllTiles(Tile.BLOCK))
        assertEquals(emptyList(), game.findAllTiles(Tile.PADDLE))
    }

    @Test
    fun testPlayAndReset(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "3,100,1,100,101,101,104,-1,104,0,4,101,99")
        val game = Game(file)

        var strategy = CustomStrategy { BigInteger("8647") }
        var score = game.play(strategy)
        assertEquals(BigInteger("8647"), score)

        strategy = CustomStrategy { BigInteger("4426") }
        score = game.play(strategy)
        assertEquals(BigInteger("8647"), score)

        game.reset()
        score = game.play(strategy)
        assertEquals(BigInteger("4426"), score)
    }

    @Test
    fun testPlayAndResetWithInitialization(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(text = "3,100,1,100,101,101,104,-1,104,0,4,101,99")
        val game = Game(file) { it[101] += BigInteger.TEN }

        var strategy = CustomStrategy { BigInteger("2359") }
        var score = game.play(strategy)
        assertEquals(BigInteger("2369"), score)

        strategy = CustomStrategy { BigInteger("7426") }
        score = game.play(strategy)
        assertEquals(BigInteger("2369"), score)

        game.reset()
        score = game.play(strategy)
        assertEquals(BigInteger("7436"), score)
    }

    @Test
    fun testToString(@TempDir tempDir: Path) {
        val file = tempDir.createTempFile(
            text = "104,0,104,0,104,1,104,1,104,1,104,1,104,0,104,2,104,2,104,-1,104,0,104,3133,99"
        )
        val game = Game(file)
        assertEquals(
            """
                Score: 3133
                #.
                .#
                X.
            """.trimIndent(),
            game.toString()
        )
    }
}
