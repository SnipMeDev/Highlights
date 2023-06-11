package dev.snipme.highlights.model

data class PhraseLocation(val start: Int, val end: Int)

// TODO Migrate to set
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
) {
    operator fun plus(new: CodeStructure): CodeStructure =
        CodeStructure(
            tokens = tokens + new.tokens,
            marks = tokens + new.marks,
            punctuations = tokens + new.punctuations,
            keywords = tokens + new.keywords,
            strings = tokens + new.strings,
            literals = tokens + new.literals,
            comments = tokens + new.comments,
            multilineComments = tokens + new.multilineComments,
            annotations = tokens + new.annotations,
        )

    operator fun minus(new: CodeStructure): CodeStructure =
        CodeStructure(
            tokens = tokens - new.tokens,
            marks = tokens - new.marks,
            punctuations = tokens - new.punctuations,
            keywords = tokens - new.keywords,
            strings = tokens - new.strings,
            literals = tokens - new.literals,
            comments = tokens - new.comments,
            multilineComments = tokens - new.multilineComments,
            annotations = tokens - new.annotations,
        )
}
