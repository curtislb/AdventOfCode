package com.curtislb.adventofcode.year2019.day04.password

import com.curtislb.adventofcode.common.math.allDigits

/**
 * Generates passwords that simultaneously satisfy all criteria given by [generators].
 */
class SatisfiesAllGenerator(private val generators: List<PasswordGenerator>) : PasswordGenerator() {
    /**
     * Generates passwords that simultaneously satisfy all criteria given by [generators].
     */
    constructor(vararg generators: PasswordGenerator) : this(generators.toList())

    override val isValid: Boolean by lazy { generators.all { it.isValid } }

    override val nextDigits: Set<Int> by lazy {
        generators.fold(allDigits) { digitSet, generator -> digitSet.intersect(generator.nextDigits) }
    }

    override fun addDigit(digit: Int): PasswordGenerator {
        return SatisfiesAllGenerator(generators.map { it.addDigit(digit) })
    }
}
