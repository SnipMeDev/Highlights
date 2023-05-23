package dev.snipme.highlights.model

data class PhraseLocation(val start: Int, val end: Int)

data class CodeStructure(
    val tokens: List<PhraseLocation>,
    val marks: List<PhraseLocation>,
    val punctuations: List<PhraseLocation>,
    val keywords: List<PhraseLocation>,
    val strings: List<PhraseLocation>,
    val literals: List<PhraseLocation>,
    val comments: List<PhraseLocation>,
    val multilineComments: List<PhraseLocation>,
    val annotations: List<PhraseLocation>
)
