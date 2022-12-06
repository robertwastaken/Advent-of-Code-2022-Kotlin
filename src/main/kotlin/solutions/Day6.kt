package solutions

import java.io.File

fun day6() {
    val input = File("src/inputs/day6/input.txt").readText().trim()


    //Part 1

    for (i in 0 until input.length - 4) {
        if (input[i] == input[i + 1] || input[i] == input[i + 2] || input[i] == input[i + 3])
            continue
        else if (input[i + 1] == input[i + 2] || input[i + 1] == input[i + 3])
            continue
        else if (input[i + 2] == input[i + 3])
            continue
        else {
            println("\nCharacters to be processed before the first start-of-packet marker: ${i + 4}\n")
            break
        }

    }

    //Part 2

    //basically the same but 14 different, they just don't want me to do if else if else oof
    //I mean I still could...

    for (i in 0 until input.length - 14) {
        var same = false
        for (j in 1 until 14) {
            if (input[i+j-1] in input.substring(i + j, i + 14)) {

                same = true
                break
            }
        }
        if (same) {
            continue
        }
        else {
            println("Characters to be processed before the first start-of-message marker: ${i + 14}")
            break
        }
    }

}