package com.curtislb.adventofcode.year2021.day04.bingo

/**
 * A bingo board space that contains a [number] and can be [marked].
 */
data class BingoSpace(val number: Int, var marked: Boolean = false) {
    override fun toString(): String = if (marked) "($number)" else number.toString()
}
