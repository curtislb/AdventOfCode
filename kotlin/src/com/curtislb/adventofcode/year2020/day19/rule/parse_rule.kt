package com.curtislb.adventofcode.year2020.day19.rule

/**
 * TODO
 */
private val LITERAL_RULE_REGEX = Regex("""".*"""")

/**
 * TODO
 */
fun parseRule(
    rule: String,
    ruleStrings: Map<Int, String>,
    parsedRules: MutableMap<Int, String> = mutableMapOf()
): String {
    return when {
        LITERAL_RULE_REGEX.matches(rule) -> rule.trim('"')

        '|' in rule -> rule.split('|').joinToString(separator = "|") { subRule ->
            "(?:${parseRule(subRule.trim(), ruleStrings, parsedRules)})"
        }

        else -> rule.split(' ').map { it.toInt() }.joinToString(separator = "") { ruleKey ->
            if (parsedRules[ruleKey] == null) {
                parsedRules[ruleKey] = parseRule(ruleStrings[ruleKey]!!, ruleStrings, parsedRules)
            }
            "(?:${parsedRules[ruleKey]})"
        }
    }
}
