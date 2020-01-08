package com.adventofcode.curtislb.year2019.day06.orbits

import java.io.File

/**
 * A collection of planets that may orbit one another.
 *
 * This constructor initializes an empty [Universe] to which planets can then be added via [addOrbit].
 */
class Universe() {
    /**
     * A [MutableMap] of names to planets in the [Universe].
     */
    private val planets: MutableMap<String, Planet> = mutableMapOf()

    /**
     * A collection of planets that may orbit one another.
     *
     * This constructor builds a [Universe] from a file containing orbital data, where each line consists of a string
     * such as `A)B`, indicating that planet B orbits planet A.
     */
    constructor(inputFile: File) : this() {
        inputFile.forEachLine { line ->
            val (orbited, orbiter) = line.trim().split(')')
            addOrbit(orbited, orbiter)
        }
    }

    /**
     * A [Map] of names to planets currently in the [Universe].
     */
    val planetMap: Map<String, Planet>
        get() = planets.toMap()

    /**
     * Adds information about a single orbital planet pair.
     *
     * For each new planet name given, a corresponding [Planet] is added to the [Universe].
     *
     * @param orbited The [Planet] that is orbited by [orbiter].
     * @param orbited A [Planet] that orbits [orbited].
     */
    fun addOrbit(orbited: String, orbiter: String) {
        val parent = getOrAddPlanet(orbited)
        val child = getOrAddPlanet(orbiter)
        parent.children.add(child)
        child.parent = parent
    }

    /**
     * Gets the [Planet] with [name] if is already in the [Universe], creating and adding it if not.
     * @param name The unique name of the [Planet] to be returned.
     * @return A [Planet] with the given name, now guaranteed to be in the [Universe].
.     */
    private fun getOrAddPlanet(name: String): Planet {
        val planet: Planet
        if (name in planets) {
            planet = planets[name]!!
        } else {
            planet = Planet(name)
            planets[name] = planet
        }
        return planet
    }

    /**
     * Counts all direct and indirect orbits starting from a central planet.
     * @param center The effective gravitational center [Planet] from which the number of orbits is counted.
     * @return The number of direct and indirect orbits involving only [center] and its orbital descendants.
     */
    fun countOrbits(center: String): Int {
        val root = planets[center]
        return countOrbitsInternal(root, depth = 0)
    }

    /**
     * Recursive helper function for [countOrbits].
     * @param root The root [Planet] node of an orbital subtree.
     * @param depth The current recursive call depth.
     * @return The partial sum of orbit counts from [root].
     */
    private fun countOrbitsInternal(root: Planet?, depth: Int): Int {
        return if (root == null) 0 else depth + root.children.sumBy { countOrbitsInternal(it, depth + 1) }
    }

    /**
     * Finds the number of orbital edges that must be crossed to travel from one planet to another.
     * @param start The starting [Planet] from which distance will be calculated.
     * @param target The target [Planet] to which distance will be calculated.
     * @return The minimum number of orbital edges between [start] and [target].
     */
    fun findOrbitalDistance(start: String, target: String): Int? {
        var planetA = planets[start]
        var planetB = planets[target]
        var stepCount = 0
        val visited: MutableMap<String, Int> = mutableMapOf()
        while (planetA != null || planetB != null) {
            stepCount++

            // Step to planet A's parent, checking if it has been seen in planet B's ancestral chain.
            planetA = planetA?.parent
            if (planetA != null) {
                if (planetA.name in visited) {
                    return stepCount + visited[planetA.name]!!
                }
                visited[planetA.name] = stepCount
            }

            // Step to planet B's parent, checking if it has been seen in planet A's ancestral chain.
            planetB = planetB?.parent
            if (planetB != null) {
                if (planetB.name in visited) {
                    return stepCount + visited[planetB.name]!!
                }
                visited[planetB.name] = stepCount
            }
        }
        return null
    }
}
