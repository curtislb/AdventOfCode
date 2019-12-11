package com.adventofcode.curtislb.year2019.day04.password

import com.adventofcode.curtislb.common.math.allDigits

/**
 * Generates passwords that simultaneously satisfy all criteria given by a list of generators.
 * @param generators A list of all generators whose criteria must be satisfied for a password to be valid.
 */
class SatisfiesAllGenerator(private val generators: List<PasswordGenerator>) : PasswordGenerator() {
    /**
     * Generates passwords that simultaneously satisfy all criteria given by a list of generators.
     * @param generators A vararg list of all generators whose criteria must be satisfied for a password to be valid.
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
