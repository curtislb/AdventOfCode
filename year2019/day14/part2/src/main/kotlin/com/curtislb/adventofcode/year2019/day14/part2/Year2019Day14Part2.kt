/*
--- Part Two ---

After collecting ORE for a while, you check your cargo hold: 1 trillion (1000000000000) units of
ORE.

With that much ore, given the examples above:

- The 13312 ORE-per-FUEL example could produce 82892753 FUEL.
- The 180697 ORE-per-FUEL example could produce 5586022 FUEL.
- The 2210736 ORE-per-FUEL example could produce 460664 FUEL.

Given 1 trillion ORE, what is the maximum amount of FUEL you can produce?
*/

package com.curtislb.adventofcode.year2019.day14.part2

import com.curtislb.adventofcode.common.search.bisect
import com.curtislb.adventofcode.year2019.day14.chemistry.MaterialAmount
import com.curtislb.adventofcode.year2019.day14.chemistry.Nanofactory
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2019, day 14, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 * @param rawMaterial The name of an available raw material that can be used for reactions.
 * @param rawMaterialAvailableAmount The amount of raw material that is available for reactions.
 * @param desiredMaterial The name of the material that should ultimately be produced.
 */
fun solve(
    inputPath: Path = Paths.get("..", "input", "input.txt"),
    rawMaterial: String = "ORE",
    rawMaterialAvailableAmount: Long = 1_000_000_000_000L,
    desiredMaterial: String = "FUEL"
): Long {
    val factory = Nanofactory(inputPath.toFile())

    // Binary search for the first product amount requiring more raw material than available
    val rawMaterials = setOf(rawMaterial)
    val productAmountLimit = bisect { amount ->
        val products = listOf(MaterialAmount(desiredMaterial, amount))
        val requiredMaterials = factory.findRequiredMaterials(rawMaterials, products)
        val rawMaterialNeeded =
            requiredMaterials?.find { (material, _) -> material == rawMaterial }?.amount
                ?: error("Failed to produce $desiredMaterial during bisect")
        rawMaterialNeeded > rawMaterialAvailableAmount
    }

    return productAmountLimit - 1L
}

fun main() {
    println(solve())
}
