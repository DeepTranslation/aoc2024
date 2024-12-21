package com.example.aoc24

import org.junit.Test
import org.junit.Assert.*

class Day7Test {

    @Test
    fun getOperationResult190(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getOperationResult(190, listOf(10, 19))
        assertEquals(true, result)
    }

    @Test
    fun getOperationResult191(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getOperationResult(191, listOf(10, 19))
        assertEquals(false, result)
    }

    @Test
    fun getOperationResult3267(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getOperationResult(3267, listOf(81, 40, 27))
        assertEquals(true, result)
    }

    @Test
    fun getOperationResult3268(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getOperationResult(3268, listOf(81, 40, 27))
        assertEquals(false, result)
    }

    @Test
    fun getOperationResult292(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getOperationResult(292, listOf(11, 6, 16, 20))
        assertEquals(true, result)
    }

    @Test
    fun getOperationResult272(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getOperationResult(272, listOf(11, 6, 16))
        assertEquals(true, result)
    }

    @Test
    fun getOperationResult17(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getOperationResult(17, listOf(11, 6))
        assertEquals(true, result)
    }

    @Test
    fun getOperationResult170(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getOperationResult(170, listOf(11, 6, 10))
        assertEquals(true, result)
    }

    @Test
    fun getOperationResult192(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getOperationResult(192, listOf(17, 8, 14))
        assertEquals(false, result)
    }

    @Test
    fun getOperationResult83(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getOperationResult(83, listOf(17, 5))
        assertEquals(false, result)
    }

    @Test
    fun getOperationResult156(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getOperationResult(156, listOf(15, 6))
        assertEquals(false, result)
    }

    @Test
    fun getOperationResult7290(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getOperationResult(7290, listOf(6, 8, 6, 15))
        assertEquals(false, result)
    }

    @Test
    fun getOperationResult161011(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getOperationResult(161011, listOf(16, 10, 13))
        assertEquals(false, result)
    }

    @Test
    fun getOperationResult21037(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getOperationResult(21037, listOf(9, 7, 18, 13))
        assertEquals(false, result)
    }

    @Test
    fun getTernaryOperationResult156(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getTernaryOperationResult(156, listOf(15, 6))
        assertEquals(true, result)
    }

    @Test
    fun getTernaryOperationResult7290(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getTernaryOperationResult(7290, listOf(6, 8, 6, 15))
        assertEquals(true, result)
    }

    @Test
    fun getTernaryOperationResult192(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getTernaryOperationResult(192, listOf(17, 8, 14))
        assertEquals(true, result)
    }

    @Test
    fun getTernaryOperationResult83(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getTernaryOperationResult(83, listOf(17, 5))
        assertEquals(false, result)
    }



    @Test
    fun getTernaryOperationResult161011(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getTernaryOperationResult(161011, listOf(16, 10, 13))
        assertEquals(false, result)
    }

    @Test
    fun getTernaryOperationResult21037(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getTernaryOperationResult(21037, listOf(9, 7, 18, 13))
        assertEquals(false, result)
    }

    @Test
    fun getTernaryOperationResult8113(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getTernaryOperationResult(8113, listOf(9, 7, 18, 13))
        assertEquals(true, result)
    }

    @Test
    fun convert48(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.convertDecimalToTernary(48)
        assertEquals("1210", result)
    }
    @Test
    fun convert121(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.convertDecimalToTernary(121)
        assertEquals("11111", result)
    }

    @Test
    fun convert637(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.convertDecimalToTernary(637)
        assertEquals("212121", result)
    }

    @Test
    fun getTernaryOperationResult190(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getTernaryOperationResult(190, listOf(10, 19))
        assertEquals(true, result)
    }

    @Test
    fun getTernaryOperationResult191(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getTernaryOperationResult(191, listOf(10, 19))
        assertEquals(false, result)
    }

    @Test
    fun getTernaryOperationResult3267(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getTernaryOperationResult(3267, listOf(81, 40, 27))
        assertEquals(true, result)
    }

    @Test
    fun getTernaryOperationResult3268(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getTernaryOperationResult(3268, listOf(81, 40, 27))
        assertEquals(false, result)
    }

    @Test
    fun getTernaryOperationResult292(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getTernaryOperationResult(292, listOf(11, 6, 16, 20))
        assertEquals(true, result)
    }

    @Test
    fun getTernaryOperationResult272(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getTernaryOperationResult(272, listOf(11, 6, 16))
        assertEquals(true, result)
    }

    @Test
    fun getTernaryOperationResult17(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getTernaryOperationResult(17, listOf(11, 6))
        assertEquals(true, result)
    }

    @Test
    fun getTernaryOperationResult170(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getTernaryOperationResult(170, listOf(11, 6, 10))
        assertEquals(true, result)
    }

    @Test
    fun getTernaryOperationResult11612(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getTernaryOperationResult(11612, listOf(11, 6, 12))
        assertEquals(true, result)
    }

    @Test
    fun getTernaryOperationResult11613(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getTernaryOperationResult(11613, listOf(11, 6, 12))
        assertEquals(false, result)
    }
}