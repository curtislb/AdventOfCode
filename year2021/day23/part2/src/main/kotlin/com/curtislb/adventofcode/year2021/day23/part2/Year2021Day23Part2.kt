/*
--- Part Two ---

As you prepare to give the amphipods your solution, you notice that the diagram they handed you was
actually folded up. As you unfold it, you discover an extra part of the diagram.

Between the first and second lines of text that contain amphipod starting positions, insert the
following lines:

```
  #D#C#B#A#
  #D#B#A#C#
```

So, the above example now becomes:

```
#############
#...........#
###B#C#B#D###
  #D#C#B#A#
  #D#B#A#C#
  #A#D#C#A#
  #########
```

The amphipods still want to be organized into rooms similar to before:

```
#############
#...........#
###A#B#C#D###
  #A#B#C#D#
  #A#B#C#D#
  #A#B#C#D#
  #########
```

In this updated example, the least energy required to organize these amphipods is 44169:

```
#############
#...........#
###B#C#B#D###
  #D#C#B#A#
  #D#B#A#C#
  #A#D#C#A#
  #########

#############
#..........D#
###B#C#B#.###
  #D#C#B#A#
  #D#B#A#C#
  #A#D#C#A#
  #########

#############
#A.........D#
###B#C#B#.###
  #D#C#B#.#
  #D#B#A#C#
  #A#D#C#A#
  #########

#############
#A........BD#
###B#C#.#.###
  #D#C#B#.#
  #D#B#A#C#
  #A#D#C#A#
  #########

#############
#A......B.BD#
###B#C#.#.###
  #D#C#.#.#
  #D#B#A#C#
  #A#D#C#A#
  #########

#############
#AA.....B.BD#
###B#C#.#.###
  #D#C#.#.#
  #D#B#.#C#
  #A#D#C#A#
  #########

#############
#AA.....B.BD#
###B#.#.#.###
  #D#C#.#.#
  #D#B#C#C#
  #A#D#C#A#
  #########

#############
#AA.....B.BD#
###B#.#.#.###
  #D#.#C#.#
  #D#B#C#C#
  #A#D#C#A#
  #########

#############
#AA...B.B.BD#
###B#.#.#.###
  #D#.#C#.#
  #D#.#C#C#
  #A#D#C#A#
  #########

#############
#AA.D.B.B.BD#
###B#.#.#.###
  #D#.#C#.#
  #D#.#C#C#
  #A#.#C#A#
  #########

#############
#AA.D...B.BD#
###B#.#.#.###
  #D#.#C#.#
  #D#.#C#C#
  #A#B#C#A#
  #########

#############
#AA.D.....BD#
###B#.#.#.###
  #D#.#C#.#
  #D#B#C#C#
  #A#B#C#A#
  #########

#############
#AA.D......D#
###B#.#.#.###
  #D#B#C#.#
  #D#B#C#C#
  #A#B#C#A#
  #########

#############
#AA.D......D#
###B#.#C#.###
  #D#B#C#.#
  #D#B#C#.#
  #A#B#C#A#
  #########

#############
#AA.D.....AD#
###B#.#C#.###
  #D#B#C#.#
  #D#B#C#.#
  #A#B#C#.#
  #########

#############
#AA.......AD#
###B#.#C#.###
  #D#B#C#.#
  #D#B#C#.#
  #A#B#C#D#
  #########

#############
#AA.......AD#
###.#B#C#.###
  #D#B#C#.#
  #D#B#C#.#
  #A#B#C#D#
  #########

#############
#AA.......AD#
###.#B#C#.###
  #.#B#C#.#
  #D#B#C#D#
  #A#B#C#D#
  #########

#############
#AA.D.....AD#
###.#B#C#.###
  #.#B#C#.#
  #.#B#C#D#
  #A#B#C#D#
  #########

#############
#A..D.....AD#
###.#B#C#.###
  #.#B#C#.#
  #A#B#C#D#
  #A#B#C#D#
  #########

#############
#...D.....AD#
###.#B#C#.###
  #A#B#C#.#
  #A#B#C#D#
  #A#B#C#D#
  #########

#############
#.........AD#
###.#B#C#.###
  #A#B#C#D#
  #A#B#C#D#
  #A#B#C#D#
  #########

#############
#..........D#
###A#B#C#.###
  #A#B#C#D#
  #A#B#C#D#
  #A#B#C#D#
  #########

#############
#...........#
###A#B#C#D###
  #A#B#C#D#
  #A#B#C#D#
  #A#B#C#D#
  #########
```

Using the initial configuration from the full diagram, what is the least energy required to organize
the amphipods?
*/

package com.curtislb.adventofcode.year2021.day23.part2

import com.curtislb.adventofcode.year2021.day23.amphipod.Amphipod
import com.curtislb.adventofcode.year2021.day23.amphipod.Burrow
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2021, day 23, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 * @param amphipodInsertions Lists of the amphipods to be added to each room, immediately after the
 *  first (innermost) amphipod in each room.
 */
fun solve(
    inputPath: Path = Paths.get("..", "input", "input.txt"),
    amphipodInsertions: List<List<Amphipod>> = listOf(
        listOf(Amphipod.DESERT, Amphipod.DESERT),
        listOf(Amphipod.BRONZE, Amphipod.COPPER),
        listOf(Amphipod.AMBER, Amphipod.BRONZE),
        listOf(Amphipod.COPPER, Amphipod.AMBER)
    )
): Long? {
    val initialBurrow = Burrow.fromString(inputPath.toFile().readText())

    // Insert additional amphipods into each room
    val fullRooms = amphipodInsertions.mapIndexed { index, amphipods ->
        val room = initialBurrow.rooms[index]
        room.subList(0, 1) + amphipods + room.subList(1, room.size)
    }
    val fullBurrow = initialBurrow.copy(
        rooms = fullRooms,
        roomCapacity = fullRooms.maxOf { it.size }
    )

    return fullBurrow.energyRequiredToOrganize()
}

fun main() {
    when (val solution = solve()) {
        null -> println("No solution")
        else -> println(solution)
    }
}
