package com.curtislb.adventofcode.year2020.day13.bus

/**
 * A schedule that lists the IDs of in-service buses at various offsets.
 *
 * All in-service buses initially depart at timestamp 0, and the ID of each bus indicates the time
 * between this and each
 * subsequent departure.
 *
 * @param schedule A string representation of the schedule, with integer bus IDs (or out-of-service
 *  buses, represented by `x`) separated by commas.
 */
class BusSchedule(schedule: String) {
    /**
     * A map from the schedule index of each in-service bus to its ID.
     */
    val busIDs: Map<Int, Long> = mutableMapOf<Int, Long>().apply {
        schedule.trim().split(',').forEachIndexed { index, busIDString ->
            if (busIDString != "x") {
                this[index] = busIDString.toLong()
            }
        }
    }

    /**
     * Finds the first bus to depart after (or at) [arrivalTime] and returns a pair containing its
     * ID and the number of minutes after [arrivalTime] that it departs.
     */
    fun findShortestWait(arrivalTime: Long): Pair<Long, Long>? {
        return busIDs.values.map { Pair(it, it - (arrivalTime % it)) }.minByOrNull { it.second }
    }

    /**
     * Returns the earliest time `t` (in minutes) such that each in-service bus with schedule index
     * `i` departs at time `t + i`.
     */
    fun findEarliestAlignedDepartureTime(): Long {
        val (time, _) = busIDs.entries.sortedBy { it.value }
            .foldRight(Pair(0L, 1L)) { (offset, busID), (time, step) ->
                var newTime = time
                var remainder = (time + offset) % busID
                while (remainder != 0L) {
                    newTime += step
                    remainder = (remainder + step) % busID
                }
                Pair(newTime, step * busID)
            }
        return time
    }
}
