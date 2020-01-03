package com.adventofcode.curtislb.common.math

/**
 * TODO
 */
fun Int.nextMultipleAbove(n: Int) = n + (this - (n % this))

/**
 * TODO
 */
fun Int.nextMultipleAtLeast(n: Int) = if (n % this == 0) n else this.nextMultipleAbove(n)
