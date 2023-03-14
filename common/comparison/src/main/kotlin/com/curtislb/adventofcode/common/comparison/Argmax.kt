package com.curtislb.adventofcode.common.comparison

/**
 * Returns the index of the greatest element in this iterable.
 *
 * @throws NoSuchElementException If the iterable contains no elements.
 */
fun <T : Comparable<T>> Iterable<T>.argmax(): Int = withIndex().maxBy { it.value }.index

/**
 * Returns the index of the element for which the [transform] function produces the greatest value.
 *
 * @throws NoSuchElementException If the iterable contains no elements.
 */
inline fun <T, R : Comparable<R>> Iterable<T>.argmaxBy(transform: (element: T) -> R): Int =
    withIndex().maxBy { transform(it.value) }.index
