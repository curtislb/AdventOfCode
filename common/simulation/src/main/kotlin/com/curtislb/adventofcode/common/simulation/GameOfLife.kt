package com.curtislb.adventofcode.common.simulation

/**
 * A Game of Life simulation, consisting of a game state [S], and key-value pairs ([K], [V]).
 */
abstract class GameOfLife<S, K, V> {
    /**
     * Returns the value associated with the given [key] in the current game [state].
     */
    abstract fun getValue(state: S, key: K): V

    /**
     * Returns a copy of [state] with the given [key] mapped to the given [value].
     *
     * If [state] is mutable, this method may update it in-place. An implementation that does this
     * *must* also provide a defensive copy function by overriding [copyState].
     */
    abstract fun setValue(state: S, key: K, value: V): S

    /**
     * Returns a (finite) sequence containing all keys in the current game [state] that may be
     * updated in the next iteration.
     *
     * Note that the returned sequence need not contain *only* keys that will be updated in the next
     * iteration. However, returning a larger sequence will increase the time needed to process
     * updates for the iteration.
     */
    abstract fun getUpdatableKeys(state: S): Sequence<K>

    /**
     * Returns a (finite) sequence of all keys "neighboring" a given [key] in the current game
     * [state].
     */
    abstract fun getNeighboringKeys(state: S, key: K): Sequence<K>

    /**
     * Returns the result of applying update rules to a given [value] for one iteration, given the
     * current values of all its [neighbors].
     */
    abstract fun applyUpdateRules(value: V, neighbors: Sequence<V>): V

    /**
     * Returns a defensive copy of the given game [state].
     *
     * An implementation that modifies the current game state in-place in [setValue] *must* also
     * override this method.
     */
    open fun copyState(state: S): S = state

    /**
     * Returns if the simulation should terminate, given the [previousState] and current [state] of
     * the game.
     */
    open fun shouldTerminate(previousState: S, state: S): Boolean = false

    /**
     * Runs the Game of Life simulation, starting from an [initialState] and ending after
     * [maxIterations] or when [shouldTerminate] is `true`, and returns the resulting game state.
     */
    fun runSimulation(initialState: S, maxIterations: Int? = null): S {
        var state = initialState

        // Run simulation for the maximum number of iterations
        var remainingIterations = maxIterations
        while (remainingIterations == null || remainingIterations > 0) {
            // Create a safe copy of the current state (if needed)
            var newState = copyState(state)

            // Simultaneously update each key-value pair
            for (key in getUpdatableKeys(state)) {
                val value = getValue(state, key)
                val neighbors = getNeighboringKeys(state, key).map { getValue(state, it) }
                val newValue = applyUpdateRules(value, neighbors)
                newState = setValue(newState, key, newValue)
            }

            // Check if the termination condition is satisfied
            if (shouldTerminate(state, newState)) {
                return newState
            }

            // Update the current state and continue the simulation
            state = newState
            if (remainingIterations != null) {
                remainingIterations--
            }
        }

        return state
    }
}
