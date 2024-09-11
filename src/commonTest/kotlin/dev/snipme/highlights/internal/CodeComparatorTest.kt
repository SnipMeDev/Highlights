package dev.snipme.highlights.internal

import kotlin.test.Test
import kotlin.test.assertEquals

internal class CodeComparatorTest {

    @Test
    fun `Returns none difference for empty phrases`() {
        val currentCode = ""
        val newCode = ""

        val result = CodeComparator.difference(currentCode, newCode)

        assertEquals(CodeDifference.None, result)
    }

    @Test
    fun `Returns none difference for blank phrases`() {
        val currentCode = "  "
        val newCode = "  "

        val result = CodeComparator.difference(currentCode, newCode)

        assertEquals(CodeDifference.None, result)
    }

    @Test
    fun `Returns none difference for whitespace phrases`() {
        val currentCode = "\r"
        val newCode = "\r\r"

        val result = CodeComparator.difference(currentCode, newCode)

        assertEquals(CodeDifference.None, result)
    }

    @Test
    fun `Returns none difference for the same phrases`() {
        val currentCode = "@ABCD"
        val newCode = "@ABCD"

        val result = CodeComparator.difference(currentCode, newCode)

        assertEquals(CodeDifference.None, result)
    }

    @Test
    fun `Returns increase difference for the longer new phrase`() {
        val currentCode = "@ABCD"
        val newCode = "@ABCD abcd dd ee"

        val result = CodeComparator.difference(currentCode, newCode)

        assertEquals(CodeDifference.Increase("abcd dd ee"), result)
    }

    @Test
    fun `Returns decrease difference for the shorter new phrase`() {
        val currentCode = "@ABCD abcd dd"
        val newCode = "@ABCD"

        val result = CodeComparator.difference(currentCode, newCode)

        assertEquals(CodeDifference.Decrease("abcd dd"), result)
    }

    @Test
    fun `Returns none difference for the mixed new phrase`() {
        val currentCode = "@ABCD abcd dd ee"
        val newCode = "@ABCD abcd ee dd"

        val result = CodeComparator.difference(currentCode, newCode)

        assertEquals(CodeDifference.None, result)
    }

//    @Test
//    fun `Returns difference for the char change in token`() {
//        val currentCode = "const foo = 'bar';"
//
//        val newCode = "const foo = 'baz';"
//
//        val result = CodeComparator.difference(currentCode, newCode)
//
//        assertEquals(CodeDifference.Swap("'bar'", "'baz'"), result)
//    }

//    @Test
//    fun `Returns difference for the char change in token`() {
//        val currentCode = "const foo = 'bar';"
//
//        val newCode = "co,st foo = 'bar';"
//
//        val result = CodeComparator.difference(currentCode, newCode)
//
//        assertEquals(CodeDifference.Swap("const", "co,st"), result)
//    }
//
//    @Test
//    fun `Returns difference for the char addition in single token`() {
//        val currentCode = "const foo = 'bar';"
//
//        val newCode = "const foo = 'barrr';"
//
//        val result = CodeComparator.difference(currentCode, newCode)
//
//        assertEquals(CodeDifference.Swap("'bar'", "'barrr'"), result)
//    }
//
//    @Test
//    fun `Returns difference for the char subtraction in single token`() {
//        val currentCode = "const foo = 'barrr';"
//
//        val newCode = "const foo = 'bar';"
//
//        val result = CodeComparator.difference(currentCode, newCode)
//
//        assertEquals(CodeDifference.Swap("'barrr'", "'bar'"), result)
//    }

    @Test
    fun `Returns only difference for complex code change`() {
        val currentCode = """
            /** a */
            // b
            class C extends {}
            "d";
            @E
            ...
            123.00f
        """.trimIndent()

        val newCode = """
            /** a */
            // b
            class C extends {}
            "d";
            @E
            ...
            123.00f
            
            field.forEach { }
        """.trimIndent()

        val result = CodeComparator.difference(currentCode, newCode)

        assertEquals(CodeDifference.Increase(" field.forEach { }"), result)
    }
}