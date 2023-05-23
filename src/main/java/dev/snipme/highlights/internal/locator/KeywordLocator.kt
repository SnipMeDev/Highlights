package dev.snipme.highlights.internal.locator

import dev.snipme.highlights.model.PhraseLocation
import dev.snipme.highlights.internal.SyntaxTokens.TOKEN_DELIMITERS
import dev.snipme.highlights.internal.indicesOf

private const val START_INDEX = 0

internal object KeywordLocator {

    fun locate(code: String, keywords: List<String>): List<PhraseLocation> {
        val locations = mutableListOf<PhraseLocation>()
        val foundKeywords = findKeywords(code, keywords)
        foundKeywords.forEach { keyword ->
            val indices = code
                .indicesOf(keyword)
                .filter { isIndexOnlyKeyword(code, it) }

            indices.forEach { index ->
                locations.add(PhraseLocation(index, index + keyword.length))
            }
        }

        return locations.toList()
    }

    private fun findKeywords(code: String, keywords: List<String>): Set<String> =
        TOKEN_DELIMITERS.toTypedArray().let { delimiters ->
            code.split(*delimiters, ignoreCase = true) // Split into words
                .asSequence() // Reduce amount of operations
                .filter { it.isNotBlank() } // Remove empty
                .map { it.trim() } // Remove whitespaces from phrase
                .map { it.lowercase() } // Standardize
                .filter { it in keywords } // Get supported
                .toSet() // Filter duplicates
        }

    // Sometimes keyword can be found in the middle of word.
    // This returns information if index points only to the keyword
    private fun isIndexOnlyKeyword(code: String, index: Int): Boolean {
        if (index == START_INDEX) return true
        if (index == code.lastIndex) return true

        val charBefore = code[index - 1].toString()
        return charBefore in TOKEN_DELIMITERS || charBefore.first().isLetter().not()
    }
}