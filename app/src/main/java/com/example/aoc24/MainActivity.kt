package com.example.aoc24

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aoc24.ui.theme.AoC24Theme
import java.io.InputStream
import java.lang.Math.abs
import kotlin.reflect.KFunction1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AoC24Theme {
                Column {
                    Solution()
                }
            }
        }
    }
}


fun puzzle(day: Int, testContent: List<String>, puzzleContent: List<String>): DayData {
    // Switch between test input and real input here //
   //val firstInput = true
     val firstInput = false
    val puzzleLetter = if (firstInput) "a" else "b"
    val content : List<String>
    if (firstInput) content = testContent
    else content = puzzleContent
    //val content = getLines(filename = "day$day$puzzleLetter.txt") ?: return "not provided"

    val solutionPair : Pair<Long, Long> = when (day) {
//        1 -> Pair (getSolutionDay1First(content).toLong(), getSolutionDay1Second(content).toLong())
//        2 -> Pair (getSolutionDay2First(content).toLong(), getSolutionDay2Second(content).toLong())
//        3 -> Pair (getSolutionDay3First(content).toLong(), getSolutionDay3Second(content).toLong())
//        4 -> Pair (getSolutionDay4First(content).toLong(), getSolutionDay4Second(content).toLong())
//        5 -> Pair (getSolutionDay5First(content).toLong(), getSolutionDay5Second(content).toLong())
     //   6 -> Pair (getSolutionDay6First(content).toLong(), getSolutionDay6Second(content).toLong())
     //   7 -> Pair (getSolutionDay7First(content), getSolutionDay7Second(content))
      //    7 -> Pair (-1, getSolutionDay7Second(content))
          // 9 -> Pair (getSolutionDay9First(content), getSolutionDay9Second(content).toLong())
           9 -> Pair (-1, getSolutionDay9Second(content).toLong())

        else -> Pair(1,2)
    }
    var dayData = DayData(firstInput, solutionPair)
    return dayData
}

fun getSolutionDay25Second(content: List<String>): Int {
    return -1
}

fun getSolutionDay25First(content: List<String>): Int {
    return -1
}

fun getSolutionDay9Second(content: List<String>): Long {
    val line = content[0]
    val aoCFunctions = AoCFunctions()
    val disk = aoCFunctions.getBlocks(line)
    val reorderedDisk = aoCFunctions.moveWholeFiles(disk)

    return aoCFunctions.calculateChecksum(reorderedDisk)
}

fun getSolutionDay9First(content: List<String>): Long {

    val line = content[0]
    val aoCFunctions = AoCFunctions()
    val disk = aoCFunctions.getBlocks(line)
    val reorderedDisk = aoCFunctions.moveBlocks(disk)

    return aoCFunctions.calculateChecksum(reorderedDisk)
}

fun getSolutionDay7Second(content: List<String>): Long {
    var sum : Long = 0
    for (line in content){
        val parts = line.split(": ")
        val result = parts[0].toLong()
        val numbers = parts[1].split(' ').map { it.toInt()  }
        val aoCFunctions = AoCFunctions()
        if (aoCFunctions.getTernaryOperationResult(result, numbers)) sum += result
    }
    return sum
}

fun getSolutionDay7First(content: List<String>): Long {
var sum : Long = 0
    for (line in content){
        val parts = line.split(": ")
        val result = parts[0].toLong()
        val numbers = parts[1].split(' ').map { it.toInt()  }
        val aoCFunctions = AoCFunctions()
        if (aoCFunctions.getOperationResult(result, numbers)) sum += result
    }
    return sum
}

fun getSolutionDay6Second(content: List<String>): Int {
    val map = get2DarrayOfChar(content)

    val aoCFunctions = AoCFunctions()
    val startingPosition = aoCFunctions.getStartingPosition(map)
    map[startingPosition.first][ startingPosition.second] = 'X'
    var currentPosition = startingPosition
    var currentDirection = Direction.UP
    var currentMove = NextMove.MOVE
    var nextPosition : Pair<Int, Int>
    var nextMove : NextMove
    val size = map.size
    val previousDirectionsMap = Array(size) { Array<MutableSet<Direction>>(size) { mutableSetOf() } }
    previousDirectionsMap[startingPosition.first][startingPosition.second].add(currentDirection)
   aoCFunctions.addDirectionToMap(previousDirectionsMap, startingPosition, Direction.UP)
    var obstacleCounter = 0
    var possibleNextDirection : Direction
    var possibleSecondNextDirection : Direction
    do{
        nextPosition = aoCFunctions.getNextCoordinates(currentPosition, currentDirection)
        map[currentPosition.first][currentPosition.second] = 'X'
        if (aoCFunctions.isPositionOffMap(nextPosition, size))
            currentMove = NextMove.END
        else  {
            // obstacle possible?
            possibleNextDirection = aoCFunctions.changeDirection(currentDirection)
            if (previousDirectionsMap[currentPosition.first][currentPosition.second].contains(possibleNextDirection)){
                obstacleCounter += 1
            }
            //distant obstacle possible?
            possibleSecondNextDirection = aoCFunctions.changeDirection(possibleNextDirection)
            aoCFunctions.unusedObstacle(previousDirectionsMap, currentPosition, possibleNextDirection)


            // calculate next move
            nextMove = aoCFunctions.getNextMove(map[nextPosition.first][nextPosition.second])
            if (nextMove == NextMove.TURN){
                currentDirection = aoCFunctions.changeDirection(currentDirection)
            }
           aoCFunctions.addDirectionToMap(previousDirectionsMap, currentPosition, currentDirection)

            if (nextMove == NextMove.MOVE) {
                currentPosition = nextPosition
            }
        }
        aoCFunctions.getNextMove(map[currentPosition.first][ currentPosition.second])
    } while (currentMove != NextMove.END)

    return obstacleCounter

}

fun getSolutionDay6First(content: List<String>): Int {
    val map = get2DarrayOfChar(content)

    val aoCFunctions = AoCFunctions()
    val startingPosition = aoCFunctions.getStartingPosition(map)
    map[startingPosition.first][ startingPosition.second] = 'X'
    var currentPosition = startingPosition
    var currentDirection = Direction.UP
    var currentMove = NextMove.MOVE
    var nextPosition : Pair<Int, Int>
    var nextMove : NextMove
    val size = map.size
    do{
        nextPosition = aoCFunctions.getNextCoordinates(currentPosition, currentDirection)
        map[currentPosition.first][currentPosition.second] = 'X'
        if (aoCFunctions.isPositionOffMap(nextPosition, size))
            currentMove = NextMove.END
        else  {
            nextMove = aoCFunctions.getNextMove(map[nextPosition.first][nextPosition.second])
            if (nextMove == NextMove.TURN){
                currentDirection = aoCFunctions.changeDirection(currentDirection)
            } else {
                currentPosition = nextPosition
            }
        }
        //aoCFunctions.getNextMove(map[currentPosition.first][ currentPosition.second])
    } while (currentMove != NextMove.END)

    return aoCFunctions.countX(map)
}

fun getSolutionDay5Second(content: List<String>): Int {
    val (rules: MutableList<Pair<Int, Int>>, updates: MutableList<List<Int>>) = getRulesAndUpdates(content)
    var middlePageSum = 0
    val aoCFunctions = AoCFunctions()
    for (update in updates){
        if (!aoCFunctions.isUpdateRightOrder(rules,update)) {
            val correctedUpdate = aoCFunctions.correctOrder(rules, update)
            middlePageSum += aoCFunctions.getMiddleNumber(correctedUpdate)
        }
    }
    return middlePageSum
}

fun getSolutionDay5First(content: List<String>): Int {
    val (rules: MutableList<Pair<Int, Int>>, updates: MutableList<List<Int>>) = getRulesAndUpdates(content)
    var middlePageSum = 0
    val aoCFunctions = AoCFunctions()
    for (update in updates){
        if (aoCFunctions.isUpdateRightOrder(rules,update))
            middlePageSum += aoCFunctions.getMiddleNumber(update)
    }
    return middlePageSum
}

private fun getRulesAndUpdates(content: List<String>): Pair<MutableList<Pair<Int, Int>>, MutableList<List<Int>>> {
    val rules: MutableList<Pair<Int, Int>> = mutableListOf()
    val updates: MutableList<List<Int>> = mutableListOf()
    for (line in content) {
        if (line.contains('|')) {
            val pages = line.split('|')
            val rule = Pair(pages[0].toInt(), pages[1].toInt())
            rules.add(rule)
        }
        if (line.contains(',')) {
            val updateStrings = line.split(',')
            val update: MutableList<Int> = mutableListOf()
            for (page in updateStrings) {
                update.add(page.toInt())
            }
            updates.add(update.toList())
        }
    }
    return Pair(rules, updates)
}

fun getSolutionDay4Second(content: List<String>): Int {
    val twoDArray = get2DarrayOfChar(content)
    val aoCFunctions = AoCFunctions()
    val result = aoCFunctions.getXCenters(twoDArray)
    return result
}

fun getSolutionDay4First(content: List<String>): Int {
    val twoDArray = get2DarrayOfChar(content)
    val aoCFunctions = AoCFunctions()
    val result = aoCFunctions.countAllStrings(twoDArray, "XMAS")
    return result
}

private fun get2DarrayOfChar(content: List<String>): Array<Array<Char>> {
    val size = content[0].length
    val twoDArray = Array(size) { Array<Char>(size) { '.' } }
    for (j in content.indices) {
        for (i in content.indices) {
            twoDArray[j][i] = content[j].get(i)
        }
    }
    return twoDArray
}

fun getSolutionDay3Second(content: List<String>): Int {
    val aoCFunctions = AoCFunctions()
    val function = aoCFunctions::getActiveMultiplications
    val result = day3Function(content, aoCFunctions, function)
    return result
}

fun getSolutionDay3First(content: List<String>): Int {
    val aoCFunctions = AoCFunctions()
    val function = aoCFunctions::getMultiplications
    val result = day3Function(content, aoCFunctions, function)
    return result
}

private fun day3Function(
    content: List<String>,
    aoCFunctions: AoCFunctions,
    function: KFunction1<String, List<String>>
): Int {
    var string = ""
    for (s in content) {
        string += s
    }
    val result = aoCFunctions.getMultiplicationSum(string, getMultiplicationFunction = function)
    return result
}


private fun getSolutionDay2First(
    content: List<String>
): Int {
    val listOfLists: MutableList<List<Int>> = getLinesWithIntegers(content)

    try {
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getSafeReports(listOfLists.toList(), gradingFunction = aoCFunctions::gradeReportFirst)
        return result
    }  catch (ex: Exception) {
        return -1
    }
}

private fun getLinesWithIntegers(content: List<String>): MutableList<List<Int>> {
    val listOfLists: MutableList<List<Int>> = mutableListOf()
    for (line in content) {
        val stringList = line.split(" ").toList()
        val list = mutableListOf<Int>()
        for (element in stringList) {
            list.add(element.toInt())
        }
        listOfLists.add(list.toList())
    }
    return listOfLists
}

private fun getSolutionDay2Second(
    content: List<String>
): Int {
    val listOfLists: MutableList<List<Int>> = getLinesWithIntegers(content)

    try {
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getSafeReports(listOfLists.toList(), gradingFunction = aoCFunctions::gradeReportSecond)
        return result
    }  catch (ex: Exception) {
        return -1
    }
}

private fun getSolutionDay1First(
    content: List<String>
): Int {
    val firstList = mutableListOf<Int>()
    val secondList = mutableListOf<Int>()
    for (line in content){
        //text += line
        val numbers = line.split(" ")
        firstList.add(numbers[0].toInt())
        secondList.add(numbers.last().toInt())

    }
    val text = "-"

    var text1 = text

    val differences = mutableListOf<Int>()
    firstList.sort()
    secondList.sort()
    for (i in firstList.indices) {
        val difference = abs(firstList[i] - secondList[i])
        //text += difference.toString()
        differences.add(difference)
    }
    text1 += "-" + differences.sum()


    //Text(text = text1, modifier = Modifier, fontSize = 6.sp)
    return differences.sum()
}

private fun getSolutionDay1Second(
    content: List<String>
): Int {
    val firstList = mutableListOf<Int>()
    val secondList = mutableListOf<Int>()
    for (line in content){

        val numbers = line.split(" ")
        firstList.add(numbers[0].toInt())
        secondList.add(numbers.last().toInt())

    }
    var text = "-"

    var text1 = text

    val similarities = mutableListOf<Int>()
    firstList.sort()
    //secondList.sort()
    for (i in firstList.indices) {
        val currentfirstListItem =  firstList[i]
        val occurences = secondList.count{it == currentfirstListItem}
        val similarity = currentfirstListItem * occurences
        //val difference = abs(firstList[i] - secondList[i])
        text += similarity.toString()
        similarities.add(similarity)
    }
    text1 += "-" + similarities.sum()


    //Text(text = text1, modifier = Modifier, fontSize = 6.sp)
    return similarities.sum()

}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    days: List<Int> = List(10) {it+1}){
    val context = LocalContext.current
    val solutions = getLines(filename = "solutions.txt")
    val solutionsList : MutableList<DayData> = mutableListOf()
    LazyColumn(modifier = modifier) {
                items( items = days) { day ->
                    val testContent = getLines(filename = "day"+ day + "a.txt") ?: return@items
                    val puzzleContent = getLines(filename = "day"+ day + "b.txt") ?: return@items
                    val dayData = puzzle(day = day, testContent = testContent, puzzleContent = puzzleContent)
                    val resultString : String
                    resultString = if (dayData.part1) "part1: " + dayData.solutionPair
                    else "part2: " + dayData.solutionPair
                    solutionsList.add(dayData)
                    Surface(
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                        shape = MaterialTheme.shapes.medium,
                        modifier = modifier.padding(vertical = 10.dp)){

                        Text(
                            text = "Solution for day  $day: $resultString",
                            modifier = Modifier.padding(all = 10.dp),
                            fontSize = 10.sp
                        )
                    }

                }
            }
   // val json = Json.encodeToString(solutionsList)

//        val file = File("solutions.txt")
//        file.writeText("huhu")
//    val myOutput = FileOutputStream("solutions.txt")
//    myOutput.write("huhu".toByteArray())
//    try {
//        val fos: FileOutputStream =
//            context.openFileOutput("demoFile.txt", Context.MODE_PRIVATE)
//        fos.write("huhu".toByteArray())
//        fos.flush()
//        fos.close()
//    } catch (e: IOException) {
//        e.printStackTrace()
//    }

}

@Composable
private fun Solution(
    modifier: Modifier = Modifier){
    Scaffold(modifier = modifier.padding(all = 16.dp)) {innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)){
            Text("AoC Puzzles")
            Greetings()
        }

    }
}


@Composable
private fun getLines (filename: String): List<String>? {
    val context = LocalContext.current
    val content = try {
        val myAssets = context.getAssets()

        val input: InputStream = myAssets.open(filename)
        input.bufferedReader().readLines()
        //File(filename).readLines()
    } catch (e: Exception) {
        return null
    }
    return content
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    AoC24Theme {
//        puzzle(1)
//    }
//}

@Preview(showBackground = true)
@Composable
fun GreetingsPreview() {
    AoC24Theme {
        Greetings()
    }
}

@Preview(showBackground = true)
@Composable
fun SolutionPreview() {
    AoC24Theme {
        Solution()
    }
}



//@Serializable
data class DayData(
    val part1: Boolean,
    var solutionPair: Pair<Long, Long>
)

//@Serializable
//data class DayDataMap(
//    val dataMap: Map<Int, DayData>
//)