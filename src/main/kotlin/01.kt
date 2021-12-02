fun main() {
    `01`().partOne()
    println("---------------")
    `01`().partTwo()
}

class `01`: SolutionRunner() {
    fun partOne() {
        println("Running Part One")
        val depths: List<Int> = buildInputList(this.javaClass.kotlin.simpleName!!, { a: String -> a.toInt() })
        var increaseCounter = 0
        for (i in 1 until depths.size) {
            if (depths[i] > depths[i-1]) {
                increaseCounter++
            }
        }
        println("Total increases in depth=$increaseCounter")
    }
    fun partTwo() {
        println("Running Part Two")
        val depths: List<Int> = buildInputList(this.javaClass.kotlin.simpleName!!, { a: String -> a.toInt() })
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
//    private fun buildInputList(): List<Int> {
//        val inputList: MutableList<Int> = mutableListOf()
//        readInput().forEachLine {
//            inputList.add(it.toInt())
//        }
//        return inputList
//    }
//    private fun readInput(): File = File("src/main/resources/input_${this.javaClass.kotlin.simpleName}.txt")
}