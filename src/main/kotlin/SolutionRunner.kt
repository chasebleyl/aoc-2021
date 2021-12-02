import java.io.File

open class SolutionRunner {
    fun buildInputList(solutionNumber: String): List<String> {
        return buildInputList(solutionNumber, { a: String -> a })
    }
    fun <T> buildInputList(solutionNumber: String, mutator: (a: String) -> T): List<T> {
        val inputList: MutableList<T> = mutableListOf()
        readInput(solutionNumber).forEachLine {
            inputList.add(mutator(it))
        }
        return inputList
    }
    private fun readInput(solutionNumber: String): File = File("src/main/resources/input_$solutionNumber.txt")
}
