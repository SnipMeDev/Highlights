package dev.snipme.highlights.internal

fun String.indicesOf(
    phrase: String,
    options: Set<RegexOption> = setOf(RegexOption.IGNORE_CASE)
): Set<Int> {
    val indices = mutableSetOf<Int>()

    val startIndexOf = indexOf(phrase, 0)
    if (startIndexOf < 0) {
        return emptySet()
    }

    indices.add(startIndexOf)

    if (startIndexOf == (lastIndex - phrase.length)) {
        return indices
    }

    val lastIndexOf = lastIndexOf(phrase)
    if (lastIndexOf < 0) {
        return indices
    }

    indices.add(lastIndexOf)


//    do {
//        phraseIndex =
//
//        println("Phrase = $phrase, indexOf = $phraseIndex")
//
//        if (phraseIndex >= 0) {
//            indices.add(phraseIndex)
//            startIndex += phrase.length
//        } else {
//            break
//        }
//
//    } while (phraseIndex >= 0)

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

// TODO Create unit tests for this
// Sometimes keyword can be found in the middle of word.
// This returns information if index points only to the keyword
fun String.isIndependentPhrase(
    code: String,
    index: Int,
): Boolean {
    if (index == 0) return true
    if (index == code.lastIndex) return true

    val charBefore = code[maxOf(index - 1, 0)]
    val charAfter = code[minOf(index + this.length, code.lastIndex)]

    return charBefore.isLetter().not() && charAfter.isDigit().not()
}