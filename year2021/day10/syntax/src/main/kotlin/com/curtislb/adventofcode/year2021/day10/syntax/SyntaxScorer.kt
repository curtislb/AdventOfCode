package com.curtislb.adventofcode.year2021.day10.syntax

/**
 * An analyzer that processes and scores the syntax of navigation subsystem lines.
 *
 * @param lines A list of navigation subsystem lines, each of which must contain only the following
 *  characters: `(`, `)`, `[`, `]`, `{`, `}`, `<`, `>`
 *
 * @throws IllegalArgumentException If any line in `lines` does not match the required format.
 */
class SyntaxScorer(lines: List<String>) {
    /**
     * A map from each corrupted line index to the syntax error score for that line.
     *
     * A line is corrupted if it contains any closing bracket that does not match its corresponding
     * open bracket. The syntax error score of a corrupted line is determined by the first
     * mismatched closing bracket in that line, as follows:
     *
     * - `)`: 3 points
     * - `]`: 57 points
     * - `}`: 1197 points
     * - `>`: 25137 points
     */
    val syntaxErrorScores: Map<Int, Int>

    /**
     * A map from each non-corrupted line index to the completion score for that line.
     *
     * See [syntaxErrorScores] for the definition of a corrupted line.
     *
     * The completion score of a line is determined by considering, in order, each of the open
     * brackets with no matching closing bracket in that line. This score is initially 0, but for
     * each unclosed bracket, it is first multiplied by [COMPLETION_SCORE_MULTIPLIER], and then a
     * score corresponding to the missing bracket character is added to the result:
     *
     * - `)`: 1 point
     * - `]`: 2 points
     * - `}`: 3 points
     * - `>`: 4 points
     */
    val completionScores: Map<Int, Long>

    init {
        val currentErrorScores = mutableMapOf<Int, Int>()
        val currentCompletionScores = mutableMapOf<Int, Long>()

        lines.forEachIndexed { lineIndex, line ->
            // Iterate through the line, keeping track of unmatched open brackets
            val bracketStack = ArrayDeque<Char>()
            var errorChar: Char? = null
            for (char in line.trim()) {
                if (char.isOpenBracket()) {
                    // Found a new open bracket; push it onto the stack
                    bracketStack.addLast(char)
                } else if (bracketStack.lastOrNull()?.isClosedBy(char) == true) {
                    // Found a matching close bracket; pop its open bracket from the stack
                    bracketStack.removeLast()
                } else {
                    // Found an unexpected character; this line is corrupted
                    errorChar = char
                    break
                }
            }

            if (errorChar != null) {
                // Calculate the syntax error score for this corrupted line
                currentErrorScores[lineIndex] = errorChar.syntaxErrorScore()
            } else {
                // Calculate the completion score for this incomplete line
                var score = 0L
                while (bracketStack.isNotEmpty()) {
                    val bracket = bracketStack.removeLast()
                    score = score * COMPLETION_SCORE_MULTIPLIER + bracket.completionScore()
                }
                currentCompletionScores[lineIndex] = score
            }
        }

        syntaxErrorScores = currentErrorScores
        completionScores = currentCompletionScores
    }

    companion object {
        /**
         * The value by which a partial completion score should be multiplied before adding the
         * score for the next character.
         */
        private const val COMPLETION_SCORE_MULTIPLIER = 5L

        /**
         * Checks if this is an open bracket character.
         */
        private fun Char.isOpenBracket(): Boolean = when (this) {
            '(', '[', '{', '<' -> true
            else -> false
        }

        /**
         * Checks if this character is an open bracket with corresponding closing bracket [other].
         */
        private fun Char.isClosedBy(other: Char): Boolean = when (this) {
            '(' -> other == ')'
            '[' -> other == ']'
            '{' -> other == '}'
            '<' -> other == '>'
            else -> false
        }

        /**
         * Returns the partial syntax error score for this closing bracket.
         *
         * @throws IllegalArgumentException If this character is not a closing bracket.
         */
        private fun Char.syntaxErrorScore(): Int = when (this) {
            ')' -> 3
            ']' -> 57
            '}' -> 1197
            '>' -> 25137
            else -> throw IllegalArgumentException("No syntax error score for character: $this")
        }

        /**
         * Returns the partial completion score for this open bracket.
         *
         * @throws IllegalArgumentException If this character is not an open bracket.
         */
        private fun Char.completionScore(): Int = when (this) {
            '(' -> 1
            '[' -> 2
            '{' -> 3
            '<' -> 4
            else -> throw IllegalArgumentException("No completion score for character: $this")
        }
    }
}
