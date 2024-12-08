package com.example.aoc24

import org.junit.Test
import org.junit.Assert.*

class Day6Test {
    val matrix: Array<Array<Char>> =
        arrayOf(arrayOf('.','#','#','.'),
                arrayOf('.','.','.','#'),
                arrayOf('.','^','X','.'),
                arrayOf('X','X','.','.'))

    @Test
    fun changeDirectionUp(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.changeDirection(Direction.UP)
        assertEquals(Direction.RIGHT, result)
    }

    @Test
    fun changeDirectionRight(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.changeDirection(Direction.RIGHT)
        assertEquals(Direction.DOWN, result)
    }

    @Test
    fun changeDirectionDown(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.changeDirection(Direction.DOWN)
        assertEquals(Direction.LEFT, result)
    }

    @Test
    fun changeDirectionLeft(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.changeDirection(Direction.LEFT)
        assertEquals(Direction.UP, result)
    }

    @Test
    fun nextMoveObstruction(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getNextMove('#')
        assertEquals(NextMove.TURN, result)
    }

    @Test
    fun nextMoveEmpty(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getNextMove('.')
        assertEquals(NextMove.MOVE, result)
    }

    @Test
    fun nextMoveX(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getNextMove('X')
        assertEquals(NextMove.MOVE, result)
    }

    @Test
    fun nextCoordinatesUp(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getNextCoordinates(Pair(2,2), Direction.UP)
        assertEquals(Pair(1,2), result)
    }

    @Test
    fun nextCoordinatesRight(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getNextCoordinates(Pair(2,2), Direction.RIGHT)
        assertEquals(Pair(2,3), result)
    }

    @Test
    fun nextCoordinatesDown(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getNextCoordinates(Pair(2,2), Direction.DOWN)
        assertEquals(Pair(3,2), result)
    }

    @Test
    fun nextCoordinatesLeft(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getNextCoordinates(Pair(2,2), Direction.LEFT)
        assertEquals(Pair(2,1), result)
    }

    @Test
    fun offMap(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.isPositionOffMap(Pair(2,2), 8)
        assertEquals(false, result)
    }

    @Test
    fun offMapUp(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.isPositionOffMap(Pair(-1,2), 8)
        assertEquals(true, result)
    }

    @Test
    fun offMapRight(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.isPositionOffMap(Pair(-1,8), 8)
        assertEquals(true, result)
    }

    @Test
    fun offMapDown(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.isPositionOffMap(Pair(8,2), 8)
        assertEquals(true, result)
    }

    @Test
    fun offMapLeft(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.isPositionOffMap(Pair(2,-1), 8)
        assertEquals(true, result)
    }

    @Test
    fun startingPosition(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getStartingPosition(matrix)
        assertEquals(Pair(2,1), result)
    }

    @Test
    fun countX(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.countX(matrix)
        assertEquals(3, result)
    }
}