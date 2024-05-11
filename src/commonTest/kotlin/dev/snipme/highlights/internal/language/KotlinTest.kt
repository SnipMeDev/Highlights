package dev.snipme.highlights.internal.language

import dev.snipme.highlights.Highlights
import dev.snipme.highlights.model.SyntaxLanguage
import kotlin.test.Test
import kotlin.test.assertEquals

class KotlinTest {

    @Test
    fun test() {
        val code = """
            val intent = 0
            copy(input = input(intent.value)) // highlights "value"
            
            // calling configure() is equivalent to:
            
            val new = 1
            
            val initialPages = 0
            
        """.trimIndent()

        val result = Highlights.Builder(
            language = SyntaxLanguage.KOTLIN,
            code = code
        ).build().getCodeStructure()

        assertEquals(4, result.keywords.size)
    }
}