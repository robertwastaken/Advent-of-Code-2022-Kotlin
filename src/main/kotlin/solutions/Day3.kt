package solutions

import java.io.File

fun day3() {
    val input = File("src/inputs/day3/input.txt").readText().trim()

    val rucksacks = input.split("\n")
    val chunks = rucksacks.map { it.chunked(it.length / 2) }.map { chunk -> Pair(chunk[0], chunk[1]) }

    //Part 1

    var priority = 0
    for (pair in chunks) {
        for (character in pair.first) {
            val match = pair.second.firstOrNull { it == character }
            if (match != null) {
                priority += getPriority(character)
                break
            }
        }
    }

    println("\nTotal priority: $priority\n")

    //Part 2

    var priority2 = 0
    val chunks2 = rucksacks.chunked(3)
    for (group in chunks2) {
        for (character in group[0]) {
            group[1].firstOrNull { it == character } ?: continue
            group[2].firstOrNull { it == character } ?: continue
            priority2 += getPriority(character)
            break
        }
    }

    println("Total priority of groups: $priority2")

}

fun getPriority(character: Char): Int =
    if (character.isLowerCase())
        character.code - 96
    else
        character.code - 38

