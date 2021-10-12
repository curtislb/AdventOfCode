package com.curtislb.adventofcode.year2019.day06.orbits

import java.io.File

/**
 * A collection of planets that may orbit one another.
 */
class Universe() {
    /**
     * A map from names to corresponding planets in the universe.
     */
    private val planets: MutableMap<String, Planet> = mutableMapOf()

    /**
     * A collection of planets that may orbit one another.
     *
     * @param file A file containing orbital data that is used to populate the universe. Each line
     *  of the file should contain a string like `"A)B"`, indicating that planet B orbits planet A.
     */
    constructor(file: File) : this() {
        file.forEachLine { line ->
            val (orbited, orbiter) = line.trim().split(')')
            addOrbit(orbited, orbiter)
        }
    }

    /**
     * Makes [orbited] a parent of [orbiter] in the orbital graph, adding each planet to the
     * universe if needed.
     */
    fun addOrbit(orbited: String, orbiter: String) {
        val parent = getOrAddPlanet(orbited)
        val child = getOrAddPlanet(orbiter)
        parent.children.add(child)
        child.parent = parent
    }

    /**
     * Returns the planet with [name] in the universe, creating and adding it if needed.
     */
    private fun getOrAddPlanet(name: String): Planet {
        return planets.getOrElse(name) {
            val planet = Planet(name)
            planets[name] = planet
            planet
        }
    }

    /**
     * Returns the number of direct and indirect orbits involving [center] and its descendants.
     */
    fun countOrbits(center: String): Int {
        val root = planets[center]
        return countOrbitsInternal(root, depth = 0)
    }

    /**
     * Recursive helper function for [countOrbits].
     */
    private fun countOrbitsInternal(root: Planet?, depth: Int): Int {
        return if (root == null) 0 else depth + root.children.sumOf {
            countOrbitsInternal(
                it,
                depth + 1
            )
        }
    }

    /**
     * Returns the minimum number of orbital transfers required for [start] to enter the same orbit
     * as [target], or `null` if this is not possible for the current orbital graph.
     */
    fun findOrbitalTransferDistance(start: String, target: String): Int? {
        // Both planets must be in the orbital graph.
        if (start !in planets || target !in planets) {
            return null
        }

        // If start and target are the same, no transfers are required.
        if (start == target) {
            return 0
        }

        // Search for the nearest common ancestor of start and target.
        var planetA = planets[start]
        var planetB = planets[target]
        var stepCount = 0
        val visited = mutableMapOf<String, Int>()
        while (planetA != null || planetB != null) {
            stepCount++

            // Step to planet A's parent, checking if it's been seen in planet B's ancestral chain.
            planetA = planetA?.parent
            if (planetA != null) {
                visited[planetA.name]?.let { return stepCount + it - 2 }
                visited[planetA.name] = stepCount
            }

            // Step to planet B's parent, checking if it's been seen in planet A's ancestral chain.
            planetB = planetB?.parent
            if (planetB != null) {
                visited[planetB.name]?.let { return stepCount + it - 2 }
                visited[planetB.name] = stepCount
            }
        }

        // No common ancestor found.
        return null
    }
}
