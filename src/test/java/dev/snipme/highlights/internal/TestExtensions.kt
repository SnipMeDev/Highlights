package dev.snipme.highlights.internal

import dev.snipme.highlights.model.PhraseLocation

internal fun List<PhraseLocation>.printResults(code: String) {
    this.forEach {
        println(code.substring(it.start, it.end))
    }
}