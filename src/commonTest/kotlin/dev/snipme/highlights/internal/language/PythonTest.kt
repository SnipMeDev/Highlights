package dev.snipme.highlights.internal.language

import dev.snipme.highlights.Highlights
import dev.snipme.highlights.internal.longPythonCode
import dev.snipme.highlights.model.SyntaxLanguage
import kotlin.test.Test
import kotlin.test.assertEquals

class PythonTest {

    @Test
    fun test() {
        val result = Highlights.Builder()
            .code(longPythonCode)
            .language(SyntaxLanguage.PYTHON)
            .build()
            .getCodeStructure()

        assertEquals(6, result.keywords.size)
    }
}