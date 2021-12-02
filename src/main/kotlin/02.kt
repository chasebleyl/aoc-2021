import java.io.File

fun main() {
    `02`().partOne()
    println("---------------")
    `02`().partTwo()
}

data class Movement(
    val direction: String,
    val units: Long
)

class `02` {
    fun partOne() {
        println("Running Part One")
        val inputList: List<String> = buildInputList()
        var xPos: Long = 0
        var yPos: Long = 0
        inputList.forEach {
            val elements: List<String> = it.split(" ")
            val movement: Movement = Movement(
                elements[0],
                elements[1].toLong()
            )
            if (movement.direction == "forward") xPos += movement.units
            else if (movement.direction == "up") yPos -= movement.units
            else yPos += movement.units
        }
        println("Result xPos=$xPos, yPos=$yPos, multipliedResult=${xPos * yPos}")
    }
    fun partTwo() {
        println("Running Part Two")
        val inputList: List<String> = buildInputList()
        var xPos: Long = 0
        var yPos: Long = 0
        var aim: Long = 0
        inputList.forEach {
            val elements: List<String> = it.split(" ")
            val movement: Movement = Movement(
                elements[0],
                elements[1].toLong()
            )
            if (movement.direction == "forward") {
                xPos += movement.units
                yPos += movement.units * aim
            }
            else if (movement.direction == "up") aim -= movement.units
            else aim += movement.units
        }
        println("Result xPos=$xPos, yPos=$yPos, multipliedResult=${xPos * yPos}")
    }
    private fun buildInputList(): List<String> {
        val inputList: MutableList<String> = mutableListOf()
        readInput().forEachLine {
            inputList.add(it)
        }
        return inputList
    }
    private fun readInput(): File = File("src/main/resources/input_${this.javaClass.kotlin.simpleName}.txt")
}