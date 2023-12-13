package com.curtislb.adventofcode.year2023.day05.almanac

import com.curtislb.adventofcode.common.io.forEachSection
import com.curtislb.adventofcode.common.parse.parseLongs
import java.io.File

/**
 * A copy of the Island Island Almanac, which contains [CategoryMap]s for converting integer values
 * between various categories.
 *
 * @property categoryMaps A map from each category in the almanac to a [CategoryMap] that converts
 *  values of that category to new values of a different category.
 *
 * @constructor Creates a new [Almanac] instance with the given [categoryMaps].
 */
class Almanac(private val categoryMaps: Map<String, CategoryMap>) {
    /**
     * Returns the list of values that result from converting the given input [values] from the
     * specified [sourceCategory] to [destCategory].
     *
     * @throws IllegalArgumentException If [sourceCategory] can't be converted to [destCategory].
     */
    fun convertValues(
        values: List<Long>,
        sourceCategory: String,
        destCategory: String
    ): List<Long> {
        return foldOverCategoryMaps(values, sourceCategory, destCategory) { result, categoryMap ->
            categoryMap.convertValues(result)
        }
    }

    /**
     * Returns a list of value ranges that result from converting the given input [ranges] from the
     * specified [sourceCategory] to [destCategory].
     *
     * @throws IllegalArgumentException If [sourceCategory] can't be converted to [destCategory].
     */
    fun convertRanges(
        ranges: Set<LongRange>,
        sourceCategory: String,
        destCategory: String
    ): Set<LongRange> {
        return foldOverCategoryMaps(ranges, sourceCategory, destCategory) { result, categoryMap ->
            categoryMap.convertRanges(result)
        }
    }

    /**
     * Returns the result of applying the specified [operation] to an [initial] value and subsequent
     * result values for each category map in the almanac that can be used (in order) to map values
     * from [sourceCategory] to [destCategory].
     *
     * @throws IllegalArgumentException If [sourceCategory] can't be converted to [destCategory].
     */
    private inline fun <T> foldOverCategoryMaps(
        initial: T,
        sourceCategory: String,
        destCategory: String,
        operation: (result: T, categoryMap: CategoryMap) -> T
    ): T {
        var result = initial
        var category = sourceCategory
        while (category != destCategory) {
            val categoryMap = categoryMaps[category]
            require(categoryMap != null) { "No map for category: $category" }
            result = operation(result, categoryMap)
            category = categoryMap.destCategory
        }
        return result
    }

    companion object {
        /**
         * Returns a list of initial values and an [Almanac] read from the given input [file].
         *
         * The [file] must have the following format:
         *
         * ```
         * $label: $value1 $value2 ... $valueN
         *
         * $categoryMap1
         *
         * $categoryMap2
         *
         * ...
         *
         * $categoryMapM
         * ```
         *
         * See [CategoryMap.fromLines] for how each `categoryMap` section is formatted.
         *
         * @throws IllegalArgumentException If [file] is not formatted correctly.
         */
        fun parseFile(file: File): Pair<List<Long>, Almanac> {
            var values: List<Long> = emptyList()
            val categoryMaps = mutableMapOf<String, CategoryMap>()
            file.forEachSection { lines ->
                if (values.isEmpty()) {
                    // Read initial values from the first line
                    values = lines[0].parseLongs()
                } else {
                    // Read category maps from subsequent sections
                    val categoryMap = CategoryMap.fromLines(lines)
                    categoryMaps[categoryMap.sourceCategory] = categoryMap
                }
            }
            return Pair(values, Almanac(categoryMaps))
        }
    }
}
