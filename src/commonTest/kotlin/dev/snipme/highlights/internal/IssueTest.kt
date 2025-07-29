package dev.snipme.highlights.internal

import dev.snipme.highlights.Highlights
import dev.snipme.highlights.model.ColorHighlight
import dev.snipme.highlights.model.SyntaxLanguage
import dev.snipme.highlights.model.SyntaxThemes
import kotlin.test.Test

class IssueTest {

    @Test
    fun test() {
        val theme = SyntaxThemes.atom(true).let {
            it.copy(punctuation = it.code)
        }

        val code2 = """
        max_seq_len: int, end_action_value: int for col in list_cols: int
        """.trimIndent()

        val codeHighlights = Highlights.Builder()
            .theme(theme)
            .code(code2)
            .language(SyntaxLanguage.PYTHON)
            .build()

        // Highlight & bold special segments
        codeHighlights.getHighlights()
            .filterIsInstance<ColorHighlight>()
            .forEach {
                println(
                    "Color ${it.rgb} location = ${it.location.start} word ${
                        code2.subSequence(
                            it.location.start,
                            it.location.end
                        )
                    }"
                )
            }

    }
}