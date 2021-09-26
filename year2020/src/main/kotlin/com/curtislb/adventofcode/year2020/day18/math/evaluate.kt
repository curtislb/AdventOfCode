package com.curtislb.adventofcode.year2020.day18.math

import com.curtislb.adventofcode.common.math.product
import com.curtislb.adventofcode.common.math.toDigit

/**
 * TODO
 */
fun evaluate(expression: String): Long {
    val results = mutableListOf<Long>()
    val operators = mutableListOf('(')
    expression.forEach { token ->
        when (token) {
            in '0'..'9' -> {
                val number = token.toDigit().toLong()
                val lastOperator = operators.removeLast()
                if (lastOperator == '(') {
                    results.add(number)
                } else {
                    val lastResult = results.last()
                    val newResult = applyOperation(lastOperator, lastResult, number)
                    results[results.lastIndex] = newResult
                }
            }

            '+', '*', '(' -> operators.add(token)

            ')' -> {
                val lastOperator = operators.removeLast()
                if (lastOperator != '(') {
                    val lastResult = results.removeLast()
                    val prevResult = results.last()
                    val newResult = applyOperation(lastOperator, lastResult, prevResult)
                    results[results.lastIndex] = newResult
                }
            }
        }
    }

    return results.last()
}

/**
 * TODO
 */
fun evaluateAdvanced(expression: String): Long {
    var simplifiedExpression = expression
    while (simplifiedExpression.contains('(')) {
        simplifiedExpression = PAREN_EXPR_REGEX.replace(simplifiedExpression) { matchResult ->
            val matchValue = matchResult.value
            evaluateAdvanced(matchValue.substring(1 until matchValue.lastIndex)).toString()
        }
    }

    return if (simplifiedExpression.contains('*')) {
        simplifiedExpression.split('*').map { evaluateAdvanced(it) }.product()
    } else {
        simplifiedExpression.split('+').map { it.trim().toLong() }.sum()
    }
}

private val PAREN_EXPR_REGEX = Regex("""\([^()]+\)""")

/**
 * TODO
 */
private fun applyOperation(operator: Char, number1: Long, number2: Long): Long = when (operator) {
    '+' -> number1 + number2
    '*' -> number1 * number2
    else -> throw IllegalArgumentException("Invalid operator: $operator")
}
