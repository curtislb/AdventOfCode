/*
--- Part Two ---

The final machine responsible for moving the sand down to Island Island has a module attached named
`rx`. The machine turns on when a single low pulse is sent to `rx`.

Reset all modules to their default states. Waiting for all pulses to be fully handled after each
button press, what is the fewest number of button presses required to deliver a single low pulse to
the module named `rx`?
*/

package com.curtislb.adventofcode.year2023.day20.part2

import com.curtislb.adventofcode.common.collection.mapToMutableMap
import com.curtislb.adventofcode.common.number.leastCommonMultiple
import com.curtislb.adventofcode.year2023.day20.pulse.Pulse
import com.curtislb.adventofcode.year2023.day20.pulse.PulseProcessor
import com.curtislb.adventofcode.year2023.day20.pulse.PulseSignal
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2023, day 20, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 * @param initialSourceName The name of the module that sends a pulse for each button press.
 * @param initialDestinationName The name of the module that receives a pulse for each button press.
 * @param targetModuleName The name of the target module, which must receive a single low pulse.
 */
fun solve(
    inputPath: Path = Paths.get("..", "input", "input.txt"),
    initialSourceName: String = "button",
    initialDestinationName: String = "broadcaster",
    targetModuleName: String = "rx"
): Long {
    // Build the processor and find all grandparents of the target module
    val processor = PulseProcessor.fromFile(inputPath.toFile())
    val inputModules = processor.moduleMap.values.filter { module ->
        module.destinations.any { targetModuleName in processor.moduleMap[it]!!.destinations }
    }

    // Record the number of button presses for each module to send a high pulse
    val inputPeriodMap = inputModules.mapToMutableMap { it.name to 0L }
    var buttonCount = 0L
    processor.signalListener = { signal ->
        if (inputPeriodMap[signal.source] == 0L && signal.pulse == Pulse.HIGH) {
            inputPeriodMap[signal.source] = buttonCount
        }
    }

    // Press the button until all periods are found, then calculate their LCM
    val initialSignal = PulseSignal(initialSourceName, Pulse.LOW, initialDestinationName)
    while (inputPeriodMap.values.any { it == 0L }) {
        buttonCount++
        processor.sendSignal(initialSignal)
        processor.processUntilIdle()
    }
    return leastCommonMultiple(inputPeriodMap.values)
}

fun main() {
    println(solve())
}
