fun main() {
    `05`().partOne()
    println("---------------")
    `05`().partTwo()
}

typealias Count = Int
data class Node(
    val x: Int,
    val y: Int,
)
data class NodeCount(
    val node: Node,
    var count: Count,
)
data class Line(
    val start: Node,
    val end: Node,
)
val inputLineParser = fun(input: String): Line {
    val rawNodes: List<String> = input.trim().split(" -> ")
    val node1: List<String> = rawNodes[0].split(",")
    val node2: List<String> = rawNodes[1].split(",")
    return Line(Node(node1[0].toInt(),node1[1].toInt()), Node(node2[0].toInt(),node2[1].toInt()))
}

class `05`: SolutionRunner() {
    fun partOne() {
        println("Running Part One")
        val inputList: List<Line> = buildInputList(this.javaClass.kotlin.simpleName!!, inputLineParser)
        val linesExcludingDiagonals: List<Line> = inputList
            .filter { line ->
                line.start.x == line.end.x
                || line.start.y == line.end.y
            }
        val coveredNodes: List<NodeCount> = calculateCoveredNodes(linesExcludingDiagonals)
        val maxCoveredCount = coveredNodes.count { it.count >= 2 }
        println("Result total number of most dangerous nodes is maxCoveredCount=$maxCoveredCount")
    }
    fun partTwo() {
        println("Running Part Two")
        val inputList: List<Line> = buildInputList(this.javaClass.kotlin.simpleName!!, inputLineParser)
        val coveredNodes: List<NodeCount> = calculateCoveredNodes(inputList)
        val maxCoveredCount = coveredNodes.count { it.count >= 2 }
        println("Result total number of most dangerous nodes is maxCoveredCount=$maxCoveredCount")
    }
    private fun calculateCoveredNodes(lines: List<Line>): List<NodeCount> {
        println("Calculating the covered nodes...")
        val coveredNodes: MutableMap<Node, Count> = mutableMapOf()
        lines.forEach { line ->
            var xCounter = line.start.x
            val xIncrement: Int = if (line.start.x < line.end.x) 1 else if (line.start.x > line.end.x) -1 else 0
            var yCounter = line.start.y
            val yIncrement = if (line.start.y < line.end.y) 1 else if (line.start.y > line.end.y) -1 else 0
            while(isStillIncrementing(line, xCounter, yCounter)) {
                val currentNode = Node(xCounter, yCounter)
                val nodeCount: Count? = coveredNodes[currentNode]
                if (nodeCount != null) coveredNodes[currentNode] = coveredNodes[currentNode]!! + 1
                else coveredNodes[currentNode] = 1
                xCounter += xIncrement
                yCounter += yIncrement
            }
        }
        return coveredNodes.toList().map { NodeCount(it.first, it.second) }
    }
    private fun isStillIncrementing(line: Line, xCounter: Int, yCounter: Int): Boolean {
        if (line.start.x < line.end.x && xCounter > line.end.x) return false
        if (line.start.x > line.end.x && xCounter < line.end.x) return false
        if (line.start.y < line.end.y && yCounter > line.end.y) return false
        if (line.start.y > line.end.y && yCounter < line.end.y) return false
        return true
    }
}