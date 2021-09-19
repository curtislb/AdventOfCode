package com.curtislb.adventofcode.year2020.day16.ticket

/**
 * TODO
 */
class Ticket(val fieldValues: List<Int>) {
    /**
     * TODO
     */
    private val fieldNames: MutableList<String?> = MutableList(fieldValues.size) { null }

    /**
     * TODO
     */
    private val nameToFieldMap: MutableMap<String, Int> = mutableMapOf()

    /**
     * TODO
     */
    val fieldCount: Int get() = fieldValues.size

    /**
     * TODO
     */
    val fields: List<Pair<String?, Int>>
        get() = fieldValues.mapIndexed { index, value -> Pair(fieldNames[index], value) }

    /**
     * TODO
     */
    fun assignFieldName(fieldIndex: Int, name: String) {
        require(fieldIndex in fieldValues.indices) { "Field index not in range ${fieldValues.indices}: $fieldIndex" }

        fieldNames[fieldIndex]?.let { nameToFieldMap.remove(it) }

        fieldNames[fieldIndex] = name
        nameToFieldMap[name] = fieldIndex
    }

    /**
     * TODO
     */
    operator fun get(fieldIndex: Int): Int = fieldValues[fieldIndex]

    /**
     * TODO
     */
    operator fun get(fieldName: String): Int? = nameToFieldMap[fieldName]?.let { fieldValues[it] }

    override fun toString(): String {
        return fields.joinToString(separator = "\n") { (name, value) -> "${name ?: UNKNOWN_FIELD_STRING}: $value" }
    }

    companion object {
        /**
         * TODO
         */
        private const val UNKNOWN_FIELD_STRING = "???"
    }
}
