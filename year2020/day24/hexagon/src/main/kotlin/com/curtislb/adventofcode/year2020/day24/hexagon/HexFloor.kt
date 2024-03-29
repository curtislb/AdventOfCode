package com.curtislb.adventofcode.year2020.day24.hexagon

import com.curtislb.adventofcode.common.geometry.Point

class HexFloor(floorString: String) {
    var blackTiles: Set<Point> = mutableSetOf<Point>().apply {
        for (tileString in floorString.trim().lines()) {
            val point = tileString.trim().toHexPoint()
            if (point in this) {
                remove(point)
            } else {
                add(point)
            }
        }
    }
        private set

    fun update() {
        val newBlackTiles = blackTiles.toMutableSet()
        for (tile in blackTiles) {
            val neighborCount = tile.hexNeighbors.count { it in blackTiles }
            if (neighborCount !in 1..2) {
                newBlackTiles.remove(tile)
            }
        }

        val adjacentTiles = mutableSetOf<Point>().apply {
            for (tile in blackTiles) {
                for (neighbor in tile.hexNeighbors) {
                    if (neighbor !in blackTiles) {
                        add(neighbor)
                    }
                }
            }
        }
        for (tile in adjacentTiles) {
            val neighborCount = tile.hexNeighbors.count { it in blackTiles }
            if (neighborCount == 2) {
                newBlackTiles.add(tile)
            }
        }

        blackTiles = newBlackTiles
    }

    companion object {
        private val Point.hexNeighbors: List<Point>
            get() = listOf(
                Point(x + 2, y),
                Point(x + 1, y - 1),
                Point(x - 1, y - 1),
                Point(x - 2, y),
                Point(x - 1, y + 1),
                Point(x + 1, y + 1)
            )

        private fun String.toHexPoint(): Point {
            var x = 0
            var y = 0
            var index = 0
            while (index < length) {
                when (val firstLetter = this[index]) {
                    'e' -> {
                        x += 2
                        index++
                    }

                    'w' -> {
                        x -= 2
                        index++
                    }

                    'n' -> {
                        y++
                        when (val secondLetter = this[index + 1]) {
                            'e' -> x++
                            'w' -> x--
                            else -> throw IllegalArgumentException(
                                "Invalid direction: n$secondLetter"
                            )
                        }
                        index += 2
                    }

                    's' -> {
                        y--
                        when (val secondLetter = this[index + 1]) {
                            'e' -> x++
                            'w' -> x--
                            else -> throw IllegalArgumentException(
                                "Invalid direction: s$secondLetter"
                            )
                        }
                        index += 2
                    }

                    else -> throw IllegalArgumentException("Invalid direction: $firstLetter")
                }
            }
            return Point(x, y)
        }
    }
}
