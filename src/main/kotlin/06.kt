fun main() {
    `06`().partOne()
    println("---------------")
    `06`().partTwo()
}

typealias Age = Int
data class Fish(
    val age: Age,
    val firstCycle: Boolean,
    var children: List<Fish> = listOf(),
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
        val fish: List<Fish> = parseFish(inputList[0])
        // TODO: Can't figure out right data structure and thus deal with overflows
//        val daysToRun: Int = 256
        val daysToRun: Int = 80
        val school: List<Fish> = ageAndReproduceFish(fish, daysToRun)
        // TODO: Solve
        var fishCounter: Long = recursiveCount(school)
        println("Result after $daysToRun days is $fishCounter fish")
    }
    private fun ageAndReproduceFish(school: List<Fish>, day: Int): List<Fish> {
        if (day == 0) return school
        val newSchool: MutableList<Fish> = mutableListOf()
        school.forEach { fish ->
            val newChildren: List<Fish> = if (fish.children.isNotEmpty()) ageAndReproduceFish(fish.children, 1) else mutableListOf()
            if (fish.age == 0) {
                val newFish = Fish(6, true, listOf(Fish(8, true)) +  newChildren)
                newSchool.add(newFish)
            } else {
                newSchool.add(Fish(fish.age - 1, fish.firstCycle, newChildren))
            }
        }
        return ageAndReproduceFish(newSchool, day - 1)
    }
    private fun recursiveCount(school: List<Fish>): Long {
        var newCounter: Long = 0
        school.forEach { fish ->
            newCounter += recursiveCountFish(fish)
        }
        return newCounter
    }
    private fun recursiveCountFish(fish: Fish): Long {
        var newCounter: Long = 1
        fish.children.forEach { fish ->
            newCounter += recursiveCountFish(fish)
        }
        return newCounter
    }
    private fun parseFish(inputString: String): List<Fish> = inputString.trim().split(",").map { Fish(it.toInt(), false) }
}