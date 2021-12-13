/*
--- Part Two ---

Through a little deduction, you should now be able to determine the remaining digits. Consider again
the first example above:

```
acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab |
cdfeb fcadb cdfeb cdbaf
```

After some careful analysis, the mapping between signal wires and segments only make sense in the
following configuration:

```
 dddd
e    a
e    a
 ffff
g    b
g    b
 cccc
```

So, the unique signal patterns would correspond to the following digits:

- `acedgfb`: 8
- `cdfbe`: 5
- `gcdfa`: 2
- `fbcad`: 3
- `dab`: 7
- `cefabd`: 9
- `cdfgeb`: 6
- `eafb`: 4
- `cagedb`: 0
- `ab`: 1

Then, the four digits of the output value can be decoded:

- `cdfeb`: 5
- `fcadb`: 3
- `cdfeb`: 5
- `cdbaf`: 3

Therefore, the output value for this entry is 5353.

Following this same process for each entry in the second, larger example above, the output value of
each entry can be determined:

- `fdgacbe cefdb cefbgd gcbe`: 8394
- `fcgedb cgb dgebacf gc`: 9781
- `cg cg fdcagb cbg`: 1197
- `efabcd cedba gadfec cb`: 9361
- `gecf egdcabf bgf bfgea`: 4873
- `gebdcfa ecba ca fadegcb`: 8418
- `cefg dcbef fcge gbcadfe`: 4548
- `ed bcgafe cdgba cbgef`: 1625
- `gbdfcae bgc cg cgb`: 8717
- `fgae cfgab fg bagce`: 4315

Adding all of the output values in this larger example produces 61229.

For each entry, determine all of the wire/segment connections and decode the four-digit output
values. What do you get if you add up all of the output values?
*/

package com.curtislb.adventofcode.year2021.day08.part2

import com.curtislb.adventofcode.year2021.day08.display.SevenSegmentDisplay
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2021, day 8, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt")): Int {
    var total = 0
    inputPath.toFile().forEachLine { line ->
        total += SevenSegmentDisplay(line).decodeOutput()
    }
    return total
}

fun main() {
    println(solve())
}
