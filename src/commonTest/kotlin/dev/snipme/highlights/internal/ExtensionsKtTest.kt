package dev.snipme.highlights.internal

import kotlin.test.Test
import kotlin.test.assertEquals

internal class ExtensionsKtTest {

    class IndicesOfTest {

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

            assertEquals(setOf(0, 9, 16), result)
        }

        @Test
        fun `Returns all indices of special phrase`() {
            val text = "//a /a b /** d //a"
            val phrase = "/**"

            val result = text.indicesOf(phrase)

            assertEquals(setOf(9), result)
        }
    }

    class IsIndependentPhraseTest {

        @Test
        fun `Returns true for phrase at start`() {
            val code = "class "
            val index = 0

            val result = "class".isIndependentPhrase(code, index)

            assertEquals(true, result)
        }

        @Test
        fun `Returns false for wrong phrase at start`() {
            val code = "classa "
            val index = 1

            val result = "class".isIndependentPhrase(code, index)

            assertEquals(false, result)
        }

        @Test
        fun `Returns true for phrase at end`() {
            val code = "asas class"
            val index = 5

            val result = "class".isIndependentPhrase(code, index)

            assertEquals(true, result)
        }

        @Test
        fun `Returns false for wrong phrase at end`() {
            val code = "asas classa"
            val index = 6

            val result = "class".isIndependentPhrase(code, index)

            assertEquals(false, result)
        }

        @Test
        fun `Returns false for phrase with wrong prefix at end`() {
            val code = "asas aclass"
            val index = 6

            val result = "class".isIndependentPhrase(code, index)

            assertEquals(false, result)
        }

        @Test
        fun `Returns false for phrase at start other phrase`() {
            val code = "valuable"
            val index = 0

            val result = "val".isIndependentPhrase(code, index)

            assertEquals(false, result)
        }

        @Test
        fun `Returns false for phrase at end other phrase`() {
            val code = "inval"
            val index = 2

            val result = "val".isIndependentPhrase(code, index)

            assertEquals(false, result)
        }

        @Test
        fun `Returns false for phrase inside other phrase`() {
            val code = "invaluable"
            val index = 2

            val result = "val".isIndependentPhrase(code, index)

            assertEquals(false, result)
        }

        @Test
        fun `Returns true for phrase is whole code`() {
            val code = "class"
            val index = 0

            val result = "class".isIndependentPhrase(code, index)

            assertEquals(true, result)
        }

        @Test
        fun `Returns true for phrase is with new line`() {
            val code = "class\n"
            val index = 0

            val result = "class".isIndependentPhrase(code, index)

            assertEquals(true, result)
        }

        @Test
        fun `Returns true for phrase is with new line and space`() {
            val code = """
                  class 
            """
            val index = 3

            val result = "class".isIndependentPhrase(code, index)

            assertEquals(true, result)
        }

        @Test
        fun `Returns true for phrase that starts with number`() {
            val code = "9class"
            val index = 1

            val result = "class".isIndependentPhrase(code, index)

            assertEquals(true, result)
        }

        @Test
        fun `Returns false for phrase that ends with number`() {
            val code = "class9"
            val index = 0

            val result = "class".isIndependentPhrase(code, index)

            assertEquals(false, result)
        }

        @Test
        fun `Returns false for phrase that is between numbers`() {
            val code = "9class9"
            val index = 1

            val result = "class".isIndependentPhrase(code, index)

            assertEquals(false, result)
        }

        @Test
        fun `Returns true for phrase that starts special character`() {
            val code = ".class"
            val index = 1

            val result = "class".isIndependentPhrase(code, index)

            assertEquals(true, result)
        }

        @Test
        fun `Returns true for phrase that ends with special character`() {
            val code = "class."
            val index = 0

            val result = "class".isIndependentPhrase(code, index)

            assertEquals(true, result)
        }

        @Test
        fun `Returns true for phrase that is between special characters`() {
            val code = ".class."
            val index = 1

            val result = "class".isIndependentPhrase(code, index)

            assertEquals(true, result)
        }
    }
}