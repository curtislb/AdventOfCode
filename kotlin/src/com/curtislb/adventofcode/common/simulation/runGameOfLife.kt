package com.curtislb.adventofcode.common.simulation

/**
 * Returns the resulting state after running a "game of life" simulation with the given parameters.
 *
 * @param initialState The initial state of the game, before any update rules have been applied.
 * @param maxIterations The maximum number of times to update the current game state before returning. If `null`, this
 *  function will not return until [shouldTerminate] returns `true`.
 * @param shouldTerminate Checks if the simulation should terminate after each iteration by considering the previous and
 *  current game states after applying update rules.
 * @param copyState Returns a safely modifiable (or effectively immutable) copy of the game state.
 * @param getValue Returns the value associated with the given key for the current game state.
 * @param setValue Returns a copy of the game state with the given key associated with the given value.
 * @param getUpdatableKeys Returns all keys for the current game state whose values may change by applying update rules.
 * @param getNeighborKeys Returns all keys that neighbor the given key for the current game state.
 * @param applyUpdateRules Returns the result of applying any relevant update rules to the given value with its given
 *  neighbors.
 */
inline fun <S, K, V> runGameOfLife(
    initialState: S,
    maxIterations: Int? = null,
    shouldTerminate: (prevState: S, state: S) -> Boolean = { _, _ -> false },
    copyState: (state: S) -> S = { it },
    crossinline getValue: (state: S, key: K) -> V,
    setValue: (state: S, key: K, value: V) -> S,
    getUpdatableKeys: (state: S) -> Sequence<K>,
    getNeighborKeys: (state: S, key: K) -> Sequence<K>,
    applyUpdateRules: (value: V, neighbors: Sequence<V>) -> V
): S {
    var state = initialState

    // Run the simulation for the maximum number of iterations allowed.
    var remainingIterations = maxIterations
    while (remainingIterations == null || remainingIterations > 0) {
        // Create a safe copy of the current state.
        var newState = copyState(state)

        // Update each (key, value) pair in the new state simultaneously, based on the previous state.
        getUpdatableKeys(state).forEach { key ->
            val value = getValue(state, key)
            val neighbors = getNeighborKeys(state, key).map { getValue(state, it) }
            val newValue = applyUpdateRules(value, neighbors)
            newState = setValue(newState, key, newValue)
        }

        // Check if the simulation should terminate.
        if (shouldTerminate(state, newState)) {
            return newState
        }

        // Update the current state and iteration counter.
        state = newState
        if (remainingIterations != null) {
            remainingIterations--
        }
    }

    return state
}
