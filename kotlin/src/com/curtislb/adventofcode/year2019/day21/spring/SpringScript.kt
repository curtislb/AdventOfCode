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
         * Returns a builder for a springscript program. If [enableExtendedMode] is `true`, the resulting program will
         * run in extended sensor mode.
         */
        fun builder(enableExtendedMode: Boolean = false): Builder = Builder(enableExtendedMode)

        /**
         * A builder for a springscript program.
         *
         * @param isExtendedMode Whether the resulting program will run in extended sensor mode.
         */
        class Builder(private val isExtendedMode: Boolean) {
            /**
             * An ordered list of instructions in the springscript program.
             */
            private val instructions: MutableList<String> = mutableListOf()

            /**
             * Appends an AND instruction to the resulting springscript program and returns this builder.
             *
             * This instruction sets [register2] to `true` if both [register1] and [register2] are `true`; otherwise, it
             * sets [register2] to `false`.
             */
            fun and(register1: Register, register2: Register): Builder {
                checkSensorRange(register1, register2)
                checkIsWritable(register2)
                instructions.add("AND $register1 $register2")
                return this
            }

            /**
             * Appends an OR instruction to the resulting springscript program and returns this builder.
             *
             * This instruction sets [register2] to `true` if at least one of [register1] and [register2] is `true`;
             * otherwise, it sets [register2] to `false`.
             */
            fun or(register1: Register, register2: Register): Builder {
                checkSensorRange(register1, register2)
                checkIsWritable(register2)
                instructions.add("OR $register1 $register2")
                return this
            }

            /**
             * Appends a NOT instruction to the resulting springscript program and returns this builder.
             *
             * This instruction sets [register2] to `true` if [register1] is `false`; otherwise, it sets [register2] to
             * `false`.
             */
            fun not(register1: Register, register2: Register): Builder {
                checkSensorRange(register1, register2)
                checkIsWritable(register2)
                instructions.add("NOT $register1 $register2")
                return this
            }

            /**
             * Returns a springscript program with either WALK or RUN appended to the current list of instructions,
             * according to the [isExtendedMode] flag.
             */
            fun build(): SpringScript {
                instructions.add(if (isExtendedMode) "RUN" else "WALK")
                return SpringScript(instructions)
            }

            /**
             * Checks that all [registers] are accessible in the given springscript program mode.
             *
             * @throws IllegalArgumentException If one or more [registers] are not accessible in the springscript
             *  program mode specified by the [isExtendedMode] flag.
             */
            private fun checkSensorRange(vararg registers: Register) {
                if (!isExtendedMode) {
                    for (reg in registers) {
                        if (reg.isExtended) {
                            throw IllegalArgumentException("Register $reg is only available in extended sensor mode.")
                        }
                    }
                }
            }

            companion object {
                /**
                 * Checks that the given [register] can be written to by a springscript program.
                 *
                 * @throws IllegalArgumentException If [register] is read-only.
                 */
                private fun checkIsWritable(register: Register) {
                    if (!register.isWritable) {
                        throw IllegalArgumentException("Can't write to read-only register $register.")
                    }
                }
            }
        }
    }
}
