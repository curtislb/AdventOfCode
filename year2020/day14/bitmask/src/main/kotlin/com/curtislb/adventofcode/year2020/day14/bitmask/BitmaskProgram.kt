package com.curtislb.adventofcode.year2020.day14.bitmask

/**
 * A program that tracks a bitmask and interprets instructions writing values to memory addresses.
 *
 * @param programString A string representation of the program, containing one statement per line.
 */
abstract class BitmaskProgram(private val programString: String) {
    /**
     * A map from each [Bit] value to its little-endian indices in the current bitmask.
     */
    protected var maskBits: Map<Bit, List<Int>> = emptyMap()

    /**
     * A map from each memory address with a non-zero value to its current value.
     */
    protected val memory: MutableMap<Long, Long> = mutableMapOf()

    /**
     * All memory addresses with non-zero values, along with their current values.
     */
    val nonzeroMemoryEntries: Set<Map.Entry<Long, Long>> get() = memory.entries

    /**
     * Whether the program has already been run.
     */
    private var isDone: Boolean = false

    /**
     * Returns the current value at the given memory [address].
     */
    operator fun get(address: Long): Long = memory.getOrDefault(address, 0L)

    /**
     * Updates the [value] at the given memory [address].
     */
    protected operator fun set(address: Long, value: Long) {
        if (value == 0L) {
            memory.remove(address)
        } else {
            memory[address] = value
        }
    }

    /**
     * Runs the program by processing statements in sequence if the program has not been run
     * previously.
     *
     * Each statement must match one of the following patterns:
     *
     * - `"mask = $bitmaskString"` - Calls [updateBitmask] to update the current bitmask for the
     *   program.
     * - `"mem[${addressString}] = $valueString"` - Calls [writeMemory] to update the value at the
     *   given memory address.
     */
    fun run() {
        if (!isDone) {
            for (line in programString.trim().lines()) {
                processStatement(line.trim())
            }
            isDone = true
        }
    }

    /**
     * Processes the given program [statement].
     *
     * See [run] for a list of statement types recognized by a bitmask program.
     *
     * @throws IllegalArgumentException If [statement] does not match a known statement type.
     */
    private fun processStatement(statement: String) {
        UPDATE_BITMASK_REGEX.matchEntire(statement)?.let { matchResult ->
            val bitmaskString = matchResult.groupValues[1]
            updateBitmask(bitmaskString)
        } ?: WRITE_MEMORY_REGEX.matchEntire(statement)?.let { matchResult ->
            val addressString = matchResult.groupValues[1]
            val valueString = matchResult.groupValues[2]
            writeMemory(addressString, valueString)
        } ?: throw IllegalArgumentException("Illegal statement: $statement")
    }

    /**
     * Updates the current program bitmask to match the given big-endian [bitmaskString].
     */
    private fun updateBitmask(bitmaskString: String) {
        maskBits = mutableMapOf<Bit, MutableList<Int>>().apply {
            bitmaskString.forEachIndexed { index, char ->
                getOrPut(Bit.from(char)) { mutableListOf() }.add(bitmaskString.lastIndex - index)
            }
        }
    }

    /**
     * Writes the value(s) represented by [valueString] to the memory address(es) represented by
     * [addressString].
     */
    abstract fun writeMemory(addressString: String, valueString: String)

    companion object {
        /**
         * A regex used to identify and parse [updateBitmask] statements.
         */
        private val UPDATE_BITMASK_REGEX = Regex("""mask = ([01X]+)""")

        /**
         * A regex used to identify and parse [writeMemory] statements.
         */
        private val WRITE_MEMORY_REGEX = Regex("""mem\[(\d+)] = (\d+)""")
    }
}
