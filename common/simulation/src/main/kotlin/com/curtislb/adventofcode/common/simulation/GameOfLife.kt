package com.curtislb.adventofcode.common.simulation

/**
 * A Game of Life simulation, consisting of a game state [S], and key-value pairs ([K], [V]).
 */
abstract class GameOfLife<S, K, V> {
    /**
     * Returns the value associated with the given [key] in the current game [state].
     */
    protected abstract fun getValue(state: S, key: K): V

    /**
     * Returns a copy of [state] with the given [key] mapped to the specified [value].
     *
     * If [state] is mutable, this method may update it in-place. An implementation that does this
     * *must* also provide a defensive copy function by overriding [copyState].
     */
    protected abstract fun setValue(state: S, key: K, value: V): S

    /**
     * Returns a (finite) sequence of all keys in the current game [state] that may be updated in
     * the next iteration.
     *
     * For convenience, the returned sequence may also contain keys that will *not* be updated in
     * the next iteration. However, this may increase the time needed to process the iteration.
     */
    protected abstract fun getUpdatableKeys(state: S): Sequence<K>

    /**
     * Returns a (finite) sequence of all keys that neighbor the given [key] in the current game
     * [state].
     */
    protected abstract fun getNeighboringKeys(state: S, key: K): Sequence<K>

    /**
     * Returns the result of applying update rules to a given [value] for one iteration, given the
     * current values of all its [neighbors].
     */
    protected abstract fun applyUpdateRules(value: V, neighbors: Sequence<V>): V

    /**
     * Returns a defensive copy of the given game [state].
     *
     * An implementation that modifies the current game state in-place in [setValue] *must* also
     * override this method.
     */
    protected open fun copyState(state: S): S = state

    /**
     * Returns `true` if the simulation should terminate, given the current [state] and
     * [previousState] of the game.
     */
    protected open fun shouldTerminate(state: S, previousState: S): Boolean = false

    /**
     * Returns the resulting game state after running the Game of Life simulation, starting from an
     * [initialState] and ending after [maxIterations] or when [shouldTerminate] returns `true`.
     */
    fun simulate(initialState: S, maxIterations: Int? = null): S {
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
            if (shouldTerminate(newState, state)) {
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
