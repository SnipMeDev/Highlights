package dev.snipme.highlights.internal.locator

import dev.snipme.highlights.model.PhraseLocation
import dev.snipme.highlights.internal.SyntaxTokens
import dev.snipme.highlights.internal.indicesOf

internal object TokenLocator {
    fun locate(code: String): List<PhraseLocation> {
        val locations = mutableListOf<PhraseLocation>()
        code.split(*SyntaxTokens.TOKEN_DELIMITERS.toTypedArray()) // Separate words
            .asSequence() // Manipulate on given word separately
            .filter { it.isNotEmpty() } // Filter spaces and others
            .forEach { token ->
                code.indicesOf(token).forEach { startIndex ->
                    locations.add(PhraseLocation(startIndex, startIndex + token.length))
                }
            }

        return locations
    }
}