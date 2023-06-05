package dev.snipme.highlights.internal.locator

import dev.snipme.highlights.model.PhraseLocation
import dev.snipme.highlights.internal.SyntaxTokens.MARK_CHARACTERS
import dev.snipme.highlights.internal.indicesOf
import java.util.regex.Pattern.LITERAL

internal object MarkLocator {
    fun locate(code: String): List<PhraseLocation> {
        val locations = mutableListOf<PhraseLocation>()
        code.asSequence()
            .toSet()
            .filter { it.toString() in MARK_CHARACTERS }
            .forEach {
                code.indicesOf(
                    it.toString(),
                    setOf(RegexOption.IGNORE_CASE, RegexOption.LITERAL),
                ).forEach { index ->
                    locations.add(PhraseLocation(index, index + 1))
                }
            }

        return locations
    }
}