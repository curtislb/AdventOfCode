package com.curtislb.adventofcode.year2019.day04.password

import com.curtislb.adventofcode.common.math.DECIMAL_DIGITS

/**
 * A generator that produces numeric passwords that can be produced by *all* of the given [generators].
 */
class SatisfiesAllGenerator(private val generators: List<PasswordGenerator>) : PasswordGenerator() {
    /**
     * A generator that produces numeric passwords that can be produced by *all* of the given [generators].
     */
    constructor(vararg generators: PasswordGenerator) : this(generators.toList())

    override val isValid: Boolean by lazy { generators.all { it.isValid } }

    override val nextDigits: Set<Int> by lazy {
        generators.fold(DECIMAL_DIGITS) { digitSet, generator -> digitSet.intersect(generator.nextDigits) }
    }

    override fun addDigit(digit: Int): PasswordGenerator {
        return SatisfiesAllGenerator(generators.map { it.addDigit(digit) })
    }
}
