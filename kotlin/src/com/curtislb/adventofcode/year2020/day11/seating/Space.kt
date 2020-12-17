package com.curtislb.adventofcode.year2020.day11.seating

enum class Space(val symbol: Char) {
    FLOOR('.'),

    EMPTY('L'),

    OCCUPIED('#');

    companion object {
        fun from(symbol: Char): Space = when (symbol) {
            '.' -> FLOOR
            'L' -> EMPTY
            '#' -> OCCUPIED
            else -> throw IllegalArgumentException("Invalid space: $symbol")
        }
    }
}
