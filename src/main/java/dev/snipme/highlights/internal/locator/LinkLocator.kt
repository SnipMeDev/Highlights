package dev.snipme.highlights.internal.locator

import dev.snipme.highlights.internal.SyntaxTokens.TOKEN_DELIMITERS
import dev.snipme.highlights.internal.lengthToEOF
import dev.snipme.highlights.model.PhraseLocation

@Deprecated("Not ready to use in production code")
internal object LinkLocator {
    fun locate(code: String): List<PhraseLocation> =
        code.split(*TOKEN_DELIMITERS.toTypedArray())
            .filter { isUrl(it) }
            .map {
                val start = code.indexOf(it)
                val end = start + code.lengthToEOF(start)
                PhraseLocation(start, end)
            }

    private fun isUrl(phrase: String): Boolean =
        phrase.startsWith("www") || phrase.startsWith("http://") || phrase.startsWith("https://")
}