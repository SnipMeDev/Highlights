package dev.snipme.highlights.internal.locator

import dev.snipme.highlights.model.PhraseLocation
import kotlin.test.Test
import kotlin.test.assertEquals

internal class TokenLocatorTest {

    @Test
    fun `Returns location of all tokens`() {
        val testCode = """
            val pair = ('a', 12, true)
        """.trimIndent()

        val result = TokenLocator.locate(testCode)

        assertEquals(5, result.size)
        assertEquals(PhraseLocation(0, 3), result[0])
        assertEquals(PhraseLocation(4, 8), result[1])
        assertEquals(PhraseLocation(12, 15), result[2])
        assertEquals(PhraseLocation(17, 19), result[3])
        assertEquals(PhraseLocation(21, 25), result[4])
    }

    @Test
    fun `Returns location of all tokens in right order`() {
        val testCode = """
            zzz yy xx
        """.trimIndent()

        val result = TokenLocator.locate(testCode)

        assertEquals(3, result.size)
        assertEquals(PhraseLocation(0, 3), result[0])
        assertEquals(PhraseLocation(4, 6), result[1])
        assertEquals(PhraseLocation(7, 9), result[2])
    }
}