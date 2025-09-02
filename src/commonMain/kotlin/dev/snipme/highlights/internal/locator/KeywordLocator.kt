package dev.snipme.highlights.internal.locator

import dev.snipme.highlights.internal.SyntaxTokens.TOKEN_DELIMITERS
import dev.snipme.highlights.internal.indicesOf
import dev.snipme.highlights.internal.isIndependentPhrase
import dev.snipme.highlights.model.PhraseLocation

internal object KeywordLocator {

    fun locate(
        code: String,
        keywords: Set<String>,
        ignoreRanges: Set<IntRange> = emptySet(),
    ): Set<PhraseLocation> {
        val locations = mutableSetOf<PhraseLocation>()

        keywords.forEach { keyword ->
            val indices = code
                .indicesOf(keyword)
                .filterNot { index -> ignoreRanges.any { index in it } }
                .filter { keyword.isIndependentPhrase(code, it) }

            indices.forEach { index ->
                locations.add(PhraseLocation(index, index + keyword.length))
            }
        }

        return locations
    }
}