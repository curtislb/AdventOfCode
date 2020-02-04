package com.adventofcode.curtislb.common.graph

/**
 * An edge originating from a node in a weighted, directed graph.
 * @param node The node that is reached by following this [DirectedEdge].
 * @param weight The weight or cost associated with this [DirectedEdge] in the graph.
 */
data class DirectedEdge<out T>(val node: T, val weight: Long)
