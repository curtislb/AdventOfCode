/*
--- Part Two ---

Now that your FFT is working, you can decode the real signal.

The real signal is your puzzle input repeated 10000 times. Treat this new signal as a single input list. Patterns are
still calculated as before, and 100 phases of FFT are still applied.

The first seven digits of your initial input signal also represent the message offset. The message offset is the
location of the eight-digit message in the final output list. Specifically, the message offset indicates the number of
digits to skip before reading the eight-digit message. For example, if the first seven digits of your initial input
signal were 1234567, the eight-digit message would be the eight digits after skipping 1,234,567 digits of the final
output list. Or, if the message offset were 7 and your final output list were 98765432109876543210, the eight-digit
message would be 21098765. (Of course, your real message offset will be a seven-digit number, not a one-digit number
like 7.)

Here is the eight-digit message in the final output list after 100 phases. The message offset given in each input has
been highlighted. (Note that the inputs given below are repeated 10000 times to find the actual starting input lists.)

  - 03036732577212944063491565474664 becomes 84462026.
  - 02935109699940807407585447034323 becomes 78725270.
  - 03081770884921959731165446850517 becomes 53553731.

After repeating your input signal 10000 times and running 100 phases of FFT, what is the eight-digit message embedded
in the final output list?
*/

package com.curtislb.adventofcode.year2019.day16.part2

import com.curtislb.adventofcode.common.io.pathToInput
import com.curtislb.adventofcode.year2019.day16.fft.FastFFT
import com.curtislb.adventofcode.year2019.day16.fft.readSignal

/**
 * The path to the input file for this puzzle.
 */
private val INPUT_PATH = pathToInput(year = 2019, day = 16)

/**
 * The number of phases of FFT to run on the input signal.
 */
private const val PHASE_COUNT = 100

/**
 * The number of times the base signal is repeated to produce the full input signal.
 */
private const val SIGNAL_REPEAT_COUNT = 10_000

/**
 * The number of digits at the beginning of the original signal that represent the message offset.
 */
private const val OFFSET_DIGITS = 7

/**
 * The number of digits at the given offset in the final signal that represent the message.
 */
private const val MESSAGE_LENGTH = 8

// Answer: 85600369
fun main() {
    val baseSignal = INPUT_PATH.toFile().readSignal()
    val offset = baseSignal.subList(0, OFFSET_DIGITS).joinToString(separator = "").toInt()
    val fft = FastFFT(baseSignal, SIGNAL_REPEAT_COUNT, offset)
    fft.run(PHASE_COUNT)
    println(fft.readFromOffset(MESSAGE_LENGTH).joinToString(separator = ""))
}