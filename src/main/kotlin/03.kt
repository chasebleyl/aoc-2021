fun main() {
    `03`().partOne()
    println("---------------")
    `03`().partTwo()
}

data class Counters(
    var zero: Int = 0,
    var one: Int = 0
)

class `03`: SolutionRunner() {
    fun partOne() {
        println("Running Part One")
        val inputList: List<String> = buildInputList(this.javaClass.kotlin.simpleName!!)
        val bitMapCounters: MutableMap<Int, Counters> = mutableMapOf()
        inputList.forEach {
            for (i in 0..it.length-1) {
                val bit: Char = it[i]
                if (bitMapCounters[i] == null) bitMapCounters[i] = Counters()
                if (bit == '0') {
                    bitMapCounters[i]!!.zero += 1
                }
                else if (bit == '1') {
                    bitMapCounters[i]!!.one += 1
                }
            }
        }
        val gammaBinary: String = calculateGammaBinary(bitMapCounters)
        val gammaDecimal: Int = binaryStringToDecimalInt(gammaBinary)
        val epsilonBinary: String = convertGammaBinaryToEpsilonBinary(gammaBinary)
        val epsilonDecimal: Int = binaryStringToDecimalInt(epsilonBinary)
        println("Result: gammaBinary=$gammaBinary, gammaDecimal=$gammaDecimal, epsilonBinary=$epsilonBinary, epsilonDecimal=$epsilonDecimal, multipliedResult=${gammaDecimal * epsilonDecimal}")
    }
    private fun calculateGammaBinary(bitMapCounters: Map<Int, Counters>): String {
        var gammaBinaryString: String = ""
        for ((key, counters) in bitMapCounters) {
            gammaBinaryString += getCommonBit(bitMapCounters, key)
        }
        return gammaBinaryString
    }
    private fun getCommonBit(bitMapCounters: Map<Int, Counters>, key: Int): String =
        if (bitMapCounters[key]?.zero!! > bitMapCounters[key]?.one!!) "0" else "1"
    private fun convertGammaBinaryToEpsilonBinary(gammaBinary: String): String {
        val epsilonBinaryChars: MutableList<Char> = mutableListOf()
        gammaBinary.forEach {
            if (it == '0') epsilonBinaryChars.add('1')
            else epsilonBinaryChars.add('0')
        }
        return epsilonBinaryChars.joinToString("")
    }
    fun partTwo() {
        println("Running Part Two")
        val inputList: List<String> = buildInputList(this.javaClass.kotlin.simpleName!!)
        val oxygenBinary: String = calculateLifeSupportRating(inputList, 0, {zero: Int, one: Int -> if (zero > one) '0' else '1'})
        val oxygenDecimal: Int = binaryStringToDecimalInt(oxygenBinary)
        val co2Binary: String = calculateLifeSupportRating(inputList, 0, {zero: Int, one: Int -> if (zero <= one) '0' else '1'})
        val co2Decimal: Int = binaryStringToDecimalInt(co2Binary)
        println("Result; oxygenBinary=$oxygenBinary, oxygenDecimal=$oxygenDecimal, co2Binary=$co2Binary, co2Decimal=$co2Decimal, multipliedResult=${oxygenDecimal * co2Decimal}")
    }
    private fun calculateLifeSupportRating(inputList: List<String>, index: Int, filterCriteria: (zero: Int, one: Int) -> Char): String {
        if (index == inputList[0].length) return ""
        if (inputList.size == 1) return inputList[0][index] + calculateLifeSupportRating(inputList, index + 1, filterCriteria)
        val currentCounter = Counters()
        inputList.forEach {
            if (it[index] == '0') currentCounter.zero += 1
            else currentCounter.one += 1
        }
        val commonBit: Char = filterCriteria(currentCounter.zero, currentCounter.one)
        return commonBit + calculateLifeSupportRating(inputList.filter {
            it[index] == commonBit
        }, index + 1, filterCriteria)
    }
    private fun binaryStringToDecimalInt(binaryString: String): Int =
        binaryString.toInt(2)
}