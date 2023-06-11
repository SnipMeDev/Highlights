package dev.snipme.highlights.internal

import dev.snipme.highlights.model.PhraseLocation
import kotlin.test.Test
import kotlin.test.assertEquals

internal class CodeAnalyzerTest {

    @Test
    fun `Returns structure of code analyzed first time`() {
        val testCode = """
            /** a */
            // b
            class C extends {}
            "d";
            @E
            ...
        """.trimIndent()

        val result = CodeAnalyzer.analyze(testCode)

        assertEquals(
            listOf(
                PhraseLocation(30, 31),
                PhraseLocation(31, 32)
            ),
            result.marks
        )

        // TODO Fix punctuation
        assertEquals(
            listOf(
                PhraseLocation(36, 37),
                PhraseLocation(41, 42),
                PhraseLocation(42, 43),
                PhraseLocation(43, 44),
            ),
            result.punctuations
        )
    }

}