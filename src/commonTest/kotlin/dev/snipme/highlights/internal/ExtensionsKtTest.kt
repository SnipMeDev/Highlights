package dev.snipme.highlights.internal

import kotlin.test.Test
import kotlin.test.assertEquals

internal class ExtensionsKtTest {


    @Test
    fun `Returns empty result of phrase that is not present`() {
        val text = "b c d"
        val phrase = "//a"

        val result = text.indicesOf(phrase)

        assertEquals(setOf(), result)
    }

    @Test
    fun `Returns index of phrase at start`() {
        val text = "//a b c d"
        val phrase = "//a"

        val result = text.indicesOf(phrase)

        assertEquals(setOf(0), result)
    }

    @Test
    fun `Returns index of phrase at end`() {
        val text = "/a b c d //a"
        val phrase = "//a"

        val result = text.indicesOf(phrase)

        assertEquals(setOf(9), result)
    }

    @Test
    fun `Returns index of phrase at start and end`() {
        val text = "//a b c d //a"
        val phrase = "//a"

        val result = text.indicesOf(phrase)

        assertEquals(setOf(0, 10), result)
    }

    @Test
    fun `Returns all indices of phrase at end`() {
        val text = "//a /a b //ac d //a"
        val phrase = "//a"

        val result = text.indicesOf(phrase)

        assertEquals(setOf(0, 9, 17), result)
    }


}