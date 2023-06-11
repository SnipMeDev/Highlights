package dev.snipme.highlights.model

data class PhraseLocation(val start: Int, val end: Int)

// TODO Migrate to set
data class CodeStructure(
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
            marks = marks + new.marks,
            punctuations = punctuations + new.punctuations,
            keywords = keywords + new.keywords,
            strings = strings + new.strings,
            literals = literals + new.literals,
            comments = comments + new.comments,
            multilineComments = multilineComments + new.multilineComments,
            annotations = annotations + new.annotations,
        )

    operator fun minus(new: CodeStructure): CodeStructure =
        CodeStructure(
            marks = marks - new.marks,
            punctuations = punctuations - new.punctuations,
            keywords = keywords - new.keywords,
            strings = strings - new.strings,
            literals = literals - new.literals,
            comments = comments - new.comments,
            multilineComments = multilineComments - new.multilineComments,
            annotations = annotations - new.annotations,
        )
}
