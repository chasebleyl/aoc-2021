fun main() {
    `08`().partOne()
    println("---------------")
    `08`().partTwo()
}

typealias Top = Char
typealias TopLeft = Char
typealias TopRight = Char
typealias Middle = Char
typealias BottomLeft = Char
typealias BottomRight = Char
typealias Bottom = Char

enum class SegmentLocation{
    TOP,TOP_LEFT,TOP_RIGHT,MIDDLE,BOTTOM_LEFT,BOTTOM_RIGHT,BOTTOM
}

val segmentsToDigitMap: Map<Int, List<Int>> = mapOf(
    2 to listOf(1),
    3 to listOf(7),
    4 to listOf(4),
    5 to listOf(2, 3, 5),
    6 to listOf(0, 6, 9),
    7 to listOf(8)
)
val validLocationsMap: Map<Int, List<SegmentLocation>> = mapOf(
    0 to listOf(SegmentLocation.TOP, SegmentLocation.TOP_LEFT, SegmentLocation.TOP_RIGHT, SegmentLocation.BOTTOM_LEFT, SegmentLocation.BOTTOM_RIGHT, SegmentLocation.BOTTOM),
    1 to listOf(SegmentLocation.TOP_RIGHT, SegmentLocation.BOTTOM_RIGHT),
    2 to listOf(SegmentLocation.TOP, SegmentLocation.TOP_RIGHT, SegmentLocation.MIDDLE, SegmentLocation.BOTTOM_LEFT, SegmentLocation.BOTTOM),
    3 to listOf(SegmentLocation.TOP, SegmentLocation.TOP_RIGHT, SegmentLocation.MIDDLE, SegmentLocation.BOTTOM_RIGHT, SegmentLocation.BOTTOM),
    4 to listOf(SegmentLocation.TOP_LEFT, SegmentLocation.TOP_RIGHT, SegmentLocation.MIDDLE, SegmentLocation.BOTTOM_RIGHT),
    5 to listOf(SegmentLocation.TOP, SegmentLocation.TOP_LEFT, SegmentLocation.MIDDLE, SegmentLocation.BOTTOM_RIGHT, SegmentLocation.BOTTOM),
    6 to listOf(SegmentLocation.TOP, SegmentLocation.TOP_LEFT, SegmentLocation.MIDDLE, SegmentLocation.BOTTOM_LEFT, SegmentLocation.BOTTOM_RIGHT, SegmentLocation.BOTTOM),
    7 to listOf(SegmentLocation.TOP, SegmentLocation.TOP_RIGHT, SegmentLocation.BOTTOM_RIGHT),
    8 to listOf(SegmentLocation.TOP, SegmentLocation.TOP_LEFT, SegmentLocation.TOP_RIGHT, SegmentLocation.MIDDLE, SegmentLocation.BOTTOM_LEFT, SegmentLocation.BOTTOM_RIGHT, SegmentLocation.BOTTOM),
    9 to listOf(SegmentLocation.TOP, SegmentLocation.TOP_LEFT, SegmentLocation.TOP_RIGHT, SegmentLocation.MIDDLE, SegmentLocation.BOTTOM_RIGHT, SegmentLocation.BOTTOM),
)
data class SegmentDisplay(
    var top: Char? = null,
    var topLeft: Char? = null,
    var topRight: Char? = null,
    var middle: Char? = null,
    var bottomLeft: Char? = null,
    var bottomRight: Char? = null,
    var bottom: Char? = null,
)

class `08`: SolutionRunner() {
    fun partOne() {
        println("Running Part One")
        val inputOutputValues: List<String> = buildInputList(this.javaClass.kotlin.simpleName!!, { a: String -> a.split(" | ")[1] })
        val uniqueDigitOccurrences = calculateUniqueDigitOccurences(inputOutputValues)
        // TODO: Solve
        println("Result for output values uniqueDigitOccurrences=$uniqueDigitOccurrences")
    }
    private fun calculateUniqueDigitOccurences(inputValues: List<String>): Int {
        var uniqueCounter: Int = 0
        inputValues.forEach { entry ->
            val patterns: List<String> = entry.split(" ")
            patterns.forEach { pattern ->
                if (listOf(2, 3, 4, 7).contains(pattern.length)) uniqueCounter += 1
            }
        }
        return uniqueCounter
    }
    fun partTwo() {
        println("Running Part Two")
        val inputList: List<Triple<String, String, String>> = buildInputList(this.javaClass.kotlin.simpleName!!, { a: String -> Triple(a.replace(" | ", " "), a.split(" | ")[0], a.split(" | ")[1]) })
        calculateSegmentPattern(inputList[0].first)
        // TODO: Solve
        println("Result inputList=$inputList")
    }
    private fun calculateSegmentPattern(unknownPatterns: String): SegmentDisplay {
        val segmentDisplayMap: Map<SegmentLocation, MutableSet<Char>> = mapOf(
            SegmentLocation.TOP to mutableSetOf(),
            SegmentLocation.TOP_LEFT to mutableSetOf(),
            SegmentLocation.TOP_RIGHT to mutableSetOf(),
            SegmentLocation.MIDDLE to mutableSetOf(),
            SegmentLocation.BOTTOM_LEFT to mutableSetOf(),
            SegmentLocation.BOTTOM_RIGHT to mutableSetOf(),
            SegmentLocation.BOTTOM to mutableSetOf(),
        )
        unknownPatterns.split(" ").forEach { pattern ->
            val possibleDigits: List<Int> = segmentsToDigitMap[pattern.length]!!
            possibleDigits.forEach { digit ->
                pattern.forEach { char ->
                    validLocationsMap[digit]!!.forEach { location ->
                        segmentDisplayMap[location]!!.add(char)
                    }
                }
            }
            println("Possible digits for $pattern are $possibleDigits")
        }
        println("Printing out current values for segmentDisplayMap=$segmentDisplayMap")
        return SegmentDisplay()
    }
}