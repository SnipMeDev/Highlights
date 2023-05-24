package dev.snipme.highlights.internal.locator

import dev.snipme.highlights.model.PhraseLocation
import org.junit.Test
import kotlin.test.assertEquals

internal class LinkLocatorTest {

    @Test
    fun `Returns location of HTTP protocol address`() {
        val testCode = "// http://"

        val result = LinkLocator.locate(testCode)

        assertEquals(1, result.size)
        assertEquals(PhraseLocation(3, 10), result.first())
    }

    @Test
    fun `Returns location of HTTPS protocol address`() {
        val testCode = "// https://"

        val result = LinkLocator.locate(testCode)

        assertEquals(1, result.size)
        assertEquals(PhraseLocation(3, 11), result.first())
    }

    @Test
    fun `Returns location of link between other tokens`() {
        val testCode = "// This is a https://www.example.com link"

        val result = LinkLocator.locate(testCode)

        assertEquals(1, result.size)
        assertEquals(PhraseLocation(13, 36), result.first())
    }

    @Test
    fun `Returns location of full URL link`() {
        val testCode = "// This is a https://www.example.com/test?a=b&c=d%3A# link"

        val result = LinkLocator.locate(testCode)

        assertEquals(1, result.size)
        assertEquals(PhraseLocation(13, 53), result.first())
    }

    @Test
    fun `Returns location of full URL in longer phrase`() {
        val testCode = """
            val description = "more you can read [here](https://www.example.com)"
            
            
        """.trimIndent()

        val result = LinkLocator.locate(testCode)

        assertEquals(1, result.size)
        assertEquals(PhraseLocation(44, 67), result.first())
    }

    @Test
    fun `Not returns location of malformed URL link`() {
        val testCode = "// This is a https:/www.example.com/test?a=b&c=d%3A# link"

        val result = LinkLocator.locate(testCode)

        assertEquals(0, result.size)
    }
}