package dev.snipme.highlights.internal

import kotlin.test.Test
import kotlin.test.assertTrue

class SyntaxTokensTest {
    @Test
    fun `Returns all keywords without whitespaces`() {
        val hasWhitespace = SyntaxTokens.ALL_KEYWORDS.filter { keyword ->
            keyword.any { it.isWhitespace() }
        }
        assertTrue(hasWhitespace.isEmpty())
    }
}