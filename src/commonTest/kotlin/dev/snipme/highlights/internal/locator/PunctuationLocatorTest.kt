package dev.snipme.highlights.internal.locator

import dev.snipme.highlights.internal.get
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

    @Test
    fun `Not returns punctuation inside single-line comment`() {
        // "// comment, with: punctuation;" — the punctuation should be ignored
        val testCode = "// comment, with: punctuation;"
        // entire line is a comment range
        val ignoreRanges = setOf(IntRange(0, testCode.length))

        val result = PunctuationLocator.locate(testCode, ignoreRanges)

        assertEquals(0, result.size)
    }

    @Test
    fun `Not returns punctuation inside multiline comment`() {
        val testCode = "/* Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA. */"
        val ignoreRanges = setOf(IntRange(0, testCode.length))

        val result = PunctuationLocator.locate(testCode, ignoreRanges)

        assertEquals(0, result.size)
    }

    @Test
    fun `Not returns punctuation inside string literal`() {
        val testCode = "\"hello, world: foo;\""
        val ignoreRanges = setOf(IntRange(0, testCode.length))

        val result = PunctuationLocator.locate(testCode, ignoreRanges)

        assertEquals(0, result.size)
    }

    @Test
    fun `Returns punctuation outside comment but not inside`() {
        // code: foo(a, b); // bar, baz
        // punctuation outside comment: ',' at 5, ')' is a mark, ';' at 9
        // punctuation inside comment (after index 11) should be ignored
        val testCode = "foo(a, b); // bar, baz"
        // comment starts at index 11
        val ignoreRanges = setOf(IntRange(11, testCode.length - 1))

        val result = PunctuationLocator.locate(testCode, ignoreRanges)

        // Only ',' at index 5 and ';' at index 9 should be found
        assertEquals(2, result.size)
        assertEquals(PhraseLocation(5, 6), result[0])
        assertEquals(PhraseLocation(9, 10), result[1])
    }

    @Test
    fun `Returns punctuation outside string but not inside`() {
        // val s = "hello, world"; — comma inside string should be ignored, semicolon outside should be found
        val testCode = "val s = \"hello, world\";"
        // string range covers the quoted part: indices 8..21
        val ignoreRanges = setOf(IntRange(8, 21))

        val result = PunctuationLocator.locate(testCode, ignoreRanges)

        // Only ';' at index 22 should be found
        assertEquals(1, result.size)
        assertEquals(PhraseLocation(22, 23), result[0])
    }

    @Test
    fun `Returns semicolon placed right after ignored string range`() {
        val testCode = "\"d\";"
        // Range shape matches CodeAnalyzer conversion: PhraseLocation(0, 3) -> IntRange(0, 3)
        val ignoreRanges = setOf(IntRange(0, 3))

        val result = PunctuationLocator.locate(testCode, ignoreRanges)

        assertEquals(1, result.size)
        assertEquals(PhraseLocation(3, 4), result[0])
    }

    @Test
    fun `Returns semicolon placed right after ignored comment range`() {
        val testCode = "// hi;\n;"
        // Comment range from locator ends at index 6, semicolon is at index 7.
        val ignoreRanges = setOf(IntRange(0, 6))

        val result = PunctuationLocator.locate(testCode, ignoreRanges)

        assertEquals(1, result.size)
        assertEquals(PhraseLocation(7, 8), result[0])
    }

    @Test
    fun `Not returns punctuation placed at ignored range start`() {
        val testCode = ";\"d\";"
        val ignoreRanges = setOf(IntRange(0, 3))

        val result = PunctuationLocator.locate(testCode, ignoreRanges)

        assertEquals(1, result.size)
        assertEquals(PhraseLocation(4, 5), result[0])
    }

    @Test
    fun `Returns punctuation around multiple ignored ranges`() {
        val testCode = "\"a\";\"b\";"
        val ignoreRanges = setOf(
            IntRange(0, 3),
            IntRange(4, 7),
        )

        val result = PunctuationLocator.locate(testCode, ignoreRanges)

        assertEquals(2, result.size)
        assertEquals(PhraseLocation(3, 4), result[0])
        assertEquals(PhraseLocation(7, 8), result[1])
    }
}