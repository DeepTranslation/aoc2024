package com.example.aoc24

import org.junit.Test
import org.junit.Assert.*

class Day9Test {

    @Test
    fun increasingNumbers(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.compareTwoNumbers(1, 2)
        assertEquals("inc", result)
    }

}