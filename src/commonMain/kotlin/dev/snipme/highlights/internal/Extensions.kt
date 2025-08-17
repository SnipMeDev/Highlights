package dev.snipme.highlights.internal

import dev.snipme.highlights.internal.locator.NUMBER_TYPE_CHARACTERS
import dev.snipme.highlights.internal.SyntaxTokens.MARK_CHARACTERS
import dev.snipme.highlights.internal.SyntaxTokens.PUNCTUATION_CHARACTERS
import dev.snipme.highlights.model.CodeHighlight
import dev.snipme.highlights.model.PhraseLocation
import kotlinx.coroutines.Job
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.coroutines.cancellation.CancellationException

fun List<CodeHighlight>.toJson(): String {
    return Json.encodeToString<List<CodeHighlight>>(this)
}

fun String.phraseLocationSetFromJson(): Set<PhraseLocation> {
    return Json.decodeFromString(this)
}

operator fun <E> Set<E>.get(i: Int): E? {
    this.forEachIndexed { index, t ->
        if (i == index) return t
    }

    return null
}

fun String.indicesOf(
    phrase: String,
): Set<Int> {
    val indices = mutableSetOf<Int>()

    // No found
    val startIndexOf = indexOf(phrase, 0)
    if (startIndexOf < 0) {
        return emptySet()
    }

    indices.add(startIndexOf)

    // The found is the only one
    if (startIndexOf == (lastIndex - phrase.length)) {
        return indices
    }

    var startingIndex = indexOf(phrase, startIndexOf + phrase.length)

    while (startingIndex > 0) {
        indices.add(startingIndex)
        startingIndex = indexOf(phrase, startingIndex + phrase.length)
    }

    return indices
}

fun Char.isNewLine(): Boolean {
    val stringChar = this.toString()
    return stringChar == "\n" || stringChar == "\r" || stringChar == "\r\n"
}

fun String.lengthToEOF(start: Int = 0): Int {
    if (all { it.isNewLine().not() }) return length - start
    var endIndex = start
    while (this.getOrNull(endIndex)?.isNewLine()?.not() == true) {
        endIndex++
    }
    return endIndex - start
}

// Sometimes keyword can be found in the middle of word.
// This returns information if index points only to the keyword
fun String.isIndependentPhrase(
    code: String,
    index: Int,
): Boolean {
    if (index == code.lastIndex) return true
    if (code.length == this.length) return true

    // Token is at start of the code
    val charAfter = code[minOf(index + this.length, code.lastIndex)]
    if (index == 0) {
        return charAfter.isDigit().not() && charAfter.isLetter().not() && charAfter != '_'
    }

    // Token is at end of the code
    val charBefore = code[maxOf(index - 1, 0)]
    if (index + this.length == code.length) {
        return (charBefore.isLetter().not() && charBefore != '_') || isAfterNumericSuffix(code, index)
    }

    // Token is in the middle of the code
    return ((charBefore.isLetter().not() || isAfterNumericSuffix(code, index)) &&
            charAfter.isDigit().not() && charAfter.isLetter().not() && charAfter != '_')
}

private fun String.isAfterNumericSuffix(code: String, keywordIndex: Int): Boolean {
    if (keywordIndex == 0) return false

    val charBefore = code[keywordIndex - 1]

    // Check if the character before is a valid numeric suffix
    val validSuffixes = NUMBER_TYPE_CHARACTERS
    if (charBefore !in validSuffixes) return false

    // Walk backwards to validate the number structure
    var i = keywordIndex - 2
    var hasDigit = false
    var hasDot = false

    while (i >= 0) {
        val char = code[i]

        when {
            char.isDigit() -> {
                hasDigit = true
                i--
            }
            char == '.' -> {
                if (hasDot) return false
                hasDot = true
                i--
            }
            char == '-' -> {
                if (i == 0) {
                    break
                } else {
                    val prevChar = code[i - 1]
                    if (prevChar.isLetterOrDigit() || prevChar == '_') {
                        return false
                    } else {
                        break
                    }
                }
            }
            char == '_' -> {
                i--
            }
            char.isLetter() -> {
                return false
            }
            else -> {
                break
            }
        }
    }

    return hasDigit
}

fun Set<PhraseLocation>.toRangeSet(): Set<IntRange> =
    this.map { IntRange(it.start, it.end) }.toSet()

operator fun IntRange.contains(range: IntRange): Boolean {
    return range.first >= this.first && range.last <= this.last
}

fun Job.onCancel(block: () -> Unit) {
    invokeOnCompletion {
        if (it is CancellationException) {
            block()
        }
    }
}