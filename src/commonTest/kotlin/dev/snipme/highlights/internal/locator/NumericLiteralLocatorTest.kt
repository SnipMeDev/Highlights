package dev.snipme.highlights.internal.locator

import dev.snipme.highlights.internal.get
import dev.snipme.highlights.model.PhraseLocation
import kotlin.test.assertEquals
import kotlin.test.Test

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

        assertEquals(0, result.size)
    }

    @Test
    fun `Returns whole location of the all scientific notations`() {
        val testCode = """
            1e+10
            100e100
            0.11E-10
            123.456E+10
            100_00E10
            12e+1000
        """.trimIndent()

        val result = NumericLiteralLocator.locate(testCode)

        assertEquals(6, result.size)
        assertEquals(PhraseLocation(0, 5), result[0])
        assertEquals(PhraseLocation(6, 13), result[1])
        assertEquals(PhraseLocation(14, 22), result[2])
        assertEquals(PhraseLocation(23, 34), result[3])
        assertEquals(PhraseLocation(35, 44), result[4])
        assertEquals(PhraseLocation(45, 53), result[5])
    }

    @Test
    fun `Returns only proper length number with letter`() {
        val testCode = """
           12e+1000
           12.dp
           12f.d
           -2b
           12sss
           0b10000
           13.22f
        """.trimIndent()

        val result = NumericLiteralLocator.locate(testCode)

        assertEquals(7, result.size)
        assertEquals(PhraseLocation(0, 8), result[0])
        assertEquals(PhraseLocation(9, 12), result[1])
        assertEquals(PhraseLocation(15, 19), result[2])
        assertEquals(PhraseLocation(21, 23), result[3])
        assertEquals(PhraseLocation(25, 27), result[4])
        assertEquals(PhraseLocation(31, 38), result[5])
        assertEquals(PhraseLocation(39, 45), result[6])
    }
}