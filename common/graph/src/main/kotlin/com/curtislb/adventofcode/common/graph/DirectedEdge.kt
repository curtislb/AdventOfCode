package com.curtislb.adventofcode.common.graph

/**
 * An edge to [node] with the given [weight] in a weighted directed graph.
 */
data class DirectedEdge<out T>(val node: T, val weight: Long)
