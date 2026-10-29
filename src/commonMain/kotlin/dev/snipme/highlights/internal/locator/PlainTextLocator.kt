package dev.snipme.highlights.internal.locator

import dev.snipme.highlights.internal.SyntaxTokens.COMMENT_DELIMITERS
import dev.snipme.highlights.internal.SyntaxTokens.MULTILINE_COMMENT_DELIMITERS
import dev.snipme.highlights.internal.isNewLine
import dev.snipme.highlights.internal.lengthToEOF
import dev.snipme.highlights.model.PhraseLocation

private const val TRIPLE_QUOTE = "\"\"\""
private val QUOTES = setOf('"', '\'')
private const val ESCAPE = '\\'

/**
 * Comments and strings, found in one left-to-right pass so that whichever starts first wins: a comment
 * delimiter inside a string (`"http://..."`, `'#'`, or a block-comment opener) doesn't start a comment,
 * and a quote inside a comment doesn't start a string. Locating them separately can't get both
 * right, since each depends on the other.
 */
internal object PlainTextLocator {

    data class PlainText(
        val comments: Set<PhraseLocation>,
        val multilineComments: Set<PhraseLocation>,
        val strings: Set<PhraseLocation>,
    )

    fun locate(code: String): PlainText {
        val comments = mutableSetOf<PhraseLocation>()
        val multilineComments = mutableSetOf<PhraseLocation>()
        val strings = mutableSetOf<PhraseLocation>()

        var index = 0
        while (index < code.length) {
            val blockComment = MULTILINE_COMMENT_DELIMITERS.firstOrNull { (start, _) -> code.startsWith(start, index) }
            val end = when {
                blockComment != null -> blockCommentEnd(code, index, blockComment)?.also { multilineComments += PhraseLocation(index, it) }
                COMMENT_DELIMITERS.any { code.startsWith(it, index) } -> (index + code.lengthToEOF(index)).also { comments += PhraseLocation(index, it) }
                code.startsWith(TRIPLE_QUOTE, index) -> tripleQuoteEnd(code, index)?.also { strings += PhraseLocation(index, it) }
                code[index] in QUOTES -> quoteEnd(code, index)?.also { strings += PhraseLocation(index, it) }
                else -> null
            }
            index = end ?: (index + 1)
        }

        return PlainText(comments, multilineComments, strings)
    }

    /** End of the block comment opened at [start], or null if it's never closed. */
    private fun blockCommentEnd(code: String, start: Int, delimiters: Pair<String, String>): Int? {
        val (opening, closing) = delimiters
        val closingIndex = code.indexOf(closing, start + opening.length)
        return if (closingIndex < 0) null else closingIndex + closing.length
    }

    /** End of the `"""` string opened at [start], or null if it's never closed. */
    private fun tripleQuoteEnd(code: String, start: Int): Int? {
        val closingIndex = code.indexOf(TRIPLE_QUOTE, start + TRIPLE_QUOTE.length)
        return if (closingIndex < 0) null else closingIndex + TRIPLE_QUOTE.length
    }

    /** End of the string or char literal opened at [start] on the same line (escapes skipped), or null if unclosed. */
    private fun quoteEnd(code: String, start: Int): Int? {
        val quote = code[start]
        var index = start + 1
        while (index < code.length) {
            val char = code[index]
            when {
                char == ESCAPE -> index++
                char.isNewLine() -> return null
                char == quote -> return index + 1
            }
            index++
        }
        return null
    }
}
