/*
--- Part Two ---
After collecting ORE for a while, you check your cargo hold: 1 trillion (1000000000000) units of ORE.

With that much ore, given the examples above:

  - The 13312 ORE-per-FUEL example could produce 82892753 FUEL.
  - The 180697 ORE-per-FUEL example could produce 5586022 FUEL.
  - The 2210736 ORE-per-FUEL example could produce 460664 FUEL.

Given 1 trillion ORE, what is the maximum amount of FUEL you can produce?
*/

package com.adventofcode.curtislb.year2019.day14.part2

import com.adventofcode.curtislb.common.io.pathToInput
import com.adventofcode.curtislb.common.search.bisectIndex
import com.adventofcode.curtislb.year2019.day14.chemistry.MaterialAmount
import com.adventofcode.curtislb.year2019.day14.chemistry.Nanofactory

private val INPUT_PATH = pathToInput(year = 2019, day = 14)

private const val RAW_MATERIAL = "ORE"
private const val DESIRED_PRODUCT = "FUEL"
private const val RAW_MATERIAL_AVAILABLE = 1_000_000_000_000L

// Answer: 2169535
fun main() {
    val factory = Nanofactory(INPUT_PATH.toFile())

    // Binary search for the first product amount requiring more raw material than available.
    val rawMaterials = setOf(RAW_MATERIAL)
    val productAmountLimit = bisectIndex { amount ->
        val products = mapOf(MaterialAmount(DESIRED_PRODUCT, amount).toPair())
        val requiredMaterials = factory.getRequiredMaterials(rawMaterials, products)
        val rawMaterialNeeded = requiredMaterials?.get(RAW_MATERIAL)
            ?: throw IllegalStateException("Failed to produce $DESIRED_PRODUCT during bisect.")
        rawMaterialNeeded > RAW_MATERIAL_AVAILABLE
    }

    if (productAmountLimit != null) {
        println(productAmountLimit - 1L)
    } else {
        println("Unable to find maximum amount of $DESIRED_PRODUCT.")
    }
}
