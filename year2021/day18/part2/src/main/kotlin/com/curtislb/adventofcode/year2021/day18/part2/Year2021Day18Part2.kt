/*
--- Part Two ---

You notice a second question on the back of the homework assignment:

What is the largest magnitude you can get from adding only two of the snailfish numbers?

Note that snailfish addition is not commutative - that is, `x + y` and `y + x `can produce different
results.

Again considering the last example homework assignment above:

```
[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]
[[[5,[2,8]],4],[5,[[9,9],0]]]
[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]
[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]
[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]
[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]
[[[[5,4],[7,7]],8],[[8,3],8]]
[[9,3],[[9,9],[6,[4,9]]]]
[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]
[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]
```

The largest magnitude of the sum of any two snailfish numbers in this list is 3993. This is the
magnitude of `[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]` +
`[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]`, which reduces to
`[[[[7,8],[6,6]],[[6,0],[7,7]]],[[[7,8],[8,8]],[[7,9],[0,6]]]]`.

What is the largest magnitude of any sum of two different snailfish numbers from the homework
assignment?
*/

package com.curtislb.adventofcode.year2021.day18.part2

import com.curtislb.adventofcode.common.collection.uniquePairs
import com.curtislb.adventofcode.year2021.day18.snailfish.SnailfishNumber
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.system.measureTimeMillis

/**
 * Returns the solution to the puzzle for 2021, day 18, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt")): Int {
    var maxMagnitude = 0
    val numbers = inputPath.toFile().readLines().map { SnailfishNumber.fromString(it) }
    numbers.uniquePairs().forEach { (numberA, numberB) ->
        val abMagnitude = (numberA + numberB).magnitude()
        val baMagnitude = (numberB + numberA).magnitude()
        maxMagnitude = maxOf(maxMagnitude, abMagnitude, baMagnitude)
    }
    return maxMagnitude
}

fun main() {
    println(solve())
}
