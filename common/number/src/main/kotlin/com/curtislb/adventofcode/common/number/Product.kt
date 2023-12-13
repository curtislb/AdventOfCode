package com.curtislb.adventofcode.common.number

import kotlin.experimental.ExperimentalTypeInference

/**
 * Returns the product of all values in this iterable, or 1 for an empty collection.
 */
fun Iterable<Int>.product(): Int = fold(1) { acc, num -> acc * num }

/**
 * Returns the product of all values in the collection, or 1 for an empty collection.
 */
fun Iterable<Long>.product(): Long = fold(1L) { acc, num -> acc * num }

/**
 * Returns the product of all values produced by the [selector] function applied to each element in
 * the collection, or 1 for an empty collection.
 */
@JvmName("productOfInt")
@OptIn(ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
inline fun <T> Iterable<T>.productOf(selector: (T) -> Int): Int {
    var product = 1
    for (value in this) {
        product *= selector(value)
    }
    return product
}

/**
 * Returns the product of all values produced by the [selector] function applied to each element in
 * the collection, or 1 for an empty collection.
 */
@JvmName("productOfLong")
@OptIn(ExperimentalTypeInference::class)
@OverloadResolutionByLambdaReturnType
inline fun <T> Iterable<T>.productOf(selector: (T) -> Long): Long {
    var product = 1L
    for (value in this) {
        product *= selector(value)
    }
    return product
}
