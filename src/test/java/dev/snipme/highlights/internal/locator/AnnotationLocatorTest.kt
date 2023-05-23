package dev.snipme.highlights.internal.locator

import dev.snipme.highlights.model.PhraseLocation
import kotlin.test.Test
import kotlin.test.assertEquals

internal class AnnotationLocatorTest {

    @Test
    fun `Returns proper location for annotation`() {
        val testCode = "@ABCD"

        val result = AnnotationLocator.locate(testCode)

        assertEquals(1, result.size)
        assertEquals(PhraseLocation(0, 5), result.first())
    }

    @Test
    fun `Returns proper location for annotation inside the code`() {
        val testCode = """
            import com.test.example
            
            @Serialization
            class {}
        """.trimIndent()

        val result = AnnotationLocator.locate(testCode)

        assertEquals(1, result.size)
        assertEquals(PhraseLocation(25, 39), result.first())
    }

    @Test
    fun `Returns proper location for multiple annotations`() {
        val testCode = """
            import com.test.example
            
            @Serialization
            @Test
            class {}
        """.trimIndent()

        val result = AnnotationLocator.locate(testCode)

        assertEquals(2, result.size)
        assertEquals(PhraseLocation(25, 39), result[0])
        assertEquals(PhraseLocation(40, 45), result[1])
    }

    @Test
    fun `Returns proper locations for annotation in code between`() {
        val testCode = """
            import com.test.example
            
            @Serialization
            class {
            
                @override
                fun test() {}
                
                @SerialName("name")
                fun test2() {}
            }
        """.trimIndent()

        val result = AnnotationLocator.locate(testCode)

        assertEquals(3, result.size)
        assertEquals(PhraseLocation(25, 39), result[0])
        assertEquals(PhraseLocation(53, 62), result[1])
        assertEquals(PhraseLocation(90, 101), result[2])
    }

    @Test
    fun `Returns location in scope of pharse with annotation`() {
        val testCode = """
            import com.test.example
            
            @override
            fun test() {
                this@AnnotationTest
            }
                
            @override
            fun test2() {}
            }
        """.trimIndent()

        val result = AnnotationLocator.locate(testCode)

        assertEquals(3, result.size)
        assertEquals(PhraseLocation(25, 34), result[0])
        assertEquals(PhraseLocation(79, 88), result[1])
        assertEquals(PhraseLocation(56, 71), result[2])
    }
}