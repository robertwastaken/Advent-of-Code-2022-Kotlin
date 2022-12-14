package solutions

import java.io.File

fun day1() {
    val input = File("src/inputs/day1/input.txt").readText()
    val elfList = input
        .split("\n\n")
        .map { it.split("\n") }

    //Part 1

    val calories = elfList
        .map {
            it.sumOf { calories ->
                if (calories != "")
                    calories.toInt()
                else
                    0
            }
        }
        .sortedDescending()

    println("\nMost calories carried: ${calories[0]}\n")

    //Part 2

    println("Total amount of calories carried by the top 3 elves: ${calories[0] + calories[1] + calories [2]}")
}