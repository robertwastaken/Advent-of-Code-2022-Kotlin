package solutions

import java.io.File

fun day2() {
    val input = File("src/inputs/day2/input.txt").readText().trim()
    //A, X - Rock
    //B, Y - Paper
    //C, Z - Scissors
    //X - Lose
    //Y - Draw
    //Z - Win

    val rounds = input.split("\n").map { Pair(it[0], it[2]) }

    //Part 1

    val score = rounds.sumOf { getScore(it) }
    println("\nScore with the first strategy: $score\n")

    //Part 2

    val score2 = rounds.sumOf { getScore2(it) }
    println("Score with the second strategy: $score2")

}

fun getScore(round: Pair<Char, Char>): Int {
    var score = when (round.second) {
        'X' -> 1
        'Y' -> 2
        else -> 3
    }

    if (round.second - round.first == 23)
        score += 3
    else if (round.second - round.first == 24 || round.second - round.first == 21)
        score += 6

    return score
}

fun getScore2(round: Pair<Char, Char>): Int = when (round.second) {
    'X' -> 0 + when (round.first) {
        'A' -> 3
        'B' -> 1
        else -> 2
    }
    'Y' -> 3 + when (round.first) {
        'A' -> 1
        'B' -> 2
        else -> 3
    }
    else -> 6 + when (round.first) {
        'A' -> 2
        'B' -> 3
        else -> 1
    }
}