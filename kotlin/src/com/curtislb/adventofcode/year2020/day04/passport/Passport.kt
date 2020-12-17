package com.curtislb.adventofcode.year2020.day04.passport

import com.curtislb.adventofcode.common.io.forEachSection
import java.io.File

/**
 * A passport containing data for various fields.
 *
 * @param birthYear The year that the passport holder was born.
 * @param issueYear The year that the passport was issued.
 * @param expirationYear The year that the passport expires.
 * @param height The height of the passport holder.
 * @param hairColor The hair color of the passport holder.
 * @param eyeColor The eye color of the passport holder.
 * @param passportID The ID of the passport.
 * @param countryID The ID of the country that issued the passport.
 */
data class Passport(
    val birthYear: String,
    val issueYear: String,
    val expirationYear: String,
    val height: String,
    val hairColor: String,
    val eyeColor: String,
    val passportID: String,
    val countryID: String?
) {
    /**
     * Checks if all fields of this passport are valid, according to the following rules:
     * - [birthYear] must be at least 1920 and at most 2002.
     * - [issueYear] must be at least 2010 and at most 2020.
     * - [expirationYear] must be at least 2020 and at most 2030.
     * - [height] must be a number followed by either `cm` or `in`:
     *     - If `cm`, the number must be at least 150 and at most 193.
     *     - If `in`, the number must be at least 59 and at most 76.
     * - [hairColor] must consist of a `'#'` followed by exactly six lowercase hexadecimal digits.
     * - [eyeColor] must be one of `"amb"`, `"blu"`, `"brn"`, `"gry"`, `"grn"`, `"hzl"`, or `"oth"`.
     * - [passportID] must be a nine-digit number, including any leading zeroes.
     * - [countryID], if present, can be any value.
     */
    fun isValid(): Boolean {
        return isValidBirthYear(birthYear) &&
            isValidIssueYear(issueYear) &&
            isValidExpirationYear(expirationYear) &&
            isValidHeight(height) &&
            isValidHairColor(hairColor) &&
            isValidEyeColor(eyeColor) &&
            isValidPassportID(passportID)
    }

    companion object {
        /**
         * The range of valid birth years for a passport.
         */
        private val BIRTH_YEAR_RANGE = 1920..2002

        /**
         * The range of valid issue years for a passport.
         */
        private val ISSUE_YEAR_RANGE = 2010..2020

        /**
         * The range of valid expiration years for a passport.
         */
        private val EXPIRATION_YEAR_RANGE = 2020..2030

        /**
         * The range of valid heights (in centimeters) for a passport.
         */
        private val HEIGHT_CM_RANGE = 150..193

        /**
         * The range of valid heights (in inches) for a passport.
         */
        private val HEIGHT_INCH_RANGE = 59..76

        /**
         * A regex matching a four-digit year.
         */
        private val YEAR_REGEX = Regex("""\d{4}""")

        /**
         * A regex matching a height value (in centimeters or inches).
         */
        private val HEIGHT_REGEX = Regex("""\d+(cm|in)""")

        /**
         * A regex matching a lowercase hex color.
         */
        private val COLOR_REGEX = Regex("""#[0-9a-f]{6}""")

        /**
         * A regex matching a valid passport ID.
         */
        private val PASSPORT_ID_REGEX = Regex("""\d{9}""")

        /**
         * Checks if [value] represents a valid birth year.
         */
        private fun isValidBirthYear(value: String): Boolean {
            return YEAR_REGEX.matches(value) && value.toInt() in BIRTH_YEAR_RANGE
        }

        /**
         * Checks if [value] represents a valid passport issue year.
         */
        private fun isValidIssueYear(value: String): Boolean {
            return YEAR_REGEX.matches(value) && value.toInt() in ISSUE_YEAR_RANGE
        }

        /**
         * Checks if [value] represents a valid passport expiration year.
         */
        private fun isValidExpirationYear(value: String): Boolean {
            return YEAR_REGEX.matches(value) && value.toInt() in EXPIRATION_YEAR_RANGE
        }

        /**
         * Checks if [value] represents a valid height.
         */
        fun isValidHeight(value: String): Boolean {
            // Height value must match one of the valid formats.
            if (!HEIGHT_REGEX.matches(value)) {
                return false
            }

            // Check if height is in the valid range for the given units.
            val height = value.substring(0, value.lastIndex - 1).toInt()
            return when (value.substring(value.lastIndex - 1)) {
                "cm" -> height in HEIGHT_CM_RANGE
                "in" -> height in HEIGHT_INCH_RANGE
                else -> false
            }
        }

        /**
         * Checks if [value] represents a valid hair color.
         */
        fun isValidHairColor(value: String): Boolean = COLOR_REGEX.matches(value)

        /**
         * Checks if [value] represents a valid eye color.
         */
        fun isValidEyeColor(value: String): Boolean = when (value) {
            "amb", "blu", "brn", "gry", "grn", "hzl", "oth" -> true
            else -> false
        }

        /**
         * Checks if [value] represents a valid passport ID.
         */
        fun isValidPassportID(value: String): Boolean = PASSPORT_ID_REGEX.matches(value)

        /**
         * Returns the passport corresponding to the given [string].
         *
         * @throws IllegalArgumentException If [string] is missing one or more required passport fields.
         */
        fun from(string: String): Passport {
            var birthYear: String? = null
            var issueYear: String? = null
            var expirationYear: String? = null
            var height: String? = null
            var hairColor: String? = null
            var eyeColor: String? = null
            var passportId: String? = null
            var countryId: String? = null

            // Map values to the corresponding passport fields.
            val fieldValues = string.trim().split(' ').map { it.split(':') }
            fieldValues.forEach { tokens ->
                require (tokens.size == 2) { "Malformed field value string: ${tokens.joinToString(separator = ":")}" }
                val (field, value) = tokens
                when (field) {
                    "byr" -> birthYear = value
                    "iyr" -> issueYear = value
                    "eyr" -> expirationYear = value
                    "hgt" -> height = value
                    "hcl" -> hairColor = value
                    "ecl" -> eyeColor = value
                    "pid" -> passportId = value
                    "cid" -> countryId = value
                    else -> throw IllegalArgumentException("Unexpected field: $field")
                }
            }

            // Check that all required fields are present.
            require(birthYear != null &&
                issueYear != null &&
                expirationYear != null &&
                height != null &&
                hairColor != null &&
                eyeColor != null &&
                passportId != null
            ) {
                """
                    Missing one or more required fields:
                        birthYear = $birthYear
                        issueYear = $issueYear
                        expirationYear = $expirationYear
                        height = $height
                        hairColor = $hairColor
                        eyeColor = $eyeColor
                        passportId = $passportId
                """.trimIndent()
            }

            return Passport(
                birthYear!!,
                issueYear!!,
                expirationYear!!,
                height!!,
                hairColor!!,
                eyeColor!!,
                passportId!!,
                countryId
            )
        }

        /**
         * Returns the list of passports represented by the given batch [file].
         *
         * Each passport in [file] is represented as a sequence of `"$field:$value"` pairs separated by spaces and/or
         * newlines. Passports in [file] are separated by blank lines. The valid `field` strings are:
         * - `"byr"`, corresponding to [Passport.birthYear]
         * - `"iyr"`, corresponding to [Passport.issueYear]
         * - `"eyr"`, corresponding to [Passport.expirationYear]
         * - `"hgt"`, corresponding to [Passport.height]
         * - `"hcl"`, corresponding to [Passport.hairColor]
         * - `"ecl"`, corresponding to [Passport.eyeColor]
         * - `"pid"`, corresponding to [Passport.passportID]
         * - `"cid"`, corresponding to [Passport.countryID]
         *
         * Passports in [file] that are missing one or more required fields will be skipped and not appear in the list
         * returned by this function.
         */
        fun processBatch(file: File): List<Passport> {
            val passports = mutableListOf<Passport>()
            file.forEachSection { lines ->
                try {
                    val passport = from(lines.joinToString(separator = " "))
                    passports.add(passport)
                } catch (e: IllegalArgumentException) {
                    // Passport is missing required fields.
                }
            }
            return passports
        }
    }
}
