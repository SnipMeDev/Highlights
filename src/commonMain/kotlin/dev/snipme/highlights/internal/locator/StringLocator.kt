package dev.snipme.highlights.internal.locator

import dev.snipme.highlights.internal.SyntaxTokens.STRING_DELIMITERS
import dev.snipme.highlights.internal.contains
import dev.snipme.highlights.internal.indicesOf
import dev.snipme.highlights.model.PhraseLocation

private const val START_INDEX = 0
private const val TWO_ELEMENTS = 2
private const val QUOTE_ENDING_POSITION = 1

internal object StringLocator {

    fun locate(
        code: String,
        ignoreRanges: Set<IntRange> = emptySet(),
    ): Set<PhraseLocation> = findStrings(code, ignoreRanges)

    private fun findStrings(
        code: String,
        ignoreRanges: Set<IntRange>,
    ): Set<PhraseLocation> {
        val locations = mutableSetOf<PhraseLocation>()

        // Find index of each string delimiter like " or ' or """
        STRING_DELIMITERS.forEach {
            val textIndices = mutableListOf<Int>()
            textIndices += code.indicesOf(it)

            // For given indices find words between
            for (i in START_INDEX..textIndices.lastIndex step TWO_ELEMENTS) {
                if (textIndices.getOrNull(i + 1) == null) continue

                // Skip unwanted phrases
                val textRange = IntRange(textIndices[i], textIndices[i + 1])
                if (ignoreRanges.any { ignored -> textRange in ignored })
                    continue

                locations.add(
                    PhraseLocation(
                        textIndices[i],
                        textIndices[i + 1] + QUOTE_ENDING_POSITION
                    )
                )
            }
        }

        return locations
    }
}