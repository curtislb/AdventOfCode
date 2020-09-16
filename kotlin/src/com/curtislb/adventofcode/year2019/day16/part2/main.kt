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
import com.curtislb.adventofcode.year2019.day16.fft.FastFftAlgorithm
import com.curtislb.adventofcode.year2019.day16.fft.readSignal
import java.nio.file.Path

/**
 * Returns the solution to the puzzle for 2019, day 16, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 * @param phaseCount The number of phases of FFT to run on the input signal.
 * @param signalRepeatCount The number of times the base signal is repeated to produce the full input signal.
 * @param offsetDigits The number of digits at the beginning of the original signal that represent the message offset.
 * @param messageLength The number of digits at the given offset in the final signal that represent the message.
 */
fun solve(
    inputPath: Path = pathToInput(year = 2019, day = 16),
    phaseCount: Int = 100,
    signalRepeatCount: Int = 10_000,
    offsetDigits: Int = 7,
    messageLength: Int = 8
): String {
    val baseSignal = inputPath.toFile().readSignal()
    val offset = baseSignal.subList(0, offsetDigits).joinToString(separator = "").toInt()
    val fastFft = FastFftAlgorithm(baseSignal, signalRepeatCount, offset).apply { run(phaseCount) }
    return fastFft.readFromOffset(messageLength).joinToString(separator = "")
}

fun main() {
    println(solve())
}
