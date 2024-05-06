package dev.snipme.highlights.internal.locator

import dev.snipme.highlights.internal.get
import dev.snipme.highlights.model.PhraseLocation
import kotlin.test.Test
import kotlin.test.assertEquals

internal class KeywordLocatorTest {

    @Test
    fun `Returns empty list for no keywords`() {
        val testCode = "class NewClass"
        val keywords = setOf<String>()
        val expectedResult = emptySet<PhraseLocation>()

        val result = KeywordLocator.locate(testCode, keywords)

        assertEquals(expectedResult, result)
    }

    @Test
    fun `Returns location of first found keyword`() {
        val testCode = "class NewClass"
        val keywords = setOf("static", "new", "class")

        val result = KeywordLocator.locate(testCode, keywords)

        assertEquals(1, result.size)
        assertEquals(PhraseLocation(0, 5), result.first())
    }

    @Test
    fun `Returns location of all found keyword in line`() {
        val testCode = "class NewClass extends { }"
        val keywords = setOf("static", "new", "class", "extends")

        val result = KeywordLocator.locate(testCode, keywords)

        assertEquals(2, result.size)
        assertEquals(PhraseLocation(0, 5), result[0])
        assertEquals(PhraseLocation(15, 22), result[1])
    }

    @Test
    fun `Returns location of all keyword next to each other`() {
        val testCode = "this.class.abcd ) new"
        val keywords = setOf("this", "new", "class")

        val result = KeywordLocator.locate(testCode, keywords)

        assertEquals(3, result.size)
        assertEquals(PhraseLocation(0, 4), result[0])
        assertEquals(PhraseLocation(5, 10), result[1])
        assertEquals(PhraseLocation(18, 21), result[2])
    }

    @Test
    fun `Returns location of word that is proper keyword only`() {
        val testCode = "class 1class 1 class1 class& %class aclass"
        val keywords = setOf("this", "new", "class")

        val result = KeywordLocator.locate(testCode, keywords)

        assertEquals(4, result.size)
        assertEquals(PhraseLocation(0, 5), result[0])
        assertEquals(PhraseLocation(7, 12), result[1])
        assertEquals(PhraseLocation(22, 27), result[2])
        assertEquals(PhraseLocation(30, 35), result[3])
    }

    @Test
    fun `Returns location of all keywords`() {
        val testCode = """
            class Example extends Sample {
                static class Example2 {}
            }
        """.trimIndent()
        val keywords = setOf("static", "class", "extends")

        val result = KeywordLocator.locate(testCode, keywords)

        assertEquals(4, result.size)
        assertEquals(PhraseLocation(0, 5), result[0])
        assertEquals(PhraseLocation(42, 47), result[1])
        assertEquals(PhraseLocation(14, 21), result[2])
        assertEquals(PhraseLocation(35, 41), result[3])
    }

    @Test
    fun `Not returns location of keyword inside phrase`() {
        val testCode = """
            aclassa
        """.trimIndent()
        val keywords = setOf("static", "class", "extends")

        val result = KeywordLocator.locate(testCode, keywords)

        assertEquals(0, result.size)
    }

    @Test
    fun `Not returns location of keyword inside phrase from start`() {
        val testCode = """
            val intent = 0
        """.trimIndent()
        val keywords = setOf("int")

        val result = KeywordLocator.locate(testCode, keywords)

        assertEquals(0, result.size)
    }

    @Test
    fun `Not returns keywords from single comment`() {
        val testCode = """
            // This class is static and should extend another class
        """.trimIndent()
        val keywords = setOf("static", "class", "extends")

        val result = KeywordLocator.locate(testCode, keywords, setOf(PhraseLocation(0, 55)))

        assertEquals(0, result.size)
    }

    @Test
    fun `Not returns keywords from multiline comment`() {
        val testCode = """
            /*
            This class is static and should extend another class
            */
        """.trimIndent()
        val keywords = setOf("static", "class", "extends")

        val result = KeywordLocator.locate(testCode, keywords, setOf(PhraseLocation(0, 56)))

        assertEquals(0, result.size)
    }

    @Test
    fun `Not returns keywords from string`() {
        val testCode = """
            val text = "This class is static and should extend another class"
        """.trimIndent()
        val keywords = setOf("static", "class", "extends")

        val result = KeywordLocator.locate(testCode, keywords, setOf(PhraseLocation(0, 54)))

        assertEquals(0, result.size)
    }
}