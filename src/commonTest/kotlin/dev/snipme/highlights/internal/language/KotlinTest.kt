package dev.snipme.highlights.internal.language

import dev.snipme.highlights.Highlights
import dev.snipme.highlights.model.SyntaxLanguage
import kotlin.test.Test
import kotlin.test.assertEquals

class KotlinTest {

    @Test
    fun test() {
        // TODO Return only valid first "val" keyword
        val code = """
            val intent = 0
            copy(input = input(intent.value)) // highlights "value"
            
        """.trimIndent()

        val result = Highlights.Builder(
            language = SyntaxLanguage.KOTLIN,
            code = code
        ).build().getCodeStructure()

        result.printPhrases(code)

        assertEquals(1, result.keywords.size)
    }
}