package com.curtislb.adventofcode.year2021.day13.origami

/**
 * A direction in which part of an [OrigamiSheet] can be folded, according to a [FoldInstruction].
 */
enum class FoldDirection {
    /**
     * A direction indicating that part of the sheet should be folded left along a vertical axis.
     */
    LEFT,

    /**
     * A direction indicating that part of the sheet should be folded up along a horizontal axis.
     */
    UP;
}
