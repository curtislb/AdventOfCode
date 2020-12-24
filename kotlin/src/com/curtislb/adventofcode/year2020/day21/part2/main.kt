/*
--- Part Two ---

Now that you've isolated the inert ingredients, you should have enough information to figure out which ingredient
contains which allergen.

In the above example:

- mxmxvkd contains dairy.
- sqjhc contains fish.
- fvjkl contains soy.

Arrange the ingredients alphabetically by their allergen and separate them by commas to produce your canonical dangerous
ingredient list. (There should not be any spaces in your canonical dangerous ingredient list.) In the above example,
this would be mxmxvkd,sqjhc,fvjkl.

Time to stock your raft with supplies. What is your canonical dangerous ingredient list?
*/

package com.curtislb.adventofcode.year2020.day21.part2

import com.curtislb.adventofcode.common.io.pathToInput
import com.curtislb.adventofcode.year2020.day21.food.FoodList
import java.nio.file.Path

/**
 * Returns the solution to the puzzle for 2020, day 21, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = pathToInput(year = 2020, day = 21)): String {
    val file = inputPath.toFile()
    val foodList = FoodList(file.readText())
    val sortedEntries = foodList.dangerousIngredientAllergens.entries.toMutableList().apply { sortBy { it.value } }
    return sortedEntries.joinToString(separator = ",") { it.key }
}

fun main() {
    println(solve())
}
