package dev.snipme.highlights.internal.locator

import dev.snipme.highlights.internal.SyntaxTokens.TOKEN_DELIMITERS
import dev.snipme.highlights.internal.indicesOf
import dev.snipme.highlights.model.PhraseLocation

private val NUMBER_START_CHARACTERS = listOf('-', '.')
private val HEX_NUMBER_CHARACTERS = listOf('a', 'b', 'c', 'd', 'e', 'f')
private val NUMBER_SPECIAL_CHARACTERS = listOf('_')
public val NUMBER_TYPE_CHARACTERS = listOf('e', 'u', 'f', 'l')

internal object NumericLiteralLocator {

    fun locate(code: String): Set<PhraseLocation> {
        return findDigitIndices(code)
    }

    private fun findDigitIndices(code: String): Set<PhraseLocation> {
        val foundPhrases = mutableSetOf<String>()
        val locations = mutableSetOf<PhraseLocation>()

        val delimiters = TOKEN_DELIMITERS.filterNot { it == "." }.toTypedArray()

        code.split(*delimiters) // Separate words
            .asSequence() // Manipulate on given word separately
            .filterNot { foundPhrases.contains(it) }
            .filter { it.isNotBlank() } // Filter spaces and others
            .filter {
                it.first().isDigit() || (NUMBER_START_CHARACTERS.contains(it.first())
                        && it.getOrNull(1)?.isDigit() == true)
            } // Find start of literals
            .forEach { number ->
                // For given literal find all occurrences
                val indices = code.indicesOf(number)
                for (startIndex in indices) {
                    // TODO Correct this and publish
                    if (code.isFullNumber(number, startIndex).not()) return@forEach
                    // Omit in the middle of text, probably variable name (this100)
                    if (code.isNumberFirstIndex(startIndex).not()) return@forEach
                    // Add matching occurrence to the output locations
                    val length = calculateNumberLength(number.lowercase())
                    locations.add(PhraseLocation(startIndex, startIndex + length))
                }

                foundPhrases.add(number)
            }

        return locations.toSet()
    }

    // Returns if given index is the beginning of word (there is no letter before)
    private fun String.isNumberFirstIndex(index: Int): Boolean {
        if (index < 0) return false
        if (index == 0) return true
        val positionBefore = maxOf(index - 1, 0)
        val charBefore = getOrNull(positionBefore) ?: return false

        return TOKEN_DELIMITERS.contains(charBefore.toString())
    }

    private fun String.isFullNumber(number: String, startIndex: Int): Boolean {
        val numberEndingIndex = startIndex + number.length
        if (numberEndingIndex >= lastIndex) return true
        val numberEnding = getOrNull(numberEndingIndex) ?: return false

        return TOKEN_DELIMITERS.contains(numberEnding.toString())
    }

    private fun calculateNumberLength(number: String): Int {
        if (number.startsWith("0x")) {
            return getLengthOfSubstringFor(number) {
                it.isDigit() || HEX_NUMBER_CHARACTERS.contains(it)
            }
        }

        if (number.contains("0b")) {
            return getLengthOfSubstringFor(number) {
                it == '0' || it == '1'
            }
        }

        var length = 0
        var foundE = false
        var foundSignAfterE = false
        var foundDot = false
        var suffixCount = 0
        val maxSuffixes = 1

        for (i in number.indices) {
            val char = number[i]
            when {
                char.isDigit() -> {
                    length++
                }
                ((char == '-' && i == 0) || char == '_') -> {
                    length++
                }
                char == '.' && !foundDot -> {
                    foundDot = true
                    length++
                }
                (char.lowercaseChar() == 'e' && !foundE) -> {
                    foundE = true
                    length++
                }
                ((char == '+' || char == '-') && foundE && !foundSignAfterE) -> {
                    foundSignAfterE = true
                    length++
                }
                NUMBER_TYPE_CHARACTERS.contains(char) -> {
                    if (suffixCount < maxSuffixes) {
                        length++
                        suffixCount++
                    } else {
                        break
                    }
                }
                else -> break
            }
        }

        return length
    }

    private fun getLengthOfSubstringFor(number: String, condition: (Char) -> Boolean): Int {
        var hexSequenceLength = 2
        run loop@{
            number.substring(startIndex = hexSequenceLength).forEach {
                if (condition(it)) {
                    hexSequenceLength++
                } else {
                    return@loop
                }
            }
        }

        return hexSequenceLength
    }
}