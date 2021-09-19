package com.curtislb.adventofcode.year2020.day23.cups

import com.curtislb.adventofcode.common.collection.mapToMap
import com.curtislb.adventofcode.common.math.toDigit

class CupCircle(labeling: String, fillUpTo: Int? = null) {
    val cups: Map<Int, Cup>

    private var currentCup: Cup

    init {
        val cupList = labeling.trim().map { Cup(it.toDigit()) }.toMutableList()
        var maxInitialLabel = Int.MIN_VALUE
        cupList.forEachIndexed { index, cup ->
            cup.next = cupList[(index + 1) % cupList.size]
            maxInitialLabel = maxInitialLabel.coerceAtLeast(cup.label)
        }

        if (fillUpTo != null) {
            for (label in (maxInitialLabel + 1)..fillUpTo) {
                val cup = Cup(label)
                cupList.last().next = cup
                cupList.add(cup)
            }
            cupList.last().next = cupList.first()
        }

        cups = cupList.mapToMap { it.label to it }
        currentCup = cupList[0]
    }

    private val minLabel = cups.keys.minOrNull() ?: 1

    private val maxLabel = cups.keys.maxOrNull() ?: 0

    fun performMove() {
        val movedCups = mutableListOf<Cup>()
        repeat(3) {
            val prevCup = movedCups.lastOrNull() ?: currentCup
            movedCups.add(prevCup.next!!)
        }

        val newCurrentCup = movedCups.last().next!!
        currentCup.next = newCurrentCup

        var destLabel = currentCup.label
        val movedLabels = movedCups.map { it.label }
        do {
            destLabel = findNextHighestLabel(destLabel)
        } while (destLabel in movedLabels)

        val destCup = cups[destLabel]
        movedCups.last().next = destCup?.next
        destCup?.next = movedCups.first()

        currentCup = newCurrentCup
    }

    private fun findNextHighestLabel(label: Int): Int = if (label == minLabel) maxLabel else label - 1

    fun findLabelsAfter(label: Int, count: Int = cups.size - 1): List<Int> {
        val labelList = mutableListOf<Int>()
        var cup = cups[label]!!
        for (i in 1..count) {
            cup = cup.next!!
            if (cup.label == label) {
                break
            }
            labelList.add(cup.label)
        }
        return labelList
    }
}
