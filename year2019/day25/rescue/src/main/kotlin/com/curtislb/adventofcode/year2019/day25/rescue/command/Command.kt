package com.curtislb.adventofcode.year2019.day25.rescue.command

/**
 * A single command that may be given to the rescue droid.
 */
interface Command {
    /**
     * The string for this command, which can be as an ASCII instruction to the rescue droid.
     */
    override fun toString(): String
}
