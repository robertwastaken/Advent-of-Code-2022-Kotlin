package solutions

import java.io.File
import java.lang.Exception

fun day4() {
    val input = File("src/inputs/day4/input.txt").readText().trim()

    val ranges = input.split("\n")
        .map { it.split(",") }
        .map {
            it.map { range ->
                range.split("-")
            }
        }

    //Part 1

    var nr = 0
    for (row in ranges) {
        try {
            //yay windows and \r
            if (row[0][0].toInt() <= row[1][0].toInt() && row[0][1].toInt() >= row[1][1].replace("\r", "").toInt())
                nr++
            else if (row[0][0].toInt() >= row[1][0].toInt() && row[0][1].toInt() <= row[1][1].replace("\r", "").toInt())
                nr++
        } catch (e: Exception) {
            continue
        }
    }

    println("\nNumber of assignment pairs where one range fully contains the other: $nr\n")

    //Part 2


    var overlaps = 0
    for (row in ranges) {
        try {
            for (number in row[0][0].toInt()..row[0][1].toInt())
                if (number in row[1][0].toInt()..row[1][1].replace("\r", "").toInt()) {
                    overlaps++
                    break
                }
        } catch (e: Exception) {
            continue
        }
    }

    println("Number of pairs that overlap: $overlaps")

}