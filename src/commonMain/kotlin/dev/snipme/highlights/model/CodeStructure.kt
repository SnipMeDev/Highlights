package dev.snipme.highlights.model

import kotlinx.serialization.Serializable

@Serializable
data class PhraseLocation(val start: Int, val end: Int)

@Serializable
data class CodeStructure(
    val marks: Set<PhraseLocation>,
    val punctuations: Set<PhraseLocation>,
    val keywords: Set<PhraseLocation>,
    val strings: Set<PhraseLocation>,
    val literals: Set<PhraseLocation>,
    val comments: Set<PhraseLocation>,
    val multilineComments: Set<PhraseLocation>,
    val annotations: Set<PhraseLocation>,
    val incremental: Boolean,
) {
    fun move(position: Int) =
        CodeStructure(
            marks = marks.map {
                it.copy(start = it.start + position, end = it.end + position)
            }.toSet(),
            punctuations = punctuations.map {
                it.copy(
                    start = it.start + position,
                    end = it.end + position
                )
            }.toSet(),
            keywords = keywords.map {
                it.copy(
                    start = it.start + position,
                    end = it.end + position
                )
            }.toSet(),
            strings = strings.map {
                it.copy(start = it.start + position, end = it.end + position)
            }.toSet(),
            literals = literals.map {
                it.copy(
                    start = it.start + position,
                    end = it.end + position
                )
            }.toSet(),
            comments = comments.map {
                it.copy(
                    start = it.start + position,
                    end = it.end + position
                )
            }.toSet(),
            multilineComments = multilineComments.map {
                it.copy(
                    start = it.start + position,
                    end = it.end + position
                )
            }.toSet(),
            annotations = annotations.map {
                it.copy(
                    start = it.start + position,
                    end = it.end + position
                )
            }.toSet(),
            incremental = true,
        )

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
            incremental = true,
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
            incremental = true,
        )

    fun printStructure(code: String) {
        print("marks = ${marks.join(code)}")
        print("punctuations = ${punctuations.join(code)}")
        print("keywords = ${keywords.join(code)}")
        print("strings = ${strings.join(code)}")
        print("literals = ${literals.join(code)}")
        print("comments = ${comments.join(code)}")
        print("multilineComments = ${multilineComments.join(code)}")
        print("annotations = ${annotations.join(code)}")
    }

    private fun Set<PhraseLocation>.join(code: String) =
        this.joinToString(separator = " ") { code.substring(it.start, it.end) } + "\n"
}
