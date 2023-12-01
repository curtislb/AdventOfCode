package com.curtislb.adventofcode.common.simulation

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Tests the [GameOfLife] class.
 */
class GameOfLifeTest {
    @Test
    fun simulate_withArrayImpl_emptyState_zeroIterations_defaultShouldTerminate() {
        val initialState = emptyArray<BooleanArray>()
        val gameOfLife = GameOfLifeArrayImpl()

        val finalState = gameOfLife.simulate(initialState, maxIterations = 0)

        assertThat(finalState).isEmpty()
    }

    @Test
    fun simulate_withArrayImpl_emptyState_oneIteration_defaultShouldTerminate() {
        val initialState = emptyArray<BooleanArray>()
        val gameOfLife = GameOfLifeArrayImpl()

        val finalState = gameOfLife.simulate(initialState, maxIterations = 1)

        assertThat(finalState).isEmpty()
    }

    @Test
    fun simulate_withArrayImpl_emptyState_defaultIterations_shouldTerminateIfSameState() {
        val initialState = emptyArray<BooleanArray>()
        val gameOfLife = object : GameOfLifeArrayImpl() {
            override fun shouldTerminate(
                state: Array<BooleanArray>,
                previousState: Array<BooleanArray>
            ): Boolean {
                return state.contentDeepEquals(previousState)
            }
        }

        val finalState = gameOfLife.simulate(initialState)

        assertThat(finalState).isEmpty()
    }

    @Test
    fun simulate_withArrayImpl_deadCellState_zeroIterations_defaultShouldTerminate() {
        val initialState = arrayOf(booleanArrayOf(false))
        val gameOfLife = GameOfLifeArrayImpl()

        val finalState = gameOfLife.simulate(initialState, maxIterations = 0)

        assertThat(finalState).isDeepEqualTo(arrayOf(booleanArrayOf(false)))
    }

    @Test
    fun simulate_withArrayImpl_deadCellState_oneIteration_defaultShouldTerminate() {
        val initialState = arrayOf(booleanArrayOf(false))
        val gameOfLife = GameOfLifeArrayImpl()

        val finalState = gameOfLife.simulate(initialState, maxIterations = 1)

        assertThat(finalState).isDeepEqualTo(arrayOf(booleanArrayOf(false)))
    }

    @Test
    fun simulate_withArrayImpl_deadCellState_defaultIterations_shouldTerminateIfSameState() {
        val initialState = arrayOf(booleanArrayOf(false))
        val gameOfLife = object : GameOfLifeArrayImpl() {
            override fun shouldTerminate(
                state: Array<BooleanArray>,
                previousState: Array<BooleanArray>
            ): Boolean {
                return state.contentDeepEquals(previousState)
            }
        }

        val finalState = gameOfLife.simulate(initialState)

        assertThat(finalState).isDeepEqualTo(arrayOf(booleanArrayOf(false)))
    }

    @Test
    fun simulate_withArrayImpl_liveCellState_zeroIterations_defaultShouldTerminate() {
        val initialState = arrayOf(booleanArrayOf(true))
        val gameOfLife = GameOfLifeArrayImpl()

        val finalState = gameOfLife.simulate(initialState, maxIterations = 0)

        assertThat(finalState).isDeepEqualTo(arrayOf(booleanArrayOf(true)))
    }

    @Test
    fun simulate_withArrayImpl_liveCellState_oneIteration_defaultShouldTerminate() {
        val initialState = arrayOf(booleanArrayOf(true))
        val gameOfLife = GameOfLifeArrayImpl()

        val finalState = gameOfLife.simulate(initialState, maxIterations = 1)

        assertThat(finalState).isDeepEqualTo(arrayOf(booleanArrayOf(false)))
    }

    @Test
    fun simulate_withArrayImpl_liveCellState_defaultIterations_shouldTerminateIfSameState() {
        val initialState = arrayOf(booleanArrayOf(true))
        val gameOfLife = object : GameOfLifeArrayImpl() {
            override fun shouldTerminate(
                state: Array<BooleanArray>,
                previousState: Array<BooleanArray>
            ): Boolean {
                return state.contentDeepEquals(previousState)
            }
        }

        val finalState = gameOfLife.simulate(initialState)

        assertThat(finalState).isDeepEqualTo(arrayOf(booleanArrayOf(false)))
    }

    @Test
    fun simulate_withArrayImpl_multiCellState_zeroIterations_defaultShouldTerminate() {
        val initialState = arrayOf(
            booleanArrayOf(false, false, false, false),
            booleanArrayOf(false, true, true, true),
            booleanArrayOf(false, true, false, false)
        )
        val gameOfLife = GameOfLifeArrayImpl()

        val finalState = gameOfLife.simulate(initialState, maxIterations = 0)

        assertThat(finalState).isDeepEqualTo(
            arrayOf(
                booleanArrayOf(false, false, false, false),
                booleanArrayOf(false, true, true, true),
                booleanArrayOf(false, true, false, false)
            )
        )
    }

    @Test
    fun simulate_withArrayImpl_multiCellState_oneIteration_defaultShouldTerminate() {
        val initialState = arrayOf(
            booleanArrayOf(false, false, false, false),
            booleanArrayOf(false, true, true, true),
            booleanArrayOf(false, true, false, false)
        )
        val gameOfLife = GameOfLifeArrayImpl()

        val finalState = gameOfLife.simulate(initialState, maxIterations = 1)

        assertThat(finalState).isDeepEqualTo(
            arrayOf(
                booleanArrayOf(false, false, true, false),
                booleanArrayOf(false, true, true, false),
                booleanArrayOf(false, true, false, false)
            )
        )
    }

    @Test
    fun simulate_withArrayImpl_multiCellState_twoIterations_defaultShouldTerminate() {
        val initialState = arrayOf(
            booleanArrayOf(false, false, false, false),
            booleanArrayOf(false, true, true, true),
            booleanArrayOf(false, true, false, false)
        )
        val gameOfLife = GameOfLifeArrayImpl()

        val finalState = gameOfLife.simulate(initialState, maxIterations = 2)

        assertThat(finalState).isDeepEqualTo(
            arrayOf(
                booleanArrayOf(false, true, true, false),
                booleanArrayOf(false, true, true, false),
                booleanArrayOf(false, true, true, false)
            )
        )
    }

    @Test
    fun simulate_withArrayImpl_multiCellState_threeIterations_defaultShouldTerminate() {
        val initialState = arrayOf(
            booleanArrayOf(false, false, false, false),
            booleanArrayOf(false, true, true, true),
            booleanArrayOf(false, true, false, false)
        )
        val gameOfLife = GameOfLifeArrayImpl()

        val finalState = gameOfLife.simulate(initialState, maxIterations = 3)

        assertThat(finalState).isDeepEqualTo(
            arrayOf(
                booleanArrayOf(false, true, true, false),
                booleanArrayOf(true, false, false, true),
                booleanArrayOf(false, true, true, false)
            )
        )
    }

    @Test
    fun simulate_withArrayImpl_multiCellState_fourIterations_defaultShouldTerminate() {
        val initialState = arrayOf(
            booleanArrayOf(false, false, false, false),
            booleanArrayOf(false, true, true, true),
            booleanArrayOf(false, true, false, false)
        )
        val gameOfLife = GameOfLifeArrayImpl()

        val finalState = gameOfLife.simulate(initialState, maxIterations = 4)

        assertThat(finalState).isDeepEqualTo(
            arrayOf(
                booleanArrayOf(false, true, true, false),
                booleanArrayOf(true, false, false, true),
                booleanArrayOf(false, true, true, false)
            )
        )
    }

    @Test
    fun simulate_withArrayImpl_multiCellState_oneAndOneIteration_defaultShouldTerminate() {
        val initialState = arrayOf(
            booleanArrayOf(false, false, false, false),
            booleanArrayOf(false, true, true, true),
            booleanArrayOf(false, true, false, false)
        )
        val gameOfLife = GameOfLifeArrayImpl()

        val middleState = gameOfLife.simulate(initialState, maxIterations = 1)
        val finalState = gameOfLife.simulate(middleState, maxIterations = 1)

        assertThat(finalState).isDeepEqualTo(
            arrayOf(
                booleanArrayOf(false, true, true, false),
                booleanArrayOf(false, true, true, false),
                booleanArrayOf(false, true, true, false)
            )
        )
    }

    @Test
    fun simulate_withArrayImpl_multiCellState_defaultIterations_shouldTerminateIfSameState() {
        val initialState = arrayOf(
            booleanArrayOf(false, false, false, false),
            booleanArrayOf(false, true, true, true),
            booleanArrayOf(false, true, false, false)
        )
        val gameOfLife = object : GameOfLifeArrayImpl() {
            override fun shouldTerminate(
                state: Array<BooleanArray>,
                previousState: Array<BooleanArray>
            ): Boolean {
                return state.contentDeepEquals(previousState)
            }
        }

        val finalState = gameOfLife.simulate(initialState)

        assertThat(finalState).isDeepEqualTo(
            arrayOf(
                booleanArrayOf(false, true, true, false),
                booleanArrayOf(true, false, false, true),
                booleanArrayOf(false, true, true, false)
            )
        )
    }

    @Test
    fun simulate_withListImpl_emptyState_zeroIterations_defaultShouldTerminate() {
        val initialState = emptyList<List<Boolean>>()
        val gameOfLife = GameOfLifeListImpl()

        val finalState = gameOfLife.simulate(initialState, maxIterations = 0)

        assertThat(finalState).isEmpty()
    }

    @Test
    fun simulate_withListImpl_emptyState_oneIteration_defaultShouldTerminate() {
        val initialState = emptyList<List<Boolean>>()
        val gameOfLife = GameOfLifeListImpl()

        val finalState = gameOfLife.simulate(initialState, maxIterations = 1)

        assertThat(finalState).isEmpty()
    }

    @Test
    fun simulate_withListImpl_emptyState_defaultIterations_shouldTerminateIfSameState() {
        val initialState = emptyList<List<Boolean>>()
        val gameOfLife = object : GameOfLifeListImpl() {
            override fun shouldTerminate(
                state: List<List<Boolean>>,
                previousState: List<List<Boolean>>
            ): Boolean {
                return state == previousState
            }
        }

        val finalState = gameOfLife.simulate(initialState)

        assertThat(finalState).isEmpty()
    }

    @Test
    fun simulate_withListImpl_deadCellState_zeroIterations_defaultShouldTerminate() {
        val initialState = listOf(listOf(false))
        val gameOfLife = GameOfLifeListImpl()

        val finalState = gameOfLife.simulate(initialState, maxIterations = 0)

        assertThat(finalState).containsExactly(listOf(false))
    }

    @Test
    fun simulate_withListImpl_deadCellState_oneIteration_defaultShouldTerminate() {
        val initialState = listOf(listOf(false))
        val gameOfLife = GameOfLifeListImpl()

        val finalState = gameOfLife.simulate(initialState, maxIterations = 1)

        assertThat(finalState).containsExactly(listOf(false))
    }

    @Test
    fun simulate_withListImpl_deadCellState_defaultIterations_shouldTerminateIfSameState() {
        val initialState = listOf(listOf(false))
        val gameOfLife = object : GameOfLifeListImpl() {
            override fun shouldTerminate(
                state: List<List<Boolean>>,
                previousState: List<List<Boolean>>
            ): Boolean {
                return state == previousState
            }
        }

        val finalState = gameOfLife.simulate(initialState)

        assertThat(finalState).containsExactly(listOf(false))
    }

    @Test
    fun simulate_withListImpl_liveCellState_zeroIterations_defaultShouldTerminate() {
        val initialState = listOf(listOf(true))
        val gameOfLife = GameOfLifeListImpl()

        val finalState = gameOfLife.simulate(initialState, maxIterations = 0)

        assertThat(finalState).containsExactly(listOf(true))
    }

    @Test
    fun simulate_withListImpl_liveCellState_oneIteration_defaultShouldTerminate() {
        val initialState = listOf(listOf(true))
        val gameOfLife = GameOfLifeListImpl()

        val finalState = gameOfLife.simulate(initialState, maxIterations = 1)

        assertThat(finalState).containsExactly(listOf(false))
    }

    @Test
    fun simulate_withListImpl_liveCellState_defaultIterations_shouldTerminateIfSameState() {
        val initialState = listOf(listOf(true))
        val gameOfLife = object : GameOfLifeListImpl() {
            override fun shouldTerminate(
                state: List<List<Boolean>>,
                previousState: List<List<Boolean>>
            ): Boolean {
                return state == previousState
            }
        }

        val finalState = gameOfLife.simulate(initialState)

        assertThat(finalState).containsExactly(listOf(false))
    }

    @Test
    fun simulate_withListImpl_multiCellState_zeroIterations_defaultShouldTerminate() {
        val initialState = listOf(
            listOf(false, false, false, false),
            listOf(false, true, true, true),
            listOf(false, true, false, false)
        )
        val gameOfLife = GameOfLifeListImpl()

        val finalState = gameOfLife.simulate(initialState, maxIterations = 0)

        assertThat(finalState).containsExactly(
            listOf(false, false, false, false),
            listOf(false, true, true, true),
            listOf(false, true, false, false)
        )
    }

    @Test
    fun simulate_withListImpl_multiCellState_oneIteration_defaultShouldTerminate() {
        val initialState = listOf(
            listOf(false, false, false, false),
            listOf(false, true, true, true),
            listOf(false, true, false, false)
        )
        val gameOfLife = GameOfLifeListImpl()

        val finalState = gameOfLife.simulate(initialState, maxIterations = 1)

        assertThat(finalState).containsExactly(
            listOf(false, false, true, false),
            listOf(false, true, true, false),
            listOf(false, true, false, false)
        )
    }

    @Test
    fun simulate_withListImpl_multiCellState_twoIterations_defaultShouldTerminate() {
        val initialState = listOf(
            listOf(false, false, false, false),
            listOf(false, true, true, true),
            listOf(false, true, false, false)
        )
        val gameOfLife = GameOfLifeListImpl()

        val finalState = gameOfLife.simulate(initialState, maxIterations = 2)

        assertThat(finalState).containsExactly(
            listOf(false, true, true, false),
            listOf(false, true, true, false),
            listOf(false, true, true, false)
        )
    }

    @Test
    fun simulate_withListImpl_multiCellState_threeIterations_defaultShouldTerminate() {
        val initialState = listOf(
            listOf(false, false, false, false),
            listOf(false, true, true, true),
            listOf(false, true, false, false)
        )
        val gameOfLife = GameOfLifeListImpl()

        val finalState = gameOfLife.simulate(initialState, maxIterations = 3)

        assertThat(finalState).containsExactly(
            listOf(false, true, true, false),
            listOf(true, false, false, true),
            listOf(false, true, true, false)
        )
    }

    @Test
    fun simulate_withListImpl_multiCellState_fourIterations_defaultShouldTerminate() {
        val initialState = listOf(
            listOf(false, false, false, false),
            listOf(false, true, true, true),
            listOf(false, true, false, false)
        )
        val gameOfLife = GameOfLifeListImpl()

        val finalState = gameOfLife.simulate(initialState, maxIterations = 4)

        assertThat(finalState).containsExactly(
            listOf(false, true, true, false),
            listOf(true, false, false, true),
            listOf(false, true, true, false)
        )
    }

    @Test
    fun simulate_withListImpl_multiCellState_oneAndOneIteration_defaultShouldTerminate() {
        val initialState = listOf(
            listOf(false, false, false, false),
            listOf(false, true, true, true),
            listOf(false, true, false, false)
        )
        val gameOfLife = GameOfLifeListImpl()

        val middleState = gameOfLife.simulate(initialState, maxIterations = 1)
        val finalState = gameOfLife.simulate(middleState, maxIterations = 1)

        assertThat(finalState).containsExactly(
            listOf(false, true, true, false),
            listOf(false, true, true, false),
            listOf(false, true, true, false)
        )
    }

    @Test
    fun simulate_withListImpl_multiCellState_defaultIterations_shouldTerminateIfSameState() {
        val initialState = listOf(
            listOf(false, false, false, false),
            listOf(false, true, true, true),
            listOf(false, true, false, false)
        )
        val gameOfLife = object : GameOfLifeListImpl() {
            override fun shouldTerminate(
                state: List<List<Boolean>>,
                previousState: List<List<Boolean>>
            ): Boolean {
                return state == previousState
            }
        }

        val finalState = gameOfLife.simulate(initialState)

        assertThat(finalState).containsExactly(
            listOf(false, true, true, false),
            listOf(true, false, false, true),
            listOf(false, true, true, false)
        )
    }
}

/**
 * Sample mutable array implementation of a Game of Life process with "standard" update rules.
 */
private open class GameOfLifeArrayImpl
    : GameOfLife<Array<BooleanArray>, Pair<Int, Int>, Boolean>() {

    override fun getValue(state: Array<BooleanArray>, key: Pair<Int, Int>): Boolean =
        state[key.first][key.second]

    override fun setValue(
        state: Array<BooleanArray>,
        key: Pair<Int, Int>,
        value: Boolean
    ): Array<BooleanArray> {
        state[key.first][key.second] = value
        return state
    }

    override fun getUpdatableKeys(state: Array<BooleanArray>): Sequence<Pair<Int, Int>> {
        val keys = mutableSetOf<Pair<Int, Int>>()
        for (rowIndex in state.indices) {
            for (colIndex in state[rowIndex].indices) {
                if (state[rowIndex][colIndex]) {
                    val key = Pair(rowIndex, colIndex)
                    keys.add(key)
                    keys.addAll(getNeighboringKeys(state, key))
                }
            }
        }
        return keys.asSequence()
    }

    override fun getNeighboringKeys(
        state: Array<BooleanArray>,
        key: Pair<Int, Int>
    ): Sequence<Pair<Int, Int>> {
        val (rowIndex, colIndex) = key
        val rowIndices = state.indices
        val colIndices = state[rowIndex].indices
        return sequence {
            for (rowShift in -1..1) {
                for (colShift in -1..1) {
                    if (rowShift == 0 && colShift == 0) {
                        continue
                    }
                    val neighborRow = rowIndex + rowShift
                    val neighborCol = colIndex + colShift
                    if (neighborRow in rowIndices && neighborCol in colIndices) {
                        yield(Pair(neighborRow, neighborCol))
                    }
                }
            }
        }
    }

    override fun applyUpdateRules(value: Boolean, neighbors: Sequence<Boolean>): Boolean {
        val neighborCount = neighbors.count { it }
        return neighborCount == 3 || (value && neighborCount == 2)
    }

    override fun copyState(state: Array<BooleanArray>): Array<BooleanArray> =
        Array(state.size) { state[it].copyOf() }
}

/**
 * Sample immutable list implementation of a Game of Life process with "standard" update rules.
 */
private open class GameOfLifeListImpl
    : GameOfLife<List<List<Boolean>>, Pair<Int, Int>, Boolean>() {

    override fun getValue(state: List<List<Boolean>>, key: Pair<Int, Int>): Boolean =
        state[key.first][key.second]

    override fun setValue(
        state: List<List<Boolean>>,
        key: Pair<Int, Int>,
        value: Boolean
    ): List<List<Boolean>> {
        return List(state.size) { rowIndex ->
            List(state[rowIndex].size) { colIndex ->
                if (rowIndex == key.first && colIndex == key.second) {
                    value
                } else {
                    state[rowIndex][colIndex]
                }
            }
        }
    }

    override fun getUpdatableKeys(state: List<List<Boolean>>): Sequence<Pair<Int, Int>> {
        val keys = mutableSetOf<Pair<Int, Int>>()
        for (rowIndex in state.indices) {
            for (colIndex in state[rowIndex].indices) {
                if (state[rowIndex][colIndex]) {
                    val key = Pair(rowIndex, colIndex)
                    keys.add(key)
                    keys.addAll(getNeighboringKeys(state, key))
                }
            }
        }
        return keys.asSequence()
    }

    override fun getNeighboringKeys(
        state: List<List<Boolean>>,
        key: Pair<Int, Int>
    ): Sequence<Pair<Int, Int>> {
        val (rowIndex, colIndex) = key
        val rowIndices = state.indices
        val colIndices = state[rowIndex].indices
        return sequence {
            for (rowShift in -1..1) {
                for (colShift in -1..1) {
                    if (rowShift == 0 && colShift == 0) {
                        continue
                    }
                    val neighborRow = rowIndex + rowShift
                    val neighborCol = colIndex + colShift
                    if (neighborRow in rowIndices && neighborCol in colIndices) {
                        yield(Pair(neighborRow, neighborCol))
                    }
                }
            }
        }
    }

    override fun applyUpdateRules(value: Boolean, neighbors: Sequence<Boolean>): Boolean {
        val neighborCount = neighbors.count { it }
        return neighborCount == 3 || (value && neighborCount == 2)
    }
}
