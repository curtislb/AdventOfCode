package com.curtislb.adventofcode.year2023.day20.pulse

import com.curtislb.adventofcode.common.collection.mapToMutableMap
import com.curtislb.adventofcode.common.io.mapLines
import java.io.File

/**
 * A network of connected [PulseModule]s that communicate by sending [Pulse] signals to one another.
 *
 * @param modules A collection of all modules in the network.
 *
 * @constructor Creates a new instance of [PulseProcessor] with the given `modules`.
 */
class PulseProcessor(modules: Iterable<PulseModule>) {
    /**
     * A map from the name of each module in the network to that module.
     */
    val moduleMap: Map<String, PulseModule> = modules
        .mapToMutableMap { it.name to it }
        .apply {
            val untypedModules = mutableSetOf<String>()

            for ((moduleName, module) in entries) {
                for (destinationName in module.destinations) {
                    when (val destinationModule = this[destinationName]) {
                        null -> {
                            untypedModules.add(destinationName)
                        }
                        is ConjunctionModule -> {
                            destinationModule.addInput(moduleName)
                        }
                        else -> {}
                    }
                }
            }

            for (moduleName in untypedModules) {
                this[moduleName] = PlainModule(moduleName, destinations = emptyList())
            }
        }

    /**
     * A callback that is invoked each time a signal is sent within the network.
     *
     * @see sendSignal
     */
    var signalListener: ((signal: PulseSignal) -> Unit)? = null

    /**
     * The queue of signals to be processed.
     */
    private val signalQueue = ArrayDeque<PulseSignal>()

    /**
     * Enqueues the specified [signal] to be sent to its intended destination module in the network.
     *
     * This function will invoke the current [signalListener] (if any), but the signal will not
     * propagate through the network unless [processUntilIdle] is subsequently called.
     */
    fun sendSignal(signal: PulseSignal) {
        signalQueue.addLast(signal)
        signalListener?.invoke(signal)
    }

    /**
     * Delivers all signals enqueued by [sendSignal] and propagates them throughout the network.
     *
     * This function will continue to deliver new signals produced by modules in the network,
     * invoking the current [signalListener] (if any) for each signal sent, until all pending
     * signals have been processed.
     */
    fun processUntilIdle() {
        while (signalQueue.isNotEmpty()) {
            val signal = signalQueue.removeFirst()
            val module = moduleMap[signal.destination] ?: error("No module: ${signal.destination}")
            val output = module.processPulse(signal.source, signal.pulse)
            if (output != null) {
                for (destinationName in module.destinations) {
                    sendSignal(PulseSignal(module.name, output, destinationName))
                }
            }
        }
    }

    companion object {
        /**
         * Returns a [PulseProcessor] with modules read from the specified [file].
         *
         * The [file] must have the following format, where each `prefix` represents the
         * [PulseModule.prefix] of the source module represented by `sourceName` and each
         * `destinationsString` is a comma-and-space-separated list of destination module names:
         *
         * ```
         * $prefix1$moduleName1 -> $destinationsString1
         * $prefix2$moduleName2 -> $destinationsString2
         * ...
         * $prefixN$moduleNameN -> $destinationsStringN
         * ```
         *
         * @throws IllegalArgumentException If [file] is not formatted correctly.
         */
        fun fromFile(file: File): PulseProcessor {
            val modules = file.mapLines { PulseModule.fromString(it) }
            return PulseProcessor(modules)
        }
    }
}
