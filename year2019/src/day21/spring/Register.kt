package com.curtislb.adventofcode.year2019.day21.spring

/**
 * Value registers that may be referenced by a springscript program.
 *
 * @param isWritable Whether a springscript program can write new values to this register.
 * @param isExtended Whether this register is only available in extended sensor mode.
 */
enum class Register(val isWritable: Boolean, val isExtended: Boolean) {
    /**
     * A read-only register containing `true` if there is ground one space in front of the springdroid, or `false`
     * otherwise.
     */
    A(isWritable = false, isExtended = false),

    /**
     * A read-only register containing `true` if there is ground two spaces in front of the springdroid, or `false`
     * otherwise.
     */
    B(isWritable = false, isExtended = false),

    /**
     * A read-only register containing `true` if there is ground three spaces in front of the springdroid, or `false`
     * otherwise.
     */
    C(isWritable = false, isExtended = false),

    /**
     * A read-only register containing `true` if there is ground four spaces in front of the springdroid, or `false`
     * otherwise.
     */
    D(isWritable = false, isExtended = false),

    /**
     * A read-only register containing `true` if there is ground five spaces in front of the springdroid, or `false`
     * otherwise.
     *
     * This register is only available in extended sensor mode.
     */
    E(isWritable = false, isExtended = true),

    /**
     * A read-only register containing `true` if there is ground six spaces in front of the springdroid, or `false`
     * otherwise.
     *
     * This register is only available in extended sensor mode.
     */
    F(isWritable = false, isExtended = true),

    /**
     * A read-only register containing `true` if there is ground seven spaces in front of the springdroid, or `false`
     * otherwise.
     *
     * This register is only available in extended sensor mode.
     */
    G(isWritable = false, isExtended = true),

    /**
     * A read-only register containing `true` if there is ground eight spaces in front of the springdroid, or `false`
     * otherwise.
     *
     * This register is only available in extended sensor mode.
     */
    H(isWritable = false, isExtended = true),

    /**
     * A read-only register containing `true` if there is ground nine spaces in front of the springdroid, or `false`
     * otherwise.
     *
     * This register is only available in extended sensor mode.
     */
    I(isWritable = false, isExtended = true),

    /**
     * A temporary value register that can be read from or written to by a springscript program.
     *
     * The value of this register is initialized to `false` at the beginning of a springscript program.
     */
    T(isWritable = true, isExtended = false),

    /**
     * The jump register for a springscript program. If this register contains `true` at the end of the program, the
     * springdroid will jump a distance of four spaces. Otherwise, the springdroid will walk forward one space.
     *
     * The value of this register is initialized to `false` at the beginning of a springscript program.
     */
    J(isWritable = true, isExtended = false);
}
