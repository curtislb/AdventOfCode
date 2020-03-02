package com.curtislb.adventofcode.year2019.day25.rescue.command

/**
 * A single command that may be given to the rescue droid.
 */
interface Command {
    /**
     * The string representing this command, which can be provided to the rescue robot as an ASCII instruction.
     */
    override fun toString(): String
}
