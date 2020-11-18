package com.curtislb.adventofcode.year2019.day13.game

import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.common.testing.assertContainsExactly
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests [Board].
 */
class BoardTest {
    @Test fun testWhenEmpty() {
        val board = Board()
        assertEquals(0, board.height)
        assertEquals(0, board.width)
        assertEquals(emptyList(), board.findAll(Tile.EMPTY))
        assertEquals(emptyList(), board.findAll(Tile.WALL))
        assertEquals(emptyList(), board.findAll(Tile.BLOCK))
        assertEquals(emptyList(), board.findAll(Tile.PADDLE))
        assertEquals(emptyList(), board.findAll(Tile.BALL))
        assertEquals(Tile.EMPTY, board[Point.ORIGIN])
        assertEquals(Tile.EMPTY, board[Point(1, 0)])
        assertEquals(Tile.EMPTY, board[Point(2, -2)])
        assertEquals("", board.toString())
    }

    @Test fun testUpdateTiles() {
        val board = Board()
        board[Point(4, -3)] = Tile.PADDLE
        assertEquals(4, board.height)
        assertEquals(5, board.width)
        assertContainsExactly(
            listOf(
                Point(0, 0), Point(1, 0), Point(2, 0), Point(3, 0), Point(4, 0),
                Point(0, -1), Point(1, -1), Point(2, -1), Point(3, -1), Point(4, -1),
                Point(0, -2), Point(1, -2), Point(2, -2), Point(3, -2), Point(4, -2),
                Point(0, -3), Point(1, -3), Point(2, -3), Point(3, -3)
            ),
            board.findAll(Tile.EMPTY)
        )
        assertEquals(emptyList(), board.findAll(Tile.WALL))
        assertEquals(emptyList(), board.findAll(Tile.BLOCK))
        assertContainsExactly(listOf(Point(4, -3)), board.findAll(Tile.PADDLE))
        assertEquals(emptyList(), board.findAll(Tile.BALL))
        assertEquals(Tile.EMPTY, board[Point.ORIGIN])
        assertEquals(Tile.EMPTY, board[Point(1, 0)])
        assertEquals(Tile.EMPTY, board[Point(3, 0)])
        assertEquals(Tile.EMPTY, board[Point(0, -1)])
        assertEquals(Tile.EMPTY, board[Point(2, -2)])
        assertEquals(Tile.EMPTY, board[Point(3, -3)])
        assertEquals(Tile.PADDLE, board[Point(4, -3)])
        assertEquals(
            """
                .....
                .....
                .....
                ....=
            """.trimIndent(),
            board.toString()
        )

        board[Point(1, 0)] = Tile.WALL
        board[Point(3, 0)] = Tile.WALL
        board[Point(0, -1)] = Tile.BLOCK
        board[Point(2, -2)] = Tile.BALL
        board[Point(3, -3)] = Tile.PADDLE
        board[Point(4, -3)] = Tile.EMPTY
        assertEquals(4, board.height)
        assertEquals(5, board.width)
        assertContainsExactly(
            listOf(
                Point(0, 0), Point(2, 0), Point(4, 0),
                Point(1, -1), Point(2, -1), Point(3, -1), Point(4, -1),
                Point(0, -2), Point(1, -2), Point(3, -2), Point(4, -2),
                Point(0, -3), Point(1, -3), Point(2, -3), Point(4, -3)
            ),
            board.findAll(Tile.EMPTY)
        )
        assertContainsExactly(listOf(Point(1, 0), Point(3, 0)), board.findAll(Tile.WALL))
        assertContainsExactly(listOf(Point(0, -1)), board.findAll(Tile.BLOCK))
        assertContainsExactly(listOf(Point(3, -3)), board.findAll(Tile.PADDLE))
        assertContainsExactly(listOf(Point(2, -2)), board.findAll(Tile.BALL))
        assertEquals(Tile.EMPTY, board[Point.ORIGIN])
        assertEquals(Tile.WALL, board[Point(1, 0)])
        assertEquals(Tile.WALL, board[Point(3, 0)])
        assertEquals(Tile.BLOCK, board[Point(0, -1)])
        assertEquals(Tile.BALL, board[Point(2, -2)])
        assertEquals(Tile.PADDLE, board[Point(3, -3)])
        assertEquals(Tile.EMPTY, board[Point(4, -3)])
        assertEquals(
            """
                .#.#.
                X....
                ..O..
                ...=.
            """.trimIndent(),
            board.toString()
        )
    }
}
