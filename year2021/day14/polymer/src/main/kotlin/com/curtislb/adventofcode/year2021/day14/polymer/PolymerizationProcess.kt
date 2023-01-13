package com.curtislb.adventofcode.year2021.day14.polymer

import com.curtislb.adventofcode.common.collection.Counter
import com.curtislb.adventofcode.common.collection.mapToMap
import com.curtislb.adventofcode.common.collection.minAndMaxByOrNull
import com.curtislb.adventofcode.common.io.forEachSection
import java.io.File

/**
 * The specifications for a polymerization process, consisting of an initial polymer template and a
 * collection of rules describing how that template changes during each step.
 *
 * @param template A string of elements (represented as [Char]s) that form the initial polymer.
 * @param insertionRuleStrings An unordered collection of strings, representing pair insertion rules
 *  that are applied to the current polymer during each step of the process. Each string must be of
 *  the form `"$A$B -> $C"`, where each of `A`, `B`, and `C` is a single non-whitespace [Char],
 *  representing an element. See [applyPairInsertions] for information on how the rules are applied.
 */
class PolymerizationProcess(val template: String, insertionRuleStrings: Collection<String>) {
    /**
     * A map from pairs of adjacent elements to the element that should be inserted between them
     * during each step of the process.
     */
    val insertionRules: Map<Pair<Char, Char>, Char> = insertionRuleStrings.mapToMap { ruleString ->
        val matchResult = INSERTION_RULE_REGEX.matchEntire(ruleString)
        require(matchResult != null) { "Malformed insertion rule string: $ruleString" }

        val (pairFirstString, pairSecondString, elementString) = matchResult.destructured
        val pair = Pair(pairFirstString.first(), pairSecondString.first())
        val element = elementString.first()
        pair to element
    }

    /**
     * A [Counter] of element [Char]s in the current polymer string.
     */
    private val elementCounts = Counter<Char>().apply { addAll(template.asIterable()) }

    /**
     * A [Counter] of adjacent element [Pair]s in the current polymer string.
     */
    private var pairCounts = Counter<Pair<Char, Char>>().apply {
        for (index in 0 until template.length - 1) {
            val element = template[index]
            val nextElement = template[index + 1]
            this[Pair(element, nextElement)]++
        }
    }

    /**
     * Runs the polymerization process for a number of steps equal to [stepCount].
     *
     * During each step of the process, the current polymer is modified by applying [insertionRules]
     * simultaneously to all adjacent element pairs in the polymer. Specifically, for each pair of
     * elements with a corresponding [Pair] key in [insertionRules], the [Char] value for that key
     * is inserted between those elements in the polymer.
     */
    fun applyPairInsertions(stepCount: Int = 1) {
        repeat(stepCount) {
            val newPairCounts = Counter<Pair<Char, Char>>()
            for ((pair, count) in pairCounts.entries) {
                val element = insertionRules[pair]
                if (element != null) {
                    newPairCounts[Pair(pair.first, element)] += count
                    newPairCounts[Pair(element, pair.second)] += count
                } else {
                    newPairCounts[pair] += count
                }
            }
            pairCounts = newPairCounts
        }

        elementCounts.clearNonzeroCounts()
        for ((pair, count) in pairCounts.entries) {
            elementCounts[pair.first] += count
        }
        elementCounts[template.last()]++
    }

    /**
     * Returns the difference in quantities between the most common element and least common element
     * in the current polymer.
     */
    fun maxElementCountDifference(): Long {
        val minMaxEntries = elementCounts.entries.minAndMaxByOrNull { it.value }
        val maxCount = minMaxEntries?.max?.value ?: 0L
        val minCount = minMaxEntries?.min?.value ?: 0L
        return maxCount - minCount
    }

    companion object {
        /**
         * A regex matching the string representation of a pair insertion rule.
         */
        private val INSERTION_RULE_REGEX = Regex("""\s*(\S)(\S)\s*->\s*(\S)\s*""")

        /**
         * Returns a [PolymerizationProcess] from a [file] with the following contents:
         *
         * ```
         * $template
         *
         * $insertionRuleString1
         * $insertionRuleString2
         * ...
         * $insertionRuleStringN
         * ```
         *
         * `template` is a string representing the initial polymer, and each `insertionRuleString`
         * must be a string of the form `"$A$B -> $C"`, representing an insertion rule.
         *
         * @throws IllegalArgumentException If the contents of [file] don't match the required
         *  format.
         */
        fun fromFile(file: File): PolymerizationProcess {
            val sections = mutableListOf<List<String>>()
            file.forEachSection { sections.add(it) }
            require(sections.size == 2) { "Malformed input file: $file" }

            val template = sections[0].first().trim()
            val insertionRuleStrings = sections[1]
            return PolymerizationProcess(template, insertionRuleStrings)
        }
    }
}
