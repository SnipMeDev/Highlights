package dev.snipme.highlights.internal.locator

import dev.snipme.highlights.internal.printResults
import dev.snipme.highlights.model.PhraseLocation
import org.junit.Test
import kotlin.test.assertEquals

internal class NumericLiteralLocatorTest {

    @Test
    fun `Returns location of single digit`() {
        val testCode = "val one = 1"

        val result = NumericLiteralLocator.locate(testCode)

        assertEquals(1, result.size)
        assertEquals(PhraseLocation(10, 11), result.first())
    }

    @Test
    fun `Returns location of many digits number`() {
        val testCode = "val one = 123"

        val result = NumericLiteralLocator.locate(testCode)

        assertEquals(1, result.size)
        assertEquals(PhraseLocation(10, 13), result.first())
    }

    @Test
    fun `Returns location of decimal number`() {
        val testCode = "val one = 123.456"

        val result = NumericLiteralLocator.locate(testCode)

        assertEquals(1, result.size)
        assertEquals(PhraseLocation(10, 17), result.first())
    }

    // Detailed description of underscore literal
    // https://www.educative.io/answers/what-are-the-underscores-in-numeric-literals-in-java
    @Test
    fun `Returns location of proper underscore number`() {
        val testCode = "val one = 1_23__456".trimIndent()

        val result = NumericLiteralLocator.locate(testCode)

        assertEquals(1, result.size)
        assertEquals(PhraseLocation(10, 19), result.first())
    }

    @Test
    fun `Returns location of floating number`() {
        val testCode = """
            val one = 123.456f
            val two = .2f
            val three = 12F
        """.trimIndent()

        val result = NumericLiteralLocator.locate(testCode)

        assertEquals(3, result.size)
        assertEquals(PhraseLocation(10, 18), result[0])
        assertEquals(PhraseLocation(29, 32), result[1])
        assertEquals(PhraseLocation(45, 48), result[2])
    }

    @Test
    fun `Returns location of number with letter`() {
        val testCode = """
            val one = 123456L
            val two = 2L
            val three = 12l
            val floating = 1.2f
            val longFloat = 1.22fff
            val zero = zero100
            12e + 13u
        """.trimIndent()

        val result = NumericLiteralLocator.locate(testCode)

        assertEquals(7, result.size)
        assertEquals(PhraseLocation(10, 17), result[0])
        assertEquals(PhraseLocation(28, 30), result[1])
        assertEquals(PhraseLocation(43, 46), result[2])
        assertEquals(PhraseLocation(62, 66), result[3])
        assertEquals(PhraseLocation(83, 88), result[4])
        assertEquals(PhraseLocation(110, 113), result[5])
        assertEquals(PhraseLocation(116, 119), result[6])
    }

    @Test
    fun `Returns location of hex number`() {
        val testCode = """
            0x
            0x0
            0xabcdef
            0x1ab2cdef
            0xabcdefg
            0xabgcdef
        """.trimIndent()

        val result = NumericLiteralLocator.locate(testCode)

        assertEquals(6, result.size)
        assertEquals(PhraseLocation(0, 2), result[0])
        assertEquals(PhraseLocation(3, 6), result[1])
        assertEquals(PhraseLocation(7, 15), result[2])
        assertEquals(PhraseLocation(16, 26), result[3])
        assertEquals(PhraseLocation(27, 35), result[4])
        assertEquals(PhraseLocation(37, 41), result[5])
    }

    @Test
    fun `Returns location of binary number`() {
        val testCode = """
            0b
            0b0
            0b1
            0b001
            0b9001
        """.trimIndent()

        val result = NumericLiteralLocator.locate(testCode)

        assertEquals(5, result.size)
        assertEquals(PhraseLocation(0, 2), result[0])
        assertEquals(PhraseLocation(3, 6), result[1])
        assertEquals(PhraseLocation(7, 10), result[2])
        assertEquals(PhraseLocation(11, 16), result[3])
        assertEquals(PhraseLocation(17, 19), result[4])
    }

    @Test
    fun `Not returns location of the literal characters only`() {
        val testCode = """
            ..
            LL
            xx
        """.trimIndent()

        val result = NumericLiteralLocator.locate(testCode)

        result.printResults(testCode)

        assertEquals(0, result.size)
    }
}