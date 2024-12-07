package com.example.aoc24

import org.junit.Test
import org.junit.Assert.*

class Day4Test {
    val matrix: Array<Array<Char>> = arrayOf(arrayOf('0','1'), arrayOf('2','3'))
    val bigMatrix: Array<Array<Char>> = arrayOf(arrayOf('0','1', '2'), arrayOf('3','4', '5'), arrayOf('6', '7', '8'))
    val matrix2: Array<Array<Char>> = arrayOf(arrayOf('M','M','M','S'), arrayOf('M','S','A','M'),
        arrayOf('A','M','X','S'), arrayOf('M','S','A','M'))

    @Test
    fun stringFromhorizontal(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getHorizontalStrings(matrix)
        assertEquals(listOf("01", "23"), result)
    }

    @Test
    fun stringFromVertical(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getVerticalStrings(matrix)
        assertEquals(listOf("02", "13"), result)
    }

    @Test
    fun stringFromLeftDiagonal(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getLeftDiagonalStrings(matrix)
        assertEquals(listOf("2", "03", "1"), result)
    }

    @Test
    fun stringFromRightDiagonal(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getRightDiagonalStrings(matrix)
        assertEquals(listOf("0", "21", "3"), result)
    }

    @Test
    fun stringFromRightDiagonalBigMatrix(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getRightDiagonalStrings(bigMatrix)
        assertEquals(listOf("0", "31", "642", "75", "8"), result)
    }

    @Test
    fun countHorizontalStrings(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.countHorizontalStrings(matrix2, "SA")
        assertEquals(2, result)
    }

    @Test
    fun countHorizontalReverseStrings(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.countHorizontalReversedStrings(matrix2, "SM")
        assertEquals(3, result)
    }

    @Test
    fun countVerticalStrings(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.countVerticalStrings(matrix2, "MS")
        assertEquals(3, result)
    }

    @Test
    fun countVerticalReverseStrings(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.countVerticalReversedStrings(matrix2, "AM")
        assertEquals(2, result)
    }

    @Test
    fun countLeftDiagonaltrings(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.countLeftDiagonalStrings(matrix2, "AS")
        assertEquals(2, result)
    }

    @Test
    fun countLeftVerticalReverseStrings(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.countLeftDiagonalReversedStrings(matrix2, "MS")
        assertEquals(0, result)
    }

    @Test
    fun countRightDiagonaltrings(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.countRightDiagonalStrings(matrix2, "AS")
        assertEquals(3, result)
    }

    @Test
    fun countRightVerticalReverseStrings(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.countRightDiagonalReversedStrings(matrix2, "MS")
        assertEquals(1, result)
    }

    @Test
    fun countAllStrings(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.countAllStrings(matrix2, "AS")
        assertEquals(7, result)
    }

    @Test
    fun isLeftMAS(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.isCenterOfLeftMAS(matrix2, Pair(1,2))
        assertEquals(true, result)
    }

    @Test
    fun isLeftSAM(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.isCenterOfLeftSAM(matrix2, Pair(1,2))
        assertEquals(false, result)
    }

    @Test
    fun isLRightMAS(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.isCenterOfRightMAS(matrix2, Pair(1,2))
        assertEquals(true, result)
    }

    @Test
    fun isLRightSAM(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.isCenterOfRightSAM(matrix2, Pair(1,2))
        assertEquals(false, result)
    }


    @Test
    fun getCenterOfMAS(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getXCenters(matrix2)
        assertEquals(1, result)
    }
}