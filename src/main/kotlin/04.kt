fun main() {
    `04`().partOne()
    println("---------------")
    `04`().partTwo()
}
typealias DrawNumber = Int
typealias DrawNumberSum = Int
val drawNumbers: List<DrawNumber> = listOf(49,48,98,84,71,59,37,36,6,21,46,30,5,33,3,62,63,45,43,35,65,77,57,75,19,44,4,76,88,92,12,27,7,51,14,72,96,9,0,17,83,64,38,95,54,20,1,74,69,80,81,56,10,68,42,15,99,53,93,94,47,13,29,34,60,41,82,90,25,85,78,91,32,70,58,28,61,24,55,87,39,11,79,50,22,8,89,26,16,2,73,23,18,66,52,31,86,97,67,40)
data class Board(
    val id: Int,
    val rows: MutableList<List<Number>> = mutableListOf()
)
data class Number(
    val value: DrawNumber,
    var drawn: Boolean = false
)

class `04`: SolutionRunner() {
    fun partOne() {
        println("Running Part One")
        val inputList: List<String> = buildInputList(this.javaClass.kotlin.simpleName!!)
        val boards: List<Board> = parseBoards(inputList)
        println("Finished generating boards; boardsSize=${boards.size}, boards=$boards")
        val winningPair: Pair<Board, DrawNumber> = playBingo(boards)
        val score = sumUnmarkedNumbers(winningPair.first) * winningPair.second
        println("Resulting score=$score")
    }
    private fun parseBoards(
        inputList: List<String>,
        index: Int = 0,
        activeBoard: Board = Board(0),
        boards: MutableList<Board> = mutableListOf()
    ): List<Board> {
        if (index == inputList.size) {
            boards.add(activeBoard)
            return boards
        }
        if (inputList[index] == "") {
            boards.add(activeBoard)
            return parseBoards(inputList, index + 1, Board(activeBoard.id + 1), boards)
        }
        val stringList = inputList[index].trim().replace("  ", " ").split(" ")
        val row: List<Number> = stringList.map { Number(it.toInt(), false) }
        activeBoard.rows.add(row)
        return parseBoards(inputList, index + 1, activeBoard, boards)
    }
    private fun playBingo(boards: List<Board>): Pair<Board, DrawNumber> {
        drawNumbers.forEach { numberDrawn ->
            println("Drew number $numberDrawn")
            boards.forEach { board ->
                board.rows.forEach { row ->
                    row.forEach { number ->
                        if (number.value == numberDrawn) number.drawn = true
                    }
                }
            }
            val winningBoard: Board? = getWinningBoard(boards)
            if (winningBoard != null) {
                println("Found winning board! Winning number was $numberDrawn and winning board was $winningBoard")
                return Pair(winningBoard, numberDrawn)
            }
        }
        throw Exception("Should have found a winning board")
    }
    private fun getWinningBoard(boards: List<Board>): Board? {
        boards.forEach { board ->
            for (i in 0 until board.rows.size) {
                var missingDrawnNumbers = board.rows[i].size
                for (j in 0 until board.rows[i].size) {
                    if (board.rows[i][j].drawn) missingDrawnNumbers -= 1
                }
                if (missingDrawnNumbers == 0) return board
            }
            for (i in 0 until board.rows[0].size) {
                var missingDrawnNumbers = board.rows.size
                for (j in 0 until board.rows.size) {
                    if (board.rows[j][i].drawn) missingDrawnNumbers -= 1
                }
                if (missingDrawnNumbers == 0) return board
            }
        }
        return null
    }
    private fun sumUnmarkedNumbers(board: Board): DrawNumberSum {
        var drawNumberSum: DrawNumber = 0
        board.rows.forEach { row ->
            row.forEach { number ->
                if (!number.drawn) {
                    drawNumberSum += number.value
                }
            }
        }
        return drawNumberSum
    }
    fun partTwo() {
        println("Running Part Two")
        val inputList: List<String> = buildInputList(this.javaClass.kotlin.simpleName!!)
        val boards: List<Board> = parseBoards(inputList)
        println("Finished generating boards; boardsSize=${boards.size}, boards=$boards")
        val winningPair: Pair<Board, DrawNumber> = playBingoPartTwo(boards)
        // TODO: Solve
        val score = sumUnmarkedNumbers(winningPair.first) * winningPair.second
        println("Resulting score=$score")
    }
    private fun playBingoPartTwo(boards: List<Board>): Pair<Board, DrawNumber> {
        val boardsStillPlaying: MutableSet<Int> = boards.map { it.id }.toMutableSet()
        drawNumbers.forEach { numberDrawn ->
            println("Drew number $numberDrawn")
            val remainingBoards = boards.filter { boardsStillPlaying.contains(it.id) }
            remainingBoards.forEach { board ->
                board.rows.forEach { row ->
                    row.forEach { number ->
                        if (number.value == numberDrawn) number.drawn = true
                    }
                }
            }
            val winningBoards: Set<Board> = getWinningBoards(remainingBoards)
            if (winningBoards.isNotEmpty()) {
                winningBoards.forEach { winningBoard ->
                    println("Found winning board! Winning number was $numberDrawn and winning board was ${winningBoard.id}")
                    boardsStillPlaying.remove(winningBoard.id)
                    println("New state of boardsStillPlaying=$boardsStillPlaying")
                    println("Printing all boards=$remainingBoards")
                    if (boardsStillPlaying.size == 0) return Pair(winningBoard, numberDrawn)
                }
            }
        }
        throw Exception("Should have found a winning board")
    }
    private fun getWinningBoards(boards: List<Board>): Set<Board> {
        val winningBoards: MutableSet<Board> = mutableSetOf()
        boards.forEach { board ->
            for (i in 0 until board.rows.size) {
                var missingDrawnNumbers = board.rows[i].size
                for (j in 0 until board.rows[i].size) {
                    if (board.rows[i][j].drawn) missingDrawnNumbers -= 1
                }
                if (missingDrawnNumbers == 0) winningBoards.add(board)
            }
            for (i in 0 until board.rows[0].size) {
                var missingDrawnNumbers = board.rows.size
                for (j in 0 until board.rows.size) {
                    if (board.rows[j][i].drawn) missingDrawnNumbers -= 1
                }
                if (missingDrawnNumbers == 0) winningBoards.add(board)
            }
        }
        return winningBoards
    }
}