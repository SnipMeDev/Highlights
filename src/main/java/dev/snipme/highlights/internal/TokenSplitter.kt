package dev.snipme.highlights.internal

internal object TokenSplitter {
    fun split(code: String) {
        code.split(*SyntaxTokens.TOKEN_DELIMITERS.toTypedArray())
            .asSequence()
            .filter { it.isNotBlank() }
    }
}