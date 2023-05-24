package dev.snipme.highlights.internal.locator

import dev.snipme.highlights.model.PhraseLocation
import kotlin.test.Test
import kotlin.test.assertEquals


internal class CommentLocatorTest {

    @Test
    fun `Returns location of full line of comment`() {
        val testCode = """
            // This is code
            class
        """.trimIndent()

        val result = CommentLocator.locate(testCode)

        assertEquals(1, result.size)
        assertEquals(PhraseLocation(0, 15), result.first())
    }

    @Test
    fun `Returns location of inline comment`() {
        val testCode = """
            class // This is code
        """.trimIndent()

        val result = CommentLocator.locate(testCode)

        assertEquals(1, result.size)
        assertEquals(PhraseLocation(6, 21), result.first())
    }
}