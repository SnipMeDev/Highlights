package dev.snipme.highlights.internal.locator

import dev.snipme.highlights.model.PhraseLocation
import dev.snipme.highlights.internal.SyntaxTokens.PUNCTUATION_CHARACTERS
import dev.snipme.highlights.internal.indicesOf

internal object PunctuationLocator {
    fun locate(code: String): List<PhraseLocation> {
        val locations = mutableListOf<PhraseLocation>()
        code.asSequence()
            .toSet()
            .filter { it.toString() in PUNCTUATION_CHARACTERS }
            .forEach {
                code.indicesOf(it.toString()).forEach { index ->
                    locations.add(PhraseLocation(index, index + 1))
                }
            }
        return locations
    }
}