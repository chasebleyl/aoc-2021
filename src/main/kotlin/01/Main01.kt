package `01`

import java.io.File

fun main() {
    Main01().partOne()
    Main01().partTwo()
}

class Main01 {
    private val INPUT_FILE = "input_01.txt"
    fun partOne() {
        println("Running Part One")
        val depths = buildDepthsList()
        var increaseCounter = 0
        for (i in 1 until depths.size) {
            if (depths[i] > depths[i-1]) {
                increaseCounter++
            }
        }
        println("Total increases in depth=$increaseCounter")
        println("---------------")
    }
    fun partTwo() {
        println("Running Part Two")
        val depths = buildDepthsList()
        var increaseCounter = 0
        for (i in 3 until depths.size) {
            val window1Measurement = depths[i-3] + depths[i-2] + depths[i-1]
            val window2Measurement = depths[i-2] + depths[i-1] + depths[i]
            if (window2Measurement > window1Measurement) {
                increaseCounter++
            }
        }
        println("Total increases in depth=$increaseCounter")
    }
    private fun buildDepthsList(): List<Int> {
        val depths: MutableList<Int> = mutableListOf()
        readInput().forEachLine {
            depths.add(it.toInt())
        }
        return depths
    }
    private fun readInput(): File = File("src/main/resources/$INPUT_FILE")
}