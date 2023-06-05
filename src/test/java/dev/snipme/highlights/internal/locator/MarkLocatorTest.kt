package dev.snipme.highlights.internal.locator

import dev.snipme.highlights.model.PhraseLocation
import org.junit.Test
import kotlin.test.assertEquals

internal class MarkLocatorTest {
    @Test
    fun `Returns location of mark characters`() {
        val testCode = """
            ( = { + - | ] & >
        """.trimIndent()

        val result = MarkLocator.locate(testCode)

        assertEquals(9, result.size)
        assertEquals(PhraseLocation(0, 1), result[0])
        assertEquals(PhraseLocation(2, 3), result[1])
        assertEquals(PhraseLocation(4, 5), result[2])
        assertEquals(PhraseLocation(6, 7), result[3])
        assertEquals(PhraseLocation(8, 9), result[4])
        assertEquals(PhraseLocation(10, 11), result[5])
        assertEquals(PhraseLocation(12, 13), result[6])
        assertEquals(PhraseLocation(14, 15), result[7])
        assertEquals(PhraseLocation(16, 17), result[8])
    }

    @Test
    fun `Returns multiple locations of the same mark`() {
        val testCode = """
            , ); ),
        """.trimIndent()

        val result = MarkLocator.locate(testCode)

        assertEquals(2, result.size)
        assertEquals(PhraseLocation(2, 3), result[0])
        assertEquals(PhraseLocation(5, 6), result[1])
    }

    @Test
    fun `Returns locations of the mark next to each other`() {
        val testCode = """
            })&
        """.trimIndent()

        val result = MarkLocator.locate(testCode)

        assertEquals(3, result.size)
        assertEquals(PhraseLocation(0, 1), result[0])
        assertEquals(PhraseLocation(1, 2), result[1])
        assertEquals(PhraseLocation(2, 3), result[2])
    }

    @Test
    fun `Returns locations of the mark next between tokens`() {
        val testCode = """
            {(class)},
        """.trimIndent()

        val result = MarkLocator.locate(testCode)

        assertEquals(4, result.size)
        assertEquals(PhraseLocation(0, 1), result[0])
        assertEquals(PhraseLocation(1, 2), result[1])
        assertEquals(PhraseLocation(7, 8), result[2])
        assertEquals(PhraseLocation(8, 9), result[3])
    }
}