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

    fun getNumberPairs(mulString: String, getMultiplicationFunction: (String) -> List<String> ): List<Pair<Int, Int>>  {
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

    // day 4

    fun getHorizontalStrings(matrix: Array<Array<Char>>): List<String> {
        val result: MutableList<String> = mutableListOf()
        for (array in matrix){
            var string = ""
            for (char in array){
                string += char
            }
            result.add(string)
        }
        return result
    }

    fun getVerticalStrings(matrix: Array<Array<Char>>): List<String> {
        val result: MutableList<String> = mutableListOf()

        for (j in 0 until matrix.size) {
            var string = ""
            for (i in 0 until matrix.size) {
                string += matrix[i][j]
            }
            result.add(string)
        }
        return result
    }

    fun getLeftDiagonalStrings(matrix: Array<Array<Char>>): List<String> {
        val result: MutableList<String> = mutableListOf()
        val size = matrix[0].size
        // lower triangular
        for (startingRow in size downTo  0) {
            var i = 0
            var string = ""
            for (j in startingRow until size){
                string += matrix[j][i]
                i += 1
            }
            if (string.isNotEmpty()) result.add(string)
        }
        // upper triangular
        for (startingColumn in 1 until  size) {
            var j = 0
            var string = ""
            for (i in startingColumn until size){
                string += matrix[j][i]
                j += 1
            }
            if (string.isNotEmpty()) result.add(string)
        }
        return result
    }

    fun getRightDiagonalStrings(matrix: Array<Array<Char>>): List<String> {
        val result: MutableList<String> = mutableListOf()
        val size = matrix[0].size
        // upper triangular
        for (startingRow in 0 until  size) {
            var i = 0
            var string = ""
            for (j in startingRow downTo  0){
                string += matrix[j][i]
                i += 1
            }
            if (string.isNotEmpty()) result.add(string)
        }
        // lower triangular
        for (startingcolumn in 1 until size) {
            var j = size - 1
            var string = ""
            for (i in startingcolumn until size){
                string += matrix[j][i]
                j -= 1
            }
            if (string.isNotEmpty()) result.add(string)
        }

        return result
    }



    private fun countStrings(matrix: Array<Array<Char>>, searchString: String, getStringsFunction: (Array<Array<Char>>) -> List<String>  ): Int {
        val lines = getStringsFunction(matrix)
        var counter = 0
        for (line in lines){
            val regex = Regex(searchString)
            val matches = regex.findAll(line)
            counter += matches.count()
        }
        return counter
    }

    fun countHorizontalStrings(matrix: Array<Array<Char>>, searchString: String): Int {
        return countStrings(matrix, searchString, this::getHorizontalStrings)
    }


    fun countHorizontalReversedStrings(matrix: Array<Array<Char>>, searchString: String): Int {
        val reversedSearchString = searchString.reversed()
        return countHorizontalStrings(matrix, reversedSearchString)
    }

    fun countVerticalStrings(matrix: Array<Array<Char>>, searchString: String): Int {
        return countStrings(matrix, searchString, this::getVerticalStrings)
    }

    fun countVerticalReversedStrings(matrix: Array<Array<Char>>, searchString: String): Int {
        val reversedSearchString = searchString.reversed()
        return countVerticalStrings(matrix, reversedSearchString)
    }

    fun countLeftDiagonalStrings(matrix: Array<Array<Char>>, searchString: String): Int {
        return countStrings(matrix, searchString, this::getLeftDiagonalStrings)
    }

    fun countLeftDiagonalReversedStrings(matrix: Array<Array<Char>>, searchString: String): Int {
        val reversedSearchString = searchString.reversed()
        return countLeftDiagonalStrings(matrix, reversedSearchString)
    }

    fun countRightDiagonalStrings(matrix: Array<Array<Char>>, searchString: String): Int {
        return countStrings(matrix, searchString, this::getRightDiagonalStrings)
    }

    fun countRightDiagonalReversedStrings(matrix: Array<Array<Char>>, searchString: String): Int {
        val reversedSearchString = searchString.reversed()
        return countRightDiagonalStrings(matrix, reversedSearchString)
    }

    fun countAllStrings(matrix: Array<Array<Char>>, searchString: String): Int {
        var counter = 0
        counter += countHorizontalStrings(matrix, searchString)
        counter += countHorizontalReversedStrings(matrix, searchString)
        counter += countVerticalStrings(matrix, searchString)
        counter += countVerticalReversedStrings(matrix, searchString)
        counter += countLeftDiagonalStrings(matrix, searchString)
        counter += countLeftDiagonalReversedStrings(matrix, searchString)
        counter += countRightDiagonalStrings(matrix, searchString)
        counter += countRightDiagonalReversedStrings(matrix, searchString)
        return counter
    }

    fun getXCenters(matrix: Array<Array<Char>>): Int {
        val size = matrix.size
        var counter = 0
        try {
            for (j in 1 until size-1){
                for (i in 1 until size - 1) {
                    if (matrix[j][i] == 'A') {
                        if ((isCenterOfLeftMAS(matrix, Pair(j,i)) || isCenterOfLeftSAM(matrix, Pair(j,i)) ) &&
                            (isCenterOfRightMAS(matrix, Pair(j,i)) || isCenterOfRightSAM(matrix, Pair(j,i)))) {
                            counter += 1
                        }
                    }

                }
            }
        }catch (e: Exception) {
            return -2
        }
        return counter
    }

    fun isCenterOfLeftMAS(matrix: Array<Array<Char>>, matrixCoordinatesOfA: Pair<Int, Int>): Boolean {
        val matrixY = matrixCoordinatesOfA.first
        val matrixX = matrixCoordinatesOfA.second
        val matrixYofM = matrixY - 1
        val matrixXofM = matrixX - 1
        val matrixYofS = matrixY + 1
        val matrixXofS = matrixX + 1
        if (matrix[matrixYofM][matrixXofM] == 'M' && matrix[matrixYofS][matrixXofS] == 'S'){
            return true
        }
        return false
    }

    fun isCenterOfLeftSAM(matrix: Array<Array<Char>>, matrixCoordinatesOfA: Pair<Int, Int>): Boolean {
        val matrixY = matrixCoordinatesOfA.first
        val matrixX = matrixCoordinatesOfA.second
        val matrixYofM = matrixY + 1
        val matrixXofM = matrixX + 1
        val matrixYofS = matrixY - 1
        val matrixXofS = matrixX - 1
        if (matrix[matrixYofM][matrixXofM] == 'M' && matrix[matrixYofS][matrixXofS] == 'S'){
            return true
        }
        return false
    }

    fun isCenterOfRightMAS(matrix: Array<Array<Char>>, matrixCoordinatesOfA: Pair<Int, Int>): Boolean {
        val matrixY = matrixCoordinatesOfA.first
        val matrixX = matrixCoordinatesOfA.second
        val matrixYofM = matrixY + 1
        val matrixXofM = matrixX - 1
        val matrixYofS = matrixY - 1
        val matrixXofS = matrixX + 1
        if (matrix[matrixYofM][matrixXofM] == 'M' && matrix[matrixYofS][matrixXofS] == 'S'){
            return true
        }
        return false
    }

    fun isCenterOfRightSAM(matrix: Array<Array<Char>>, matrixCoordinatesOfA: Pair<Int, Int>): Boolean {
        val matrixY = matrixCoordinatesOfA.first
        val matrixX = matrixCoordinatesOfA.second
        val matrixYofM = matrixY - 1
        val matrixXofM = matrixX + 1
        val matrixYofS = matrixY + 1
        val matrixXofS = matrixX - 1
        if (matrix[matrixYofM][matrixXofM] == 'M' && matrix[matrixYofS][matrixXofS] == 'S'){
            return true
        }
        return false
    }

    // day 5

    fun getRulesForUpdate(rules: List<Pair<Int, Int>>, update: List<Int>): List<Pair<Int, Int>> {
        val reducedRules : MutableList<Pair<Int, Int>> = mutableListOf()
        for (rule in rules){
            if (rule.first in update && rule.second in update){
                reducedRules.add(rule)
            }
        }
        return reducedRules
    }

    fun existsRuleWithLaterPage(rules: List<Pair<Int, Int>>, currentPage: Int, laterPage: Int): Boolean {
        val ruleToCheck = Pair(currentPage, laterPage)
        return rules.contains(ruleToCheck)
    }

    fun existsRuleWithEarlierPage(rules: List<Pair<Int, Int>>, currentPage: Int, earlierPage: Int): Boolean {
        val ruleToCheck = Pair(earlierPage, currentPage)
        return rules.contains(ruleToCheck)
    }

    fun isUpdateRightOrder(rules: List<Pair<Int, Int>>, update: List<Int>): Boolean {
        for (pageIndex in update.indices){
            for (pageToCompareIndex in update.indices ) {
                if (pageToCompareIndex < pageIndex) {
                    if (!existsRuleWithEarlierPage(
                            rules,
                            update[pageIndex],
                            update[pageToCompareIndex]
                        )
                    )
                        return false
                }
                if (pageToCompareIndex > pageIndex) {
                   if (!existsRuleWithLaterPage(
                        rules,
                        update[pageIndex],
                        update[pageToCompareIndex]
                       )
                   )
                        return false
                }
            }
        }
        return true
    }

    fun getMiddleNumber(update: List<Int>): Int {
        val size = update.size
        val middleIndex = (size)/2
        return update[middleIndex]
    }
}

enum class Direction {
    UP, RIGHT, DOWN, LEFT
}