package dev.snipme.highlights.internal

import dev.snipme.highlights.model.CodeHighlight
import dev.snipme.highlights.model.PhraseLocation
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun List<CodeHighlight>.toJson(): String {
    return Json.encodeToString<List<CodeHighlight>>(this)
}

fun String.phraseLocationSetFromJson(): Set<PhraseLocation> {
    return Json.decodeFromString(this)
}

inline operator fun <E> Set<E>.get(i: Int): E? {
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

    val charBefore = code[maxOf(index - 1, 0)]
    val charAfter = code[minOf(index + this.length, code.lastIndex)]

    if (index == 0) {
        return charAfter.isDigit().not() && charAfter.isLetter().not()
    }

    return charBefore.isLetter().not() &&
            charAfter.isDigit().not() && (charAfter == code.last() || charAfter.isLetter().not())
}

fun Set<PhraseLocation>.toRangeSet(): Set<IntRange> =
    this.map { IntRange(it.start, it.end) }.toSet()

operator fun IntRange.contains(range: IntRange): Boolean {
    return range.first >= this.first && range.last <= this.last
}