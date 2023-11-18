package com.curtislb.adventofcode.common.number

/**
 * Finds the unique prime factorization of the natural number [n].
 *
 * Returns a map from each prime factor of [n] to that factor's corresponding power in the prime
 * factorization of [n]. If [n] is 1, this function instead returns an empty map.
 *
 * @throws IllegalArgumentException If the number is negative or 0.
 */
fun primeFactorization(n: Long): Map<Long, Int> {
    require(n > 0L) { "Number must be positive: $n" }

    // The number 1 has no prime factors
    if (n == 1L) {
        return emptyMap()
    }

    // Search for prime factors up to min(sqrt(Long.MAX_VALUE), sqrt(remainder))
    var factor = 2L
    var remainder = n
    val factorization = mutableMapOf<Long, Int>()
    while (factor <= 3037000499L && factor * factor <= remainder) {
        // Find this factor's power by dividing n
        var power = 0
        while (remainder % factor == 0L) {
            remainder /= factor
            power++
        }

        // Record this factor's power and continue
        if (power > 0) {
            factorization[factor] = power
        }

        // All factors above 2 must be odd
        if (factor == 2L) {
            factor = 3L
        } else {
            factor += 2L
        }
    }

    // A remainder greater than 1 must be prime
    if (remainder > 1L) {
        factorization[remainder] = 1
    }

    return factorization
}
