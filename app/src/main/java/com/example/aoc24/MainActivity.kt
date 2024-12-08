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
import org.jetbrains.kotlinx.multik.api.empty
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.zeros
import org.jetbrains.kotlinx.multik.ndarray.data.D2
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.data.set
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

@Composable
fun puzzle(day: Int, modifier: Modifier = Modifier): String {
    // Switch between test input and real input here //
   //val firstInput = true
      val firstInput = false
    val puzzleLetter = if (firstInput) "a" else "b"
    val content = getLines(filename = "day$day$puzzleLetter.txt") ?: return "not provided"

    val solutionPair = when (day) {
        1 -> Pair (getSolutionDay1First(content), getSolutionDay1Second(content))
        2 -> Pair (getSolutionDay2First(content), getSolutionDay2Second(content))
        3 -> Pair (getSolutionDay3First(content), getSolutionDay3Second(content))
        4 -> Pair (getSolutionDay4First(content), getSolutionDay4Second(content))
        5 -> Pair (getSolutionDay5First(content), getSolutionDay5Second(content))

        else -> Pair(1,2)
    }
    return solutionPair.toString()
}

fun getSolutionDay25Second(content: List<String>): Int {
    return -1
}

fun getSolutionDay25First(content: List<String>): Int {
    return -1
}

fun getSolutionDay5Second(content: List<String>): Int {
    return -1
}

fun getSolutionDay5First(content: List<String>): Int {
    val rules: MutableList<Pair<Int, Int>> = mutableListOf()
    val updates: MutableList<List<Int>> = mutableListOf()
    for (line in content){
        if (line.contains('|')){
            val pages = line.split('|')
            val rule = Pair(pages[0].toInt(), pages[1].toInt())
            rules.add(rule)
        }
        if (line.contains(',')){
            val updateStrings = line.split(',')
            val update : MutableList<Int> = mutableListOf()
            for (page in updateStrings){
                update.add(page.toInt())
            }
            updates.add(update.toList())
        }
    }
    var middlePageSum = 0
    val aoCFunctions = AoCFunctions()
    for (update in updates){
        if (aoCFunctions.isUpdateRightOrder(rules,update))
            middlePageSum += aoCFunctions.getMiddleNumber(update)
    }
    return middlePageSum
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
            LazyColumn(modifier = modifier) {
                items( items = days) { day ->
                    val solutionPair = puzzle(day = day, modifier = Modifier.padding(vertical = 4.dp))

                    Surface(
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                        shape = MaterialTheme.shapes.medium,
                        modifier = modifier.padding(vertical = 10.dp)){

                        Text(
                            text = "Solution for day  $day: ${solutionPair}",
                            modifier = Modifier.padding(all = 10.dp),
                            fontSize = 10.sp
                        )
                    }

                }
            }
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AoC24Theme {
        puzzle(1)
    }
}

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