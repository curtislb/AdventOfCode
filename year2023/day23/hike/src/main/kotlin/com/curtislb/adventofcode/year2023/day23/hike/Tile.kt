package com.curtislb.adventofcode.year2023.day23.hike

/**
 * A type of tile that may appear in a [HikingTrailMap] grid.
 *
 * @property symbol A character that uniquely identifies the type of tile.
 * @property isWalkable Whether it is possible to step onto the tile.
 */
enum class Tile(val symbol: Char, val isWalkable: Boolean) {
    /**
     * A tile representing part of a hiking path. It is *always* possible to step from this tile
     * onto any other adjacent, walkable tile.
     */
    PATH(symbol = '.', isWalkable = true),

    /**
     * A tile representing part of the forest surrounding the hiking paths. It is *never* possible
     * to step onto this tile.
     */
    FOREST(symbol = '#', isWalkable = false),

    /**
     * A tile representing part of a hiking path that slopes downward in the "up" grid direction.
     *
     * If the tile is icy, it is only possible to step from this tile onto one that is directly
     * above it in the grid.
     */
    SLOPE_UP(symbol = '^', isWalkable = true),

    /**
     * A tile representing part of a hiking path that slopes downward in the "right" grid direction.
     *
     * If the tile is icy, it is only possible to step from this tile onto one that is directly
     * right of it in the grid.
     */
    SLOPE_RIGHT(symbol = '>', isWalkable = true),

    /**
     * A tile representing part of a hiking path that slopes downward in the "down" grid direction.
     *
     * If the tile is icy, it is only possible to step from this tile onto one that is directly
     * below it in the grid.
     */
    SLOPE_DOWN(symbol = 'v', isWalkable = true),

    /**
     * A tile representing part of a hiking path that slopes downward in the "left" grid direction.
     *
     * If the tile is icy, it is only possible to step from this tile onto one that is directly
     * left of it in the grid.
     */
    SLOPE_LEFT(symbol = '<', isWalkable = true);

    companion object {
        /**
         * Returns the [Tile] that corresponds to the given [char].
         *
         * @throws IllegalArgumentException If [char] has no corresponding tile.
         */
        fun fromChar(char: Char): Tile {
            return entries.firstOrNull { it.symbol == char }
                ?: throw IllegalArgumentException("Invalid tile char: $char")
        }
    }
}
