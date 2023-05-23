package dev.snipme.highlights.internal

fun String.indicesOf(phrase: String, ignoreCase: Boolean = true): List<Int> {
    val pattern = if (ignoreCase) {
        Regex(phrase, RegexOption.IGNORE_CASE)
    } else {
        Regex(phrase)
    }

    return pattern
        .findAll(this)
        .map { it.range.first }
        .toList()
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