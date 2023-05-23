package dev.snipme.highlights.internal.locator

import dev.snipme.highlights.internal.indicesOf
import dev.snipme.highlights.model.PhraseLocation

@Deprecated("Not ready to use in production code")
internal object DuplicateLocator {
    fun locate(code: String, tokens: List<PhraseLocation>) =
        tokens
            .map { code.substring(it.start, it.end) } // Map to words
            .map { Pair(it, code.indicesOf(it)) } // Find all occurrences of word
            .filter { it.second.count() > 1 } // Pass only indices of duplicates
            .map { pair -> pair.second.map { PhraseLocation(it, it + pair.first.length) } } // Create every duplicate location
            .flatMap { it.asIterable() } // Flatmap to list of PhraseLocation
}