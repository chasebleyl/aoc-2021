fun main() {
    `06`().partOne()
    println("---------------")
    `06`().partTwo()
}

typealias Age = Int
data class Fish(
    val age: Age,
    val firstCycle: Boolean,
)

class `06`: SolutionRunner() {
    fun partOne() {
        println("Running Part One")
        val inputList: List<String> = buildInputList(this.javaClass.kotlin.simpleName!!)
        val fish: List<Fish> = parseFish(inputList[0])
        val daysToRun: Int = 80
        val reproducedFish: List<Fish> = reproduceFish(fish, daysToRun)
        // TODO: Solve
        println("Result after $daysToRun days is ${reproducedFish.size} fish")
    }
    private fun reproduceFish(fish: List<Fish>, day: Int): List<Fish> {
        if (day == 0) return fish
        val newFish: MutableList<Fish> = mutableListOf()
        fish.forEach { fishie ->
            if (fishie.age == 0) {
                newFish.add(Fish(8, true))
                newFish.add(Fish(6, true))
            } else {
                newFish.add(Fish(fishie.age - 1, fishie.firstCycle))
            }
        }
        return reproduceFish(newFish, day - 1)
    }
    fun partTwo() {
        println("Running Part Two")
        val inputList: List<String> = buildInputList(this.javaClass.kotlin.simpleName!!)
        val ages: List<Int> = parseInts(inputList[0])
        // TODO: With how reproduceFish2 is structured, need to reproduce one less day to get correct answer
        val daysToRun: Int = 256 - 1
        val totalFish: Long = reproduceFish2(ages, daysToRun)
        // TODO: Solve
        println("Result after $daysToRun days is $totalFish fish")
    }
    private fun reproduceFish2(ages: List<Int>, day: Int): Long {
        var initialFishCounter: MutableMap<Age, Long> = mutableMapOf()
        ages.forEach { age ->
            if (initialFishCounter[age] != null) initialFishCounter[age] = initialFishCounter[age]!! + 1
            else initialFishCounter[age] = 1
        }
        for (i in 0..day) {
            val newFishCounter: MutableMap<Age, Long> = mutableMapOf()
            initialFishCounter.forEach { age, count ->
                if (age == 0) {
                    if (newFishCounter[6] != null) newFishCounter[6] = newFishCounter[6]!! + count
                    else newFishCounter[6] = count
                    if (newFishCounter[8] != null) newFishCounter[8] = newFishCounter[8]!! + count
                    else newFishCounter[8] = count
                } else {
                    if (newFishCounter[age-1] != null) newFishCounter[age-1] = newFishCounter[age-1]!! + count
                    else newFishCounter[age-1] = count
                }
            }
            initialFishCounter = newFishCounter
        }
        println("Printing resulting fish counters $initialFishCounter")
        var counter: Long = 0
        initialFishCounter.forEach{ age, count ->
            counter += count
        }
        return counter
    }
    private fun parseFish(inputString: String): List<Fish> = inputString.trim().split(",").map { Fish(it.toInt(), false) }
    private fun parseInts(inputString: String): List<Int> = inputString.trim().split(",").map { it.toInt() }
}