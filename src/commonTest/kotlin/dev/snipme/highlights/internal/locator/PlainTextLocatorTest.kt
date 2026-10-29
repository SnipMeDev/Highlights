package dev.snipme.highlights.internal.locator

import dev.snipme.highlights.model.PhraseLocation
import kotlin.test.Test
import kotlin.test.assertEquals

// Comment delimiters inside string and char literals (issue #78)
internal class PlainTextLocatorTest {

    private fun String.location(phrase: String) = indexOf(phrase).let { PhraseLocation(it, it + phrase.length) }

    @Test
    fun `Line comment delimiter inside a string does not start a comment`() {
        val testCode = "auto u = \"http://x.com\";"

        val result = PlainTextLocator.locate(testCode)

        assertEquals(setOf(testCode.location("\"http://x.com\"")), result.strings)
        assertEquals(emptySet(), result.comments)
    }

    @Test
    fun `Hash inside a char literal does not start a comment`() {
        val testCode = "char c = '#';"

        val result = PlainTextLocator.locate(testCode)

        assertEquals(setOf(testCode.location("'#'")), result.strings)
        assertEquals(emptySet(), result.comments)
    }

    @Test
    fun `Block comment opener inside a string does not swallow the code after it`() {
        val testCode = "auto s = \"/*\";\nint a = 1;\n/* real */"

        val result = PlainTextLocator.locate(testCode)

        assertEquals(setOf(testCode.location("\"/*\"")), result.strings)
        assertEquals(setOf(testCode.location("/* real */")), result.multilineComments)
    }

    @Test
    fun `Python hash inside a string does not start a comment`() {
        val testCode = "print(\"# not a comment\")"

        val result = PlainTextLocator.locate(testCode)

        assertEquals(setOf(testCode.location("\"# not a comment\"")), result.strings)
        assertEquals(emptySet(), result.comments)
    }
}
