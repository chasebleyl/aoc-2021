
fun main() {
    `07`().partOne()
    println("---------------")
    `07`().partTwo()
}

class `07`: SolutionRunner() {
    fun partOne() {
        println("Running Part One")
        val inputList: List<String> = buildInputList(this.javaClass.kotlin.simpleName!!)
        val inputPos: List<Int> = inputList[0].split(",").map { it.toInt() }.sorted()
        val medianElement: Int = getMedianElement(inputPos)
        println("Working with inputPos=$inputPos, median=$medianElement")
        println("Result most efficient alignment yields ${findMostEfficientAlignment(inputPos, medianElement)}")
    }
    private fun getMedianElement(inputPosList: List<Int>): Int = inputPosList[inputPosList.size/2]
    private fun findMostEfficientAlignment(inputPosList: List<Int>, median: Int): Int? {
        val fuelUsages: MutableSet<Int> = mutableSetOf()
        for (i in 0..inputPosList.indexOf(median)) {
            var currentFuelUsage = 0
            inputPosList.forEach { pos ->
                currentFuelUsage += kotlin.math.abs(pos - i)
            }
            fuelUsages.add(currentFuelUsage)
        }
        return fuelUsages.minOrNull()
    }
    fun partTwo() {
        println("Running Part Two")
        val inputList: List<String> = buildInputList(this.javaClass.kotlin.simpleName!!)
        val inputPos: List<Int> = inputList[0].split(",").map { it.toInt() }.sorted()
        println("Result most efficient alignment yields ${findMostEfficientExponentialAlignment(inputPos)}")
    }
    private fun findMostEfficientExponentialAlignment(inputPosList: List<Int>): Long? {
        val fuelUsages: MutableSet<Long> = mutableSetOf()
        val min: Int = if (inputPosList.minOrNull() == null) 0 else inputPosList.minOrNull()!!
        val max: Int = if (inputPosList.maxOrNull() == null) 0 else inputPosList.maxOrNull()!!
        for (i in min..max) {
            var currentFuelUsage: Long = 0
            inputPosList.forEach { pos ->
                currentFuelUsage += calculateNthTriangleNumber(kotlin.math.abs(pos - i))
            }
            fuelUsages.add(currentFuelUsage)
        }
        return fuelUsages.minOrNull()
    }
    // https://en.wikipedia.org/wiki/Triangular_number
    private fun calculateNthTriangleNumber(n: Int): Long = ((n*n + n)/2).toLong()
}