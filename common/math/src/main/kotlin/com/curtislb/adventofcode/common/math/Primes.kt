package com.curtislb.adventofcode.common.math

import kotlin.math.sqrt

/**
 * Returns a map from each factor in this number's prime factorization to its corresponding power.
 *
 * Because the number 1 has no prime factors, an empty map is returned in this case.
 *
 * @throws IllegalArgumentException If this number is negative or 0.
 */
fun Long.primeFactorization(): Map<Long, Int> {
    require(this > 0L) { "Number must be positive: $this" }

    // Search for prime factors below sqrt(n).
    var n = this
    var factor = 2L
    val factorization = mutableMapOf<Long, Int>()
    while (factor <= sqrt(n.toDouble()).toLong()) {
        // Find power by repeatedly dividing n by factor.
        var power = 0
        while (n % factor == 0L) {
            n /= factor
            power++
        }

        // Record power for current factor and continue.
        if (power > 0) {
            factorization[factor] = power
        }
        factor++
    }

    // If n is not fully divided, its remainder must be prime.
    if (n > 1L) {
        factorization[n] = 1
    }

    return factorization
}
