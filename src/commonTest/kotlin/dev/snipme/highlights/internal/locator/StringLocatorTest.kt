package dev.snipme.highlights.internal.locator

import dev.snipme.highlights.internal.get
import dev.snipme.highlights.model.PhraseLocation
import kotlin.test.Test
import kotlin.test.assertEquals

internal class StringLocatorTest {
    @Test
    fun `Returns location of full string`() {
        val testCode = """
            "a"
        """.trimIndent()

        val result = StringLocator.locate(testCode)

        assertEquals(1, result.size)
        assertEquals(PhraseLocation(0, 3), result[0])
    }

    @Test
    fun `Returns location of character phrase`() {
        val testCode = """
            'a'
        """.trimIndent()

        val result = StringLocator.locate(testCode)

        assertEquals(1, result.size)
        assertEquals(PhraseLocation(0, 3), result[0])
    }

    @Test
    fun `Returns location of escaped character phrase`() {
        val testCode = """
            "\\"
        """.trimIndent()

        val result = StringLocator.locate(testCode)

        assertEquals(1, result.size)
        assertEquals(PhraseLocation(0, 4), result[0])
    }

    @Test
    fun `Returns location of multiple same strings`() {
        val testCode = """
            val a = "a"
            val b = "a"
        """.trimIndent()

        val result = StringLocator.locate(testCode)

        assertEquals(2, result.size)
        assertEquals(PhraseLocation(8, 11), result[0])
        assertEquals(PhraseLocation(20, 23), result[1])
    }

    @Test
    fun `Returns strings from simplest to most complex`() {
        val testCode = """
            val b = "a"
            val a = 'a'
        """.trimIndent()

        val result = StringLocator.locate(testCode)

        assertEquals(2, result.size)
        assertEquals(PhraseLocation(20, 23), result[0])
        assertEquals(PhraseLocation(8, 11), result[1])
    }

    @Test
    fun `No returns location of unclosed string phrase`() {
        val testCode = """
            val b = "a
            val a = 'a
        """.trimIndent()

        val result = StringLocator.locate(testCode)

        assertEquals(0, result.size)
    }

    @Test
    fun `Returns location only of closed string phrase`() {
        val testCode = """
            val b = 'a
            val a = "a"
        """.trimIndent()

        val result = StringLocator.locate(testCode)

        assertEquals(1, result.size)
        assertEquals(PhraseLocation(19, 22), result[0])
    }

    @Test
    fun `NOT returns location of escaped string phrase`() {
        val testCode = """
            val b = "a\""
            val a = 'a\''
        """.trimIndent()

        val result = StringLocator.locate(testCode)

        assertEquals(2, result.size)
        assertEquals(PhraseLocation(8, 12), result[1])
        assertEquals(PhraseLocation(22, 26), result[0])
    }
}