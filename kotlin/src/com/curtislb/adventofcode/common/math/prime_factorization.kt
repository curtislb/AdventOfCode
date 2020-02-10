package com.curtislb.adventofcode.common.math

import kotlin.math.sqrt

/**
 * Returns a map from each factor in this number's prime factorization to its corresponding power.
 */
fun Long.primeFactorization(): Map<Long, Int> {
    assert(this > 0L)

    // Search for prime factors below sqrt(n).
    var n = this
    var factor = 2L
    val factorization = mutableMapOf<Long, Int>()
    while (factor < sqrt(n.toDouble()).toLong() + 1L) {
        // Find power by repeatedly dividing n by factor.
        var count = 0
        while (n % factor == 0L) {
            n /= factor
            count++
        }
        if (count > 0) {
            factorization[factor] = count
        }
        factor++
    }

    // If n is not fully divided, its remainder must be prime.
    if (n > 1L) {
        factorization[n] = 1
    }

    return factorization
}
