package com.example.aoc24

import org.junit.Test

import org.junit.Assert.*
class Day3Test {

    val aoCFunctions = AoCFunctions()

    val mulString1 = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
    val mulString2 = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"

    @Test
    fun instructionMul123By4(){
        val result = aoCFunctions.getMultiplications("mul(123,4)")
        val expectedResult : List<String> = listOf("mul(123,4)")
        assertEquals(expectedResult, result)
    }

    @Test
    fun instructionTooLongNumbers(){
        val result = aoCFunctions.getMultiplications("mul(1235,4)")
        val expectedResult : List<String> = listOf()
        assertEquals(expectedResult, result)
    }

    @Test
    fun instructionWrongLetter(){
        val result = aoCFunctions.getMultiplications("mulg(123,4)")
        val expectedResult : List<String> = listOf()
        assertEquals(expectedResult, result)
    }

    @Test
    fun instructionTwoMultiplications(){
        val result = aoCFunctions.getMultiplications("mul(669,732)mul(966,367)")
        val expectedResult : List<String> = listOf("mul(669,732)","mul(966,367)")
        assertEquals(expectedResult, result)
    }



    @Test
    fun instructionMissingSecondParameter(){
        val result = aoCFunctions.getMultiplications("mul(4*")
        val expectedResult : List<String> = listOf()
        assertEquals(expectedResult, result)
    }

    @Test
    fun instructionMissingSecondParanthesis(){
        val result = aoCFunctions.getMultiplications("mul(6,9!")
        val expectedResult : List<String> = listOf()
        assertEquals(expectedResult, result)
    }

    @Test
    fun instructionMissingCommand(){
        val result = aoCFunctions.getMultiplications("?(12,34)")
        val expectedResult : List<String> = listOf()
        assertEquals(expectedResult, result)
    }

    @Test
    fun instructionWithSpaces(){
        val result = aoCFunctions.getMultiplications("mul ( 2 , 4 )")
        val expectedResult : List<String> = listOf()
        assertEquals(expectedResult, result)
    }

    @Test
    fun instructionMul2By4(){
        val result = aoCFunctions.getMultiplications("mul(2,4)")
        val expectedResult : List<String> = listOf("mul(2,4)")
        assertEquals(expectedResult, result)
    }

    @Test
    fun instructionMul5By5(){
        val result = aoCFunctions.getMultiplications("mul(5,5)")
        val expectedResult : List<String> = listOf("mul(5,5)")
        assertEquals(expectedResult, result)
    }

    @Test
    fun instructionMul11By8(){
        val result = aoCFunctions.getMultiplications("(mul(11,8)")
        val expectedResult : List<String> = listOf("mul(11,8)")
        assertEquals(expectedResult, result)
    }

    @Test
    fun instructionMulString(){
        val result = aoCFunctions.getMultiplications(mulString1)
        val expectedResult : List<String> = listOf("mul(2,4)", "mul(5,5)", "mul(11,8)", "mul(8,5)")
        assertEquals(expectedResult, result)
    }

    @Test
    fun instructionMultiplicantPairs(){
        val result = aoCFunctions.getNumberPairs(mulString1, getMultiplicationFunction = aoCFunctions::getMultiplications)
        val expectedResult : List<Pair<Int, Int>> = listOf(Pair(2,4), Pair(5,5), Pair(11,8), Pair(8,5))
        assertEquals(expectedResult, result)
    }

    @Test
    fun instructionMultiplication(){
        val result = aoCFunctions.getMultiplicationSum(mulString1, getMultiplicationFunction = aoCFunctions::getMultiplications)
        assertEquals(161, result)
    }

    @Test
    fun instructiondo(){
        val result = aoCFunctions.getEnablingsAndMultiplications("do()")
        val expectedResult : List<String> = listOf("do()")
        assertEquals(expectedResult, result)
    }

    @Test
    fun instructiondomul2by4(){
        val result = aoCFunctions.getEnablingsAndMultiplications("do()mul(2,4)")
        val expectedResult : List<String> = listOf("do()", "mul(2,4)")
        assertEquals(expectedResult, result)
    }

    @Test
    fun instructiondodontmul2by4(){
        val result = aoCFunctions.getEnablingsAndMultiplications("do()don't()mul(2,4)")
        val expectedResult : List<String> = listOf("do()","don't()", "mul(2,4)")
        assertEquals(expectedResult, result)
    }

    @Test
    fun instructiondodontmul2by4plusLetters(){
        val result = aoCFunctions.getEnablingsAndMultiplications("do()ffdon't()?mul(2,4)")
        val expectedResult : List<String> = listOf("do()","don't()", "mul(2,4)")
        assertEquals(expectedResult, result)
    }

    @Test
    fun instructionActiveMultiplication(){
        val result = aoCFunctions.getActiveMultiplications("do()ffdon't()?mul(2,4)")
        val expectedResult : List<String> = listOf()
        assertEquals(expectedResult, result)
    }

    @Test
    fun instructionActiveMultiplicationMul2By4(){
        val result = aoCFunctions.getActiveMultiplications("do()ffdon't()do()?mul(2,4)")
        val expectedResult : List<String> = listOf("mul(2,4)")
        assertEquals(expectedResult, result)
    }

    @Test
    fun instructionMultiplicationWithEnabling(){
        val result = aoCFunctions.getMultiplicationSum("do()ffdon't()do()?mul(2,4)", getMultiplicationFunction = aoCFunctions::getActiveMultiplications)
        assertEquals(8, result)
    }

    @Test
    fun instructionMultiplicationWithDisabling(){
        val result = aoCFunctions.getMultiplicationSum("do()ffdon't()don't()?mul(2,4)", getMultiplicationFunction = aoCFunctions::getActiveMultiplications)
        assertEquals(0, result)
    }

    @Test
    fun instructionFullMultiplicationFirst(){
        val result = aoCFunctions.getMultiplicationSum(mulString2, getMultiplicationFunction = aoCFunctions::getActiveMultiplications)
        assertEquals(48, result)
    }
}