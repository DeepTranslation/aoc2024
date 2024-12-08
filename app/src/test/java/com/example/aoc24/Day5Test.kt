package com.example.aoc24

import org.junit.Test
import org.junit.Assert.*

class Day5Test {
    val rules = listOf(Pair(75, 47), Pair(75,61), Pair(75,53), Pair(75,29), Pair(47,61),
        Pair(47,53), Pair(47,29), Pair(61,53), Pair(61,29), Pair(53,29))
    val update= listOf(75,47,61,53,29)

    @Test
    fun rulesForUpdate(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getRulesForUpdate(rules, update)
        assertEquals(rules, result)
    }

    @Test
    fun someRulesForUpdate(){
        val aoCFunctions = AoCFunctions()
        val reducedUpdate = update.toMutableList()
        reducedUpdate.removeFirst()
        val reducedRules = rules.toMutableList()
        for (i in 0 until 4){
            reducedRules.removeAt(0)
        }
        val result = aoCFunctions.getRulesForUpdate(rules, reducedUpdate.toList())
        assertEquals(reducedRules.toList(), result)
    }

    @Test
    fun ruleWithLaterPage(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.existsRuleWithLaterPage(rules, 75, 47)
        assertEquals(true, result)
    }

    @Test
    fun ruleWithLaterPageFalse(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.existsRuleWithLaterPage(rules, 47, 75)
        assertEquals(false, result)
    }
    @Test
    fun ruleWithEarlierPage(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.existsRuleWithEarlierPage(rules, 47, 75 )
        assertEquals(true, result)
    }

    @Test
    fun ruleWithEarlierPageFalse(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.existsRuleWithEarlierPage(rules, 75, 47 )
        assertEquals(false, result)
    }

    @Test
    fun isUpdateRightOrder(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.isUpdateRightOrder(rules, update)
        assertEquals(true, result)
    }

    @Test
    fun middleNumber(){
        val aoCFunctions = AoCFunctions()
        val result = aoCFunctions.getMiddleNumber(update)
        assertEquals(61, result)
    }

    @Test
    fun correctOrder(){
        val aoCFunctions = AoCFunctions()
        val changedUpdate = update.toMutableList()
        val page3 = changedUpdate.get(2)
        changedUpdate.removeAt(2)
        changedUpdate.add(page3)
        val result = aoCFunctions.correctOrder(rules, changedUpdate.toList())
        assertEquals(update, result)
    }
}