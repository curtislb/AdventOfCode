package com.adventofcode.curtislb.year2019.day04.password

import com.adventofcode.curtislb.common.math.Digits

/**
 * TODO
 */
class SatisfiesAllGenerator(private val generators: List<PasswordGenerator>) : PasswordGenerator() {
    constructor(vararg generators: PasswordGenerator) : this(generators.toList())

    override val isValid: Boolean by lazy { generators.all { it.isValid } }

    override val nextDigits: Set<Int> by lazy {
        generators.fold(Digits.ALL) { digitSet, generator -> digitSet.intersect(generator.nextDigits) }
    }

    override fun addDigit(digit: Int): PasswordGenerator {
        return SatisfiesAllGenerator(generators.map { it.addDigit(digit) })
    }
}
