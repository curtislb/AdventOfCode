package com.curtislb.adventofcode.year2020.day15.memory

fun playGame(startingNums: List<Int>, turnCount: Int): Int {
    var turn = 0
    val numberTurnMap = mutableMapOf<Int, Int>()
    var lastNum = -1
    while (turn < turnCount) {
        turn++
        val newLastNum = if (turn <= startingNums.size) {
            startingNums[turn - 1]
        } else {
            numberTurnMap[lastNum]?.let { turn - 1 - it } ?: 0
        }
        numberTurnMap[lastNum] = turn - 1
        lastNum = newLastNum
    }
    return lastNum
}
