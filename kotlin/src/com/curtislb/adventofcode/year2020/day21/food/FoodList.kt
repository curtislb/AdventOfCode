package com.curtislb.adventofcode.year2020.day21.food

import com.curtislb.adventofcode.common.collection.mapToMap

/**
 * TODO
 */
class FoodList(foodListString: String) {
    /**
     * TODO
     */
    private val ingredientCounts: Map<String, Int>

    /**
     * TODO
     */
    val dangerousIngredientAllergens: Map<String, String>

    init {
        val ingredientIndicesMap = mutableMapOf<String, MutableSet<Int>>()
        val allergenIngredientsMap = mutableMapOf<String, MutableSet<String>>()
        foodListString.trim().lines().forEachIndexed { index, line ->
            val matchResult = INGREDIENTS_ALLERGENS_REGEX.matchEntire(line)
            if (matchResult == null || matchResult.groups.size != 3) {
                throw IllegalArgumentException("Malformed food entry: $line")
            }

            val ingredients = matchResult.groupValues[1].split(' ')
            ingredients.forEach { ingredient ->
                ingredientIndicesMap.getOrPut(ingredient) { mutableSetOf() }.add(index)
            }

            val allergens = matchResult.groupValues[2].split(',').map { it.trim() }
            allergens.forEach { allergen ->
                if (allergen !in allergenIngredientsMap) {
                    allergenIngredientsMap[allergen] = ingredients.toMutableSet()
                } else {
                    allergenIngredientsMap[allergen]!!.retainAll(ingredients.toMutableSet())
                }
            }
        }
        ingredientCounts = ingredientIndicesMap.mapToMap { (ingredient, indices) -> ingredient to indices.size }

        val ingredientAllergenMap = mutableMapOf<String, String>()
        while (allergenIngredientsMap.isNotEmpty()) {
            for ((allergen, ingredients) in allergenIngredientsMap.entries.toSet()) {
                when (ingredients.size) {
                    0 -> allergenIngredientsMap.remove(allergen)
                    1 -> {
                        val ingredient = ingredients.first()
                        ingredientAllergenMap[ingredient] = allergen
                        allergenIngredientsMap.remove(allergen)
                        allergenIngredientsMap.values.forEach { it.remove(ingredient) }
                    }
                }
            }
        }
        dangerousIngredientAllergens = ingredientAllergenMap
    }

    /**
     * TODO
     */
    val safeIngredients: Set<String> get() = ingredientCounts.keys - dangerousIngredientAllergens.keys

    /**
     * TODO
     */
    fun countFoodsWith(ingredient: String): Int = ingredientCounts[ingredient] ?: 0

    companion object {
        /**
         * TODO
         */
        private val INGREDIENTS_ALLERGENS_REGEX = Regex("""(.*) \(contains (.*)\)""")
    }
}
