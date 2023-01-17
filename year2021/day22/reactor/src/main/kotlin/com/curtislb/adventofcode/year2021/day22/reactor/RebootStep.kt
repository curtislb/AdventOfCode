package com.curtislb.adventofcode.year2021.day22.reactor

import com.curtislb.adventofcode.common.geometry.Cuboid

/**
 * A single step of the reactor reboot process.
 *
 * @param isOn Whether this step involves turning on a set of cubes (instead of off).
 * @param region The 3D region containing the cubes to be turned on or off during this step.
 */
data class RebootStep(val isOn: Boolean, val region: Cuboid) {
    companion object {
        /**
         * Regex used to parse reboot step strings.
         */
        private val STEP_REGEX =
            Regex("""(on|off) x=(-?\d+)\.\.(-?\d+),y=(-?\d+)\.\.(-?\d+),z=(-?\d+)\.\.(-?\d+)""")

        /**
         * Returns the [RebootStep] represented by the given [string].
         *
         * The [string] must have one of the following formats:
         *
         * - `"on x=$minX..$maxX,y=$minY..$maxY,z=$minZ..$maxZ"`: Turns on all cubes in the region
         * - `"off x=$minX..$maxX,y=$minY..$maxY,z=$minZ..$maxZ"`: Turns off all cubes in the region
         */
        fun fromString(string: String): RebootStep {
            val matchResult = STEP_REGEX.matchEntire(string)
            require(matchResult != null) { "Malformed reboot step string: $string" }
            val (onOff, xMin, xMax, yMin, yMax, zMin, zMax) = matchResult.destructured

            val isOn = onOff == "on"
            val region = Cuboid(
                xRange = xMin.toInt()..xMax.toInt(),
                yRange = yMin.toInt()..yMax.toInt(),
                zRange = zMin.toInt()..zMax.toInt()
            )
            return RebootStep(isOn, region)
        }
    }
}
