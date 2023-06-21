package dev.snipme.highlights.internal.locator

import dev.snipme.highlights.model.PhraseLocation
import kotlin.test.Test
import kotlin.test.assertEquals

internal class PunctuationLocatorTest {

    @Test
    fun `Returns location of punctuation characters`() {
        val testCode = """
            , .  :  ;
        """.trimIndent()

        val result = PunctuationLocator.locate(testCode)

        assertEquals(4, result.size)
        assertEquals(PhraseLocation(0, 1), result[0])
        assertEquals(PhraseLocation(2, 3), result[1])
        assertEquals(PhraseLocation(5, 6), result[2])
        assertEquals(PhraseLocation(8, 9), result[3])
    }

    @Test
    fun `Returns multiple locations of the same punctuation`() {
        val testCode = """
            , ); ),
        """.trimIndent()

        val result = PunctuationLocator.locate(testCode)

        assertEquals(3, result.size)
        assertEquals(PhraseLocation(0, 1), result[0])
        assertEquals(PhraseLocation(6, 7), result[1])
        assertEquals(PhraseLocation(3, 4), result[2])
    }

    @Test
    fun `Returns locations of the punctuation next to each other`() {
        val testCode = """
            ,,,
        """.trimIndent()

        val result = PunctuationLocator.locate(testCode)

        assertEquals(3, result.size)
        assertEquals(PhraseLocation(0, 1), result[0])
        assertEquals(PhraseLocation(1, 2), result[1])
        assertEquals(PhraseLocation(2, 3), result[2])
    }

    @Test
    fun `Returns locations of the punctuation next between tokens`() {
        val testCode = """
            ,,,class,
        """.trimIndent()

        val result = PunctuationLocator.locate(testCode)

        assertEquals(4, result.size)
        assertEquals(PhraseLocation(0, 1), result[0])
        assertEquals(PhraseLocation(1, 2), result[1])
        assertEquals(PhraseLocation(2, 3), result[2])
        assertEquals(PhraseLocation(8, 9), result[3])
    }

    @Test
    fun `Returns locations of the punctuation next to strings`() {
        val testCode = """
           "a";
           "b";
        """.trimIndent()

        val result = PunctuationLocator.locate(testCode)

        assertEquals(2, result.size)
        assertEquals(PhraseLocation(3, 4), result[0])
        assertEquals(PhraseLocation(8, 9), result[1])
    }

    @Test
    fun `Not returns locations of the non punctuation characters`() {
        val testCode = """
           /** a */
           "b";
        """.trimIndent()

        val result = PunctuationLocator.locate(testCode)

        assertEquals(1, result.size)
        assertEquals(PhraseLocation(12, 13), result[0])
    }
}