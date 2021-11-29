package com.curtislb.adventofcode.year2019.day21.spring

/**
 * A springscript program that can be interpreted and run by a springdroid.
 *
 * @param instructions An ordered list of instructions in the springscript program.
 */
class SpringScript private constructor(val instructions: List<String>) {
    override fun toString(): String = instructions.joinToString(separator = "\n")

    companion object {
        /**
         * Returns a springscript program with the given [extendedMode] setting and a [prepare]
         * function that provides instructions to the program [Builder].
         */
        fun create(extendedMode: Boolean = false, prepare: Builder.() -> Unit): SpringScript =
            Builder(extendedMode).apply(prepare).build()
    }

    /**
     * A builder for a springscript program.
     *
     * @param isExtendedMode Whether the resulting program will run in extended sensor mode.
     */
    class Builder(private val isExtendedMode: Boolean = false) {
        /**
         * An ordered list of instructions in the springscript program.
         */
        private val instructions: MutableList<String> = mutableListOf()

        /**
         * Appends an AND instruction to the springscript program and returns this builder.
         *
         * This instruction sets [register2] to `true` if both [register1] and [register2] are
         * `true`; otherwise, it sets [register2] to `false`.
         */
        fun and(register1: Register, register2: Register): Builder {
            checkSensorRange(register1, register2)
            checkIsWritable(register2)
            instructions.add("AND $register1 $register2")
            return this
        }

        /**
         * Appends an OR instruction to the springscript program and returns this builder.
         *
         * This instruction sets [register2] to `true` if at least one of [register1] and
         * [register2] is `true`; otherwise, it sets [register2] to `false`.
         */
        fun or(register1: Register, register2: Register): Builder {
            checkSensorRange(register1, register2)
            checkIsWritable(register2)
            instructions.add("OR $register1 $register2")
            return this
        }

        /**
         * Appends a NOT instruction to the springscript program and returns this builder.
         *
         * This instruction sets [register2] to `true` if [register1] is `false`; otherwise, it sets
         * [register2] to `false`.
         */
        fun not(register1: Register, register2: Register): Builder {
            checkSensorRange(register1, register2)
            checkIsWritable(register2)
            instructions.add("NOT $register1 $register2")
            return this
        }

        /**
         * Throws an [IllegalArgumentException] if not all [registers] are accessible in the current
         * sensor mode.
         */
        private fun checkSensorRange(vararg registers: Register) {
            if (!isExtendedMode) {
                for (register in registers) {
                    require(!register.isExtended) {
                        "Register is only available in extended sensor mode: $register"
                    }
                }
            }
        }

        /**
         * Returns a springscript program with either WALK or RUN appended to the current list of
         * instructions, according to the [isExtendedMode] flag.
         */
        fun build(): SpringScript {
            instructions.add(if (isExtendedMode) "RUN" else "WALK")
            return SpringScript(instructions)
        }

        companion object {
            /**
             * Throws an [IllegalArgumentException] if a springscript program can't write to the
             * given [register].
             */
            private fun checkIsWritable(register: Register) {
                require(register.isWritable) { "Can't write to read-only register: $register" }
            }
        }
    }
}
