package dev.snipme.highlights.internal

import kotlin.test.Test
import kotlin.test.assertEquals

internal class CodeComparatorTest {

    @Test
    fun `Returns none difference for empty phrases`() {
        val currentCode = ""
        val newCode = ""

        val result = CodeComparator.compare(currentCode, newCode)

        assertEquals(CodeDifference.None, result)
    }

    @Test
    fun `Returns none difference for blank phrases`() {
        val currentCode = "  "
        val newCode = "  "

        val result = CodeComparator.compare(currentCode, newCode)

        assertEquals(CodeDifference.None, result)
    }

    @Test
    fun `Returns none difference for whitespace phrases`() {
        val currentCode = "\r"
        val newCode = "\r\r"

        val result = CodeComparator.compare(currentCode, newCode)

        assertEquals(CodeDifference.None, result)
    }

    @Test
    fun `Returns none difference for the same phrases`() {
        val currentCode = "@ABCD"
        val newCode = "@ABCD"

        val result = CodeComparator.compare(currentCode, newCode)

        assertEquals(CodeDifference.None, result)
    }

    @Test
    fun `Returns increase difference for the longer new phrase`() {
        val currentCode = "@ABCD"
        val newCode = "@ABCD abcd dd ee"

        val result = CodeComparator.compare(currentCode, newCode)

        assertEquals(CodeDifference.Increase("abcd dd ee"), result)
    }

    @Test
    fun `Returns decrease difference for the shorter new phrase`() {
        val currentCode = "@ABCD abcd dd"
        val newCode = "@ABCD"

        val result = CodeComparator.compare(currentCode, newCode)

        assertEquals(CodeDifference.Decrease("abcd dd"), result)
    }

    @Test
    fun `Returns none difference for the mixed new phrase`() {
        val currentCode = "@ABCD abcd dd ee"
        val newCode = "@ABCD abcd ee dd"

        val result = CodeComparator.compare(currentCode, newCode)

        assertEquals(CodeDifference.None, result)
    }
}