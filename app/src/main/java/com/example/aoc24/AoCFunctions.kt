package com.example.aoc24

class AoCFunctions {
    val multiplicationRegexString = """mul\(\d{1,3}+,\d{1,3}+\)"""

    fun compareTwoNumbers(first: Int, second: Int): String {
        if (first == second) return "unsafe"
        if (first > second) {
            if (first - second > 3) return "unsafe"
            return "dec"
        }
        if (second - first > 3)
            return "unsafe"
        return "inc"
    }

    fun comparePreviousAndCurrentIncline(previous: String, current: String): String {
        if (previous == current)  return "safe"
        return "unsafe"
    }

    fun gradeReportFirst(list: List<Int>): String {
        var previousIncline = compareTwoNumbers(list[0], list[1])
        if (previousIncline == "unsafe") return "unsafe"
        for (i in 1 until list.size -1) {
            val currentIncline = compareTwoNumbers(list[i], list[i+1])
            if (currentIncline == "unsafe") return "unsafe"
            if (comparePreviousAndCurrentIncline(previousIncline, currentIncline) == "unsafe") return "unsafe"
            previousIncline = currentIncline
        }
        return "safe"
    }



    fun getSafeReports(listOfLists: List<List<Int>>, gradingFunction: (List<Int>) -> String): Int {
        var safeLineCounter = 0
        for (list in listOfLists){
            if (gradingFunction(list) == "safe") safeLineCounter += 1
        }
        return safeLineCounter

    }

    fun gradeReportSecond(list: List<Int>): String {
        for (i in 0 until list.size){
            val mutableList = list.toMutableList()
            mutableList.removeAt(i)
            if (gradeReportFirst(mutableList.toList()) == "safe") return "safe"
        }

        return "unsafe"
    }

    private fun getListFromRegex(
        multiplicationRegex: Regex,
        s: String
    ): List<String> {
        val resultSequence = multiplicationRegex.findAll(s)
        val result: MutableList<String> = mutableListOf()
        for (matchResult in resultSequence) {
            result.add(matchResult.value)
        }
        return result.toList()
    }

    fun getMultiplications(s: String): List<String>  {
        val multiplicationRegex = Regex(multiplicationRegexString)
        return getListFromRegex(multiplicationRegex, s)
    }

    fun getEnablingsAndMultiplications(s: String): List<String>  {
        val multiplicationAndEnablingString = """$multiplicationRegexString|do\(\)|don't\(\)"""
        val multiplicationRegex = Regex(multiplicationAndEnablingString)
        return getListFromRegex(multiplicationRegex, s)
    }

    fun getNumberPairs(mulString: String, getMultiplicationFunction: (String) -> List<String> ): List<Pair<Int, Int>>  {
        //val result = getMultiplications(mulString)
        val result = getMultiplicationFunction(mulString)
        val resultList : MutableList<Pair<Int, Int>> = mutableListOf()
        for (multiplication in result){
            resultList.add(getFactorsFromString(multiplication))
        }
        return resultList.toList()
    }

    private fun getFactorsFromString(multiplication: String): Pair<Int, Int> {
        val regex = Regex("""\d{1,3}""")
        val resultSequence = regex.findAll(multiplication)
        val a = resultSequence.elementAt(0).value.toInt()
        val b = resultSequence.elementAt(1).value.toInt()
        val factors: Pair<Int, Int> = Pair(a, b)
        return factors
    }

    fun getMultiplicationSum(mulString: String, getMultiplicationFunction: (String) -> List<String>): Int {
        val result = getNumberPairs(mulString, getMultiplicationFunction)
        var sum = 0
        for (pair in result){
            sum += pair.first * pair.second
        }
        return sum
    }

    fun getActiveMultiplications(s: String): List<String>  {
        val result: MutableList<String> = mutableListOf()
        val commandList = getEnablingsAndMultiplications(s)
        var enabled = true
        for (element in commandList) {
            when (element){
                 "do()" -> enabled = true
                "don't()" -> enabled = false
                else -> if (enabled) result.add(element)
            }

        }
        return result.toList()
    }


}