package dev.snipme.highlights

import dev.snipme.highlights.model.CodeHighlight

interface HighlightsResultListener {
    fun onStart()
    fun onComplete(result: List<CodeHighlight>)
    fun onError(exception: Throwable)
    fun onCancel()
}

abstract class DefaultHighlightsResultListener : HighlightsResultListener {
    override fun onStart() {}
    override fun onComplete(result: List<CodeHighlight>) {}
    override fun onError(exception: Throwable) {}
    override fun onCancel() {}
}