package dev.snipme.highlights.internal

import dev.snipme.highlights.model.CodeStructure
import dev.snipme.highlights.model.PhraseLocation
import dev.snipme.highlights.model.SyntaxLanguage
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

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
            123.00f
        """.trimIndent()

        CodeAnalyzer.clearSnapshot()
        val result = CodeAnalyzer.analyze(testCode)

        assertEquals(
            listOf(
                PhraseLocation(30, 31),
                PhraseLocation(31, 32)
            ),
            result.marks
        )

        assertEquals(
            listOf(
                PhraseLocation(36, 37),
                PhraseLocation(41, 42),
                PhraseLocation(42, 43),
                PhraseLocation(43, 44),
                PhraseLocation(48, 49),
            ),
            result.punctuations
        )

        assertEquals(
            listOf(
                PhraseLocation(14, 19),
                PhraseLocation(22, 29)
            ),
            result.keywords
        )

        assertEquals(
            listOf(
                PhraseLocation(33, 36),
            ),
            result.strings
        )

        assertEquals(
            listOf(
                PhraseLocation(45, 52),
            ),
            result.literals
        )

        assertEquals(
            listOf(
                PhraseLocation(9, 13),
            ),
            result.comments
        )

        assertEquals(
            listOf(
                PhraseLocation(0, 8),
            ),
            result.multilineComments
        )

        assertEquals(
            listOf(
                PhraseLocation(38, 40),
            ),
            result.annotations
        )

        assertEquals(false, result.incremental)
    }

    @Test
    fun `Returns incremental structure of code analyzed second time`() {
        val testCode = """
            /** a */
            // b
            class C extends {}
            "d";
            @E
            ...
            123.00f
        """.trimIndent()

        CodeAnalyzer.clearSnapshot()
        val firstExecutionTime = measureTimeMillis {
            val result = CodeAnalyzer.analyze(testCode, SyntaxLanguage.KOTLIN)
            assertEquals(false, result.incremental)
        }

        val secondTestCode = """
            /** a */
            // b
            class C extends {}
            "d";
            @E
            ...
            123.00f
            
            field.forEach { }
        """.trimIndent()

        val result: CodeStructure
        val secondExecutionTime = measureTimeMillis {
            result = CodeAnalyzer.analyze(secondTestCode, SyntaxLanguage.KOTLIN)
            assertNotNull(CodeAnalyzer.snapshot)
            assertEquals(true, result.incremental)
        }

        // Performance impact test
        assertTrue(secondExecutionTime < firstExecutionTime)

        assertEquals(
            listOf(
                PhraseLocation(30, 31),
                PhraseLocation(31, 32),
                PhraseLocation(68, 69),
                PhraseLocation(70, 71)
            ),
            result.marks
        )

        assertEquals(
            listOf(
                PhraseLocation(36, 37),
                PhraseLocation(41, 42),
                PhraseLocation(42, 43),
                PhraseLocation(43, 44),
                PhraseLocation(48, 49),
                PhraseLocation(59, 60),
            ),
            result.punctuations
        )

        assertEquals(
            listOf(
                PhraseLocation(14, 19),
                PhraseLocation(22, 29),
                PhraseLocation(54, 59)
            ),
            result.keywords
        )

        assertEquals(
            listOf(
                PhraseLocation(33, 36),
            ),
            result.strings
        )

        assertEquals(
            listOf(
                PhraseLocation(45, 52),
            ),
            result.literals
        )

        assertEquals(
            listOf(
                PhraseLocation(9, 13),
            ),
            result.comments
        )

        assertEquals(
            listOf(
                PhraseLocation(0, 8),
            ),
            result.multilineComments
        )

        assertEquals(
            listOf(
                PhraseLocation(38, 40),
            ),
            result.annotations
        )
    }

    @Test
    fun `Returns incremental structure of decreased code analyzed second time`() {
        val testCode = """
            /** a */
            // b
            class C extends {}
            "d";
            @E
            ...
            123.00f
        """.trimIndent()

        CodeAnalyzer.clearSnapshot()
        val firstExecutionTime = measureTimeMillis {
            val result = CodeAnalyzer.analyze(testCode, SyntaxLanguage.KOTLIN)
            assertEquals(false, result.incremental)
        }

        val secondTestCode = """
            /** a */
            // b
            class 
        """.trimIndent()

        val result: CodeStructure
        val secondExecutionTime = measureTimeMillis {
            result = CodeAnalyzer.analyze(secondTestCode, SyntaxLanguage.KOTLIN)
            assertNotNull(CodeAnalyzer.snapshot)
            assertEquals(true, result.incremental)
        }

        // Performance impact test
        assertTrue(secondExecutionTime < firstExecutionTime)

        assertEquals(
            emptyList(),
            result.marks
        )

        assertEquals(
            emptyList(),
            result.punctuations
        )

        assertEquals(
            listOf(
                PhraseLocation(14, 19),
            ),
            result.keywords
        )

        assertEquals(
            emptyList(),
            result.strings
        )

        assertEquals(
            emptyList(),
            result.literals
        )

        assertEquals(
            listOf(
                PhraseLocation(9, 13),
            ),
            result.comments
        )

        assertEquals(
            listOf(
                PhraseLocation(0, 8),
            ),
            result.multilineComments
        )

        assertEquals(
            emptyList(),
            result.annotations
        )
    }
}