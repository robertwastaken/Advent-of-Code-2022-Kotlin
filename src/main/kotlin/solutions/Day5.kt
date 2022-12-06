package solutions

import java.io.File

fun day5() {
    val input = File("src/inputs/day5/input.txt").readText().trimEnd()

    val lines = input.replace("\r", "").split("\n")

    var instructionStartIndex = 0
    val crates = mutableListOf<Pair<Int, Char>>()
    var stacks = 0

    //Get starting position
    for (i in lines.indices) {
        if (lines[i] == "") {
            instructionStartIndex = i + 1
            break
        }
        for (j in 1 until lines[i].length step 4) {
            if (lines[i][j] == ' ')
                continue
            if (lines[i][j-1] == '['){
                crates.add(Pair(j / 4, lines[i][j]))
            }
            else {
                stacks++
            }
        }
    }

    val scene = Array<MutableList<Char>>(stacks) { mutableListOf() }
    //why is it easier to do this than deepcopy
    val scene9001 = Array<MutableList<Char>>(stacks) { mutableListOf() }

    for (crate in crates.reversed()) {
        scene[crate.first].add(crate.second)
        scene9001[crate.first].add(crate.second)
    }

    //Get instructions
    val instructions = mutableListOf<Triple<Int, Int, Int>>()
    for (i in instructionStartIndex until lines.size) {
        val instruction = lines[i].split(" ")
        instructions.add(Triple(instruction[1].toInt(), instruction[3].toInt(), instruction[5].toInt()))
    }

    //Part 1

    val answer = moveCrates(scene, instructions)

    println("\nCrates ending on top of ecah stack: $answer\n")

    //Part 2

    val answer9001 = moveCrates9001(scene9001, instructions)

    println("Crates ending on top of ecah stack with the CrateMover 9001: $answer9001")
}

fun moveCrates(scene: Array<MutableList<Char>>, instructions: MutableList<Triple<Int, Int, Int>>): String {
    var answer = ""

    for (instruction in instructions) {
        val moving = scene[instruction.second - 1].reversed().subList(0, instruction.first)
        scene[instruction.third - 1].addAll(moving)
        scene[instruction.second - 1] = scene[instruction.second - 1].dropLast(instruction.first).toMutableList()
    }

    for (stack in scene)
        answer += stack.last()

    return answer
}

fun moveCrates9001(scene: Array<MutableList<Char>>, instructions: MutableList<Triple<Int, Int, Int>>): String {
    var answer = ""

    for (instruction in instructions) {
        val moving = scene[instruction.second - 1].reversed().subList(0, instruction.first)
        scene[instruction.third - 1].addAll(moving.reversed())
        scene[instruction.second - 1] = scene[instruction.second - 1].dropLast(instruction.first).toMutableList()
    }

    for (stack in scene)
        answer += stack.last()

    return answer
}
