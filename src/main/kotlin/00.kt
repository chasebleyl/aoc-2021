import java.io.File

fun main() {
    `00`().partOne()
    println("---------------")
    `00`().partTwo()
}

class `00` {
    private val INPUT_FILE = "input_00.txt"
    fun partOne() {
        println("Running Part One")
        // TODO: Solve
        println("Result")
    }
    fun partTwo() {
        println("Running Part Two")
        // TODO: Solve
        println("Result")
    }
    private fun buildInputList(): List<String> {
        val inputList: MutableList<String> = mutableListOf()
        readInput().forEachLine {
            inputList.add(it)
        }
        return inputList
    }
    private fun readInput(): File = File("src/main/resources/$INPUT_FILE")
}