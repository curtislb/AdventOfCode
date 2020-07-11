/*
--- Part Two ---

After collecting ORE for a while, you check your cargo hold: 1 trillion (1000000000000) units of ORE.

With that much ore, given the examples above:

  - The 13312 ORE-per-FUEL example could produce 82892753 FUEL.
  - The 180697 ORE-per-FUEL example could produce 5586022 FUEL.
  - The 2210736 ORE-per-FUEL example could produce 460664 FUEL.

Given 1 trillion ORE, what is the maximum amount of FUEL you can produce?
*/

package com.curtislb.adventofcode.year2019.day14.part2

import com.curtislb.adventofcode.common.io.pathToInput
import com.curtislb.adventofcode.common.search.bisectIndex
import com.curtislb.adventofcode.year2019.day14.chemistry.MaterialAmount
import com.curtislb.adventofcode.year2019.day14.chemistry.Nanofactory
import java.nio.file.Path

/**
 * Returns the solution to the puzzle for 2019, day 14, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 * @param rawMaterial The name of a raw material that can be used in arbitrary quantities for reactions.
 * @param rawMaterialAvailable The amount of raw material that is available to use for reactions.
 * @param desiredMaterial The name of the material that we ultimately want to produce.
 */
fun solve(
    inputPath: Path = pathToInput(year = 2019, day = 14),
    rawMaterial: String = "ORE",
    rawMaterialAvailable: Long = 1_000_000_000_000L,
    desiredMaterial: String = "FUEL"
): Long? {
    val factory = Nanofactory(inputPath.toFile())

    // Binary search for the first product amount requiring more raw material than available.
    val rawMaterials = setOf(rawMaterial)
    val productAmountLimit = bisectIndex { amount ->
        val products = mapOf(MaterialAmount(desiredMaterial, amount).toPair())
        val requiredMaterials = factory.getRequiredMaterials(rawMaterials, products)
        val rawMaterialNeeded = requiredMaterials?.get(rawMaterial)
            ?: throw IllegalStateException("Failed to produce $desiredMaterial during bisect.")
        rawMaterialNeeded > rawMaterialAvailable
    }

    return if (productAmountLimit != null) productAmountLimit - 1L else null
}

fun main() = when (val solution = solve()) {
    null -> println("Unable to produce the desired material.")
    else -> println(solution)
}
