package dev.snipme.highlights.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
sealed class CodeHighlight {
    abstract val location: PhraseLocation
}

@Serializable
data class BoldHighlight(override val location: PhraseLocation) : CodeHighlight()

@Serializable
data class ColorHighlight(
    override val location: PhraseLocation,
    val rgb: Int
) : CodeHighlight()
