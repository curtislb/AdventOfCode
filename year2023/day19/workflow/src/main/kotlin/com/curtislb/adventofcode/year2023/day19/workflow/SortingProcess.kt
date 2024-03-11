package com.curtislb.adventofcode.year2023.day19.workflow

import com.curtislb.adventofcode.common.collection.mapToMap
import com.curtislb.adventofcode.common.graph.UnweightedGraph

/**
 * A process for sending a [MachinePart] through a series of workflows that will ultimately accept
 * or reject the part.
 *
 * @param workflows A sequence of workflows that make up the sorting process.
 *
 * @constructor Creates a new instance of [SortingProcess] with the given `workflows`.
 */
class SortingProcess(workflows: Iterable<Workflow>) {
    /**
     * A map from the name of each workflow in the sorting process to that workflow.
     */
    private val workflowMap: Map<String, Workflow> = workflows.mapToMap { it.name to it }

    /**
     * A graph with edges from each step in the sorting process to possible next steps.
     */
    private val sortingStepGraph = object : UnweightedGraph<SortingStep>() {
        override fun getNeighbors(node: SortingStep): Iterable<SortingStep> {
            // Step with negative index connects to first step in workflow
            if (node.ruleIndex < 0) {
                return listOf(node.copy(ruleIndex = 0))
            }

            // Look up name by workflow, and check if it's accept/reject
            val workflow = getWorkflow(node.workflowName)
            if (workflow.isFinal()) {
                return emptyList()
            }

            // Add next step for when the current workflow rule matches a part
            val nextWorkflowIfMatch = workflow.rules[node.ruleIndex].nextWorkflow
            val nextStepIfMatch = SortingStep(workflowName = nextWorkflowIfMatch, ruleIndex = 0)
            val neighbors = mutableListOf(nextStepIfMatch)

            // Add next step for when the current workflow does not match a part
            if (node.ruleIndex != workflow.rules.lastIndex) {
                val nextStepIfNoMatch = node.copy(ruleIndex = node.ruleIndex + 1)
                neighbors.add(nextStepIfNoMatch)
            }

            return neighbors
        }
    }

    /**
     * Returns `true` if the given [part] is accepted by the sorting process, starting from the
     * workflow named [startWorkflow].
     *
     * @throws IllegalArgumentException If [startWorkflow] does not match any workflow in the
     *  sorting process.
     */
    fun isAccepted(part: MachinePart, startWorkflow: String): Boolean {
        require(startWorkflow in workflowMap) { "Invalid starting workflow: $startWorkflow" }

        // Send the part through workflows until it is accepted/rejected
        var workflow = getWorkflow(startWorkflow)
        while (!workflow.isFinal()) {
            // Search for the first rule that matches the part
            var foundMatch = false
            for (rule in workflow.rules) {
                if (rule.condition.isMatch(part)) {
                    workflow = getWorkflow(rule.nextWorkflow)
                    foundMatch = true
                    break
                }
            }

            // No rule matched the part (shouldn't be possible)
            if (!foundMatch) {
                error("Part not accepted or rejected: $part")
            }
        }

        // Check if the part is accepted
        return workflow == ACCEPT
    }

    /**
     * Returns the number of distinct [MachinePart]s with category ratings in the specified
     * [ratingRange] that would be accepted by the sorting process, starting from the workflow named
     * [startWorkflow].
     *
     * @throws IllegalArgumentException If [startWorkflow] does not match any workflow in the
     *  sorting process.
     */
    fun countAccepted(ratingRange: IntRange, startWorkflow: String): Long {
        require(startWorkflow in workflowMap) { "Invalid starting workflow: $startWorkflow" }

        // Find all paths through the process that end with a part being accepted
        val source = SortingStep(workflowName = startWorkflow, ruleIndex = -1)
        val goal = SortingStep(workflowName = ACCEPT_NAME, ruleIndex = 0)
        val pathsMap = sortingStepGraph.dfsPaths(source) { it.workflowName == ACCEPT_NAME }
        val paths = pathsMap[goal] ?: return 0L

        // Create initial constraints for the full range of valid ratings
        val initialConstraints =
            PartConstraints(x = ratingRange, m = ratingRange, a = ratingRange, s = ratingRange)

        // Determine the constraints needed for a part to follow each path
        val allConstraints = paths.map { path ->
            path.foldIndexed(initialConstraints) { stepIndex, constraints, step ->
                if (stepIndex == path.lastIndex) {
                    // Any part that reaches the last step is accepted
                    constraints
                } else {
                    // Impose additional constraints needed to follow the next step
                    val workflow = getWorkflow(step.workflowName)
                    val rule = workflow.rules[step.ruleIndex]
                    val isMatch = path[stepIndex + 1].ruleIndex == 0
                    when (val condition = rule.condition) {
                        // Add max constraint if "less" rule matches, or min constraint otherwise
                        is Condition.Less -> {
                            val (category, threshold) = condition
                            if (isMatch) {
                                constraints.withMaxConstraint(category, threshold - 1)
                            } else {
                                constraints.withMinConstraint(category, threshold)
                            }
                        }

                        // Add min constraint if "more" rule matches, or max constraint otherwise
                        is Condition.More -> {
                            val (category, threshold) = condition
                            if (isMatch) {
                                constraints.withMinConstraint(category, threshold + 1)
                            } else {
                                constraints.withMaxConstraint(category, threshold)
                            }
                        }

                        // Don't add constraints if rule always matches
                        Condition.True -> constraints
                    }
                }
            }
        }

        // Add together constraints for all paths, which must be unique
        return allConstraints.sumOf { it.countParts() }
    }

    /**
     * Returns the workflow in this process with the given [name].
     *
     * @throws IllegalArgumentException If [name] does not match any workflow in the sorting
     *  process.
     */
    private fun getWorkflow(name: String): Workflow = when (name) {
        ACCEPT_NAME -> ACCEPT
        REJECT_NAME -> REJECT
        else -> workflowMap[name] ?: throw IllegalArgumentException("Unknown workflow name: $name")
    }

    companion object {
        /**
         * Name of the workflow in a sorting process that accepts all parts.
         */
        private const val ACCEPT_NAME = "A"

        /**
         * Name of the workflow in a sorting process that rejects all parts.
         */
        private const val REJECT_NAME = "R"

        /**
         * A sorting process workflow that accepts all parts.
         */
        private val ACCEPT = Workflow(name = ACCEPT_NAME, rules = emptyList())

        /**
         * A sorting process workflow that rejects all parts.
         */
        private val REJECT = Workflow(name = REJECT_NAME, rules = emptyList())

        /**
         * Returns `true` if the workflow is the final step in a sorting process.
         */
        private fun Workflow.isFinal(): Boolean = this == ACCEPT || this == REJECT
    }

    /**
     * A single step in the sorting process for a [MachinePart].
     *
     * @property workflowName The name of the current workflow.
     * @property ruleIndex The current workflow rule index.
     *
     * @constructor Creates a new instance of [SortingStep] with the given [workflowName] and
     *  [ruleIndex].
     */
    private data class SortingStep(val workflowName: String, val ruleIndex: Int)
}
