package com.example.aoc24



import org.junit.Test

import org.junit.Assert.*

class Day2Test {

    val listOfLists = listOf(
        listOf(7, 6, 4, 2, 1),
        listOf(1, 2, 7, 8, 9),
        listOf(9, 7, 6, 2, 1),
        listOf(1, 3, 2, 4, 5),
        listOf(8, 6, 4, 4, 1),
        listOf(1, 3, 6, 7, 9)
    )

    @Test
    fun increasingNumbers(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.compareTwoNumbers(1, 2)
        assertEquals("inc", result)
    }

    @Test
    fun decreasingNumbers(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.compareTwoNumbers(2, 1)
        assertEquals("dec", result)
    }

    @Test
    fun equalNumbers(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.compareTwoNumbers(2, 2)
        assertEquals("unsafe", result)
    }

    @Test
    fun increaseAbove3(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.compareTwoNumbers(2, 7)
        assertEquals("unsafe", result)
    }

    @Test
    fun decreaseAbove3(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.compareTwoNumbers(6, 2)
        assertEquals("unsafe", result)
    }

    @Test
    fun bothIncrease(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.comparePreviousAndCurrentIncline("inc", "inc")
        assertEquals("safe", result)
    }

    @Test
    fun bothDecrease(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.comparePreviousAndCurrentIncline("dec", "dec")
        assertEquals("safe", result)
    }

    @Test
    fun decreaseAndIncrease(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.comparePreviousAndCurrentIncline("dec", "inc")
        assertEquals("unsafe", result)
    }

    @Test
    fun decreasingReport(){
        val aoCFunctions = AoCFunctions()
        val list = listOf(7, 6, 4, 2, 1)
        val result = aoCFunctions.gradeReportFirst(list)
        assertEquals("safe", result)
    }

    @Test
    fun increasingReport(){
        val aoCFunctions = AoCFunctions()
        val list = listOf(1, 3, 6, 7, 9)
        val result = aoCFunctions.gradeReportFirst(list)
        assertEquals("safe", result)
    }

    @Test
    fun reportwithEqualNumbers(){
        val aoCFunctions = AoCFunctions()
        val list = listOf(8, 6, 4, 4, 1)
        val result = aoCFunctions.gradeReportFirst(list)
        assertEquals("unsafe", result)
    }

    @Test
    fun reportwithExcessiveIncrease(){
        val aoCFunctions = AoCFunctions()
        val list = listOf(1, 2, 7, 8, 9)
        val result = aoCFunctions.gradeReportFirst(list)
        assertEquals("unsafe", result)
    }

    @Test
    fun reportwithExcessiveDecrease(){
        val aoCFunctions = AoCFunctions()
        val list = listOf(9, 7, 6, 2, 1)
        val result = aoCFunctions.gradeReportFirst(list)
        assertEquals("unsafe", result)
    }



    @Test
    fun reportwithIncreaseAndDecrease(){
        val aoCFunctions = AoCFunctions()
        val list = listOf(1, 3, 2, 4, 5)
        val result = aoCFunctions.gradeReportFirst(list)
        assertEquals("unsafe", result)
    }

    @Test
    fun reportwithDampenedIncrease(){
        val aoCFunctions = AoCFunctions()
        val list = listOf(1, 3, 2, 4, 5)
        val result = aoCFunctions.gradeReportSecond(list)
        assertEquals("safe", result)
    }

    @Test
    fun reportwithDampenedEqualNumbers(){
        val aoCFunctions = AoCFunctions()
        val list = listOf(8, 6, 4, 4, 1)
        val result = aoCFunctions.gradeReportSecond(list)
        assertEquals("safe", result)
    }

    @Test
    fun correctnumberOfSafeReports(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getSafeReports(listOfLists, gradingFunction = aoCFunctions::gradeReportFirst)
        assertEquals(2, result)
    }

    @Test
    fun correctnumberOfSafeReportsWithEqualLines(){
        val aoCFunctions = AoCFunctions()
        val mutableListofLists = listOfLists.toMutableList()
        val list = listOfLists.last()
        mutableListofLists.add(list)
        val result = aoCFunctions.getSafeReports(mutableListofLists.toList(), gradingFunction = aoCFunctions::gradeReportFirst)
        assertEquals(3, result)
    }

    @Test
    fun correctnumberOfSafeReportsSecond(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getSafeReports(listOfLists, gradingFunction = aoCFunctions::gradeReportSecond)
        assertEquals(4, result)
    }

    @Test
    fun correctnumberOfSafeReportsWithEqualLinesSecond(){
        val aoCFunctions = AoCFunctions()
        val mutableListofLists = listOfLists.toMutableList()
        val list = listOfLists.last()
        mutableListofLists.add(list)
        val result = aoCFunctions.getSafeReports(mutableListofLists.toList(), gradingFunction = aoCFunctions::gradeReportSecond)
        assertEquals(5, result)
    }

}