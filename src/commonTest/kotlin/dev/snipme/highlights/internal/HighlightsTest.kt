package dev.snipme.highlights.internal

import dev.snipme.highlights.Highlights
import kotlin.test.Test
import kotlin.test.assertTrue

class HighlightsTest {

    @Test
    fun `returns different keywords for default builder`() {
        val instance = Highlights.default()
        instance.setCode("class ")

        val highlights = instance.getHighlights()

        assertTrue(highlights.isNotEmpty())
    }

    @Test
    fun `returns different keywords for default manual builder`() {
        val instance = Highlights.Builder().build()
        instance.setCode("class ")

        val highlights = instance.getHighlights()

        assertTrue(highlights.isNotEmpty())
    }

    @Test
    fun `returns highlights after code change`() {
        val instance = Highlights.default()

        val highlights = instance.getHighlights()

        assertTrue(highlights.isEmpty())

        instance.setCode("class ")

        val newHighlights = instance.getHighlights()

        assertTrue(newHighlights.isNotEmpty())
    }

    @Test
    fun `returns no highlights after cleared code`() {
        val instance = Highlights.default()

        val highlights = instance.getHighlights()

        assertTrue(highlights.isEmpty())

        instance.setCode("class ")

        val newHighlights = instance.getHighlights()

        assertTrue(newHighlights.isNotEmpty())

        instance.setCode("")

        val emptyHighlights = instance.getHighlights()

        assertTrue(emptyHighlights.isEmpty())
    }
}