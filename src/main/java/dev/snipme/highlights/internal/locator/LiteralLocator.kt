package dev.snipme.highlights.internal.locator

import dev.snipme.highlights.model.PhraseLocation
import dev.snipme.highlights.internal.SyntaxTokens.TOKEN_DELIMITERS
import dev.snipme.highlights.internal.indicesOf

internal object LiteralLocator {

    fun locate(code: String): List<PhraseLocation> {
        return findDigitIndices(code)
    }

    private fun findDigitIndices(code: String): List<PhraseLocation> {
        val locations = mutableListOf<PhraseLocation>()

        val delimiters = TOKEN_DELIMITERS.filterNot { it == "." }.toTypedArray()

        code.split(*delimiters) // Separate words
            .asSequence() // Manipulate on given word separately
            .filter { it.isNotEmpty() } // Filter spaces and others
            .filter { it.first().isDigit() || it.first() == '-' } // Find start of literals
            .forEach { number ->
                // For given literal find all occurrences
                code.indicesOf(number).forEach { startIndex ->
                    // Omit in the middle of text, probably variable name (this100)
                    if (code.isIndexBegin(startIndex).not()) return@forEach
                    // Add matching occurrence to the output locations
                    locations.add(PhraseLocation(startIndex, startIndex + number.length))
                }
            }

        return locations
    }

    // Returns if given index is the beginning of word (there is no letter before)
    private fun String.isIndexBegin(index: Int): Boolean {
        if (index < 0) return false
        if (index == 0) return true
        return getOrNull(index - 1)?.isLetter() == false
    }
}