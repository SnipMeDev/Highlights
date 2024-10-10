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
                    
                // Visibility modifiers
                internal val internalVar: Int = 10
                private fun privateFunction() {
                    println("This is a private function")
                }
        
                // Data types
                val number: Int = 42
                val pi: Double = 3.14
                val isValid: Boolean = true 
        """.trimIndent()

        val result = Highlights.Builder(
            language = SyntaxLanguage.KOTLIN,
            code = code
        ).build().getCodeStructure()

        assertEquals(11, result.keywords.size)
    }
}