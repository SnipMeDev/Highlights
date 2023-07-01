package dev.snipme.highlights

import dev.snipme.highlights.internal.CodeAnalyzer
import dev.snipme.highlights.model.BoldHighlight
import dev.snipme.highlights.model.CodeHighlight
import dev.snipme.highlights.model.CodeStructure
import dev.snipme.highlights.model.ColorHighlight
import dev.snipme.highlights.model.PhraseLocation
import dev.snipme.highlights.model.SyntaxLanguage
import dev.snipme.highlights.model.SyntaxTheme
import dev.snipme.highlights.model.SyntaxThemes

// TODO Handle logger (lambda)
class Highlights private constructor(
    private var code: String,
    private val language: SyntaxLanguage,
    private val theme: SyntaxTheme, // Default
    private var emphasisLocations: List<PhraseLocation>
) {

    companion object {
        fun default() = fromBuilder(Builder())

        fun fromBuilder(builder: Builder) = builder.build()
    }

    data class Builder(
        var code: String = "",
        var language: SyntaxLanguage = SyntaxLanguage.DEFAULT,
        var theme: SyntaxTheme = SyntaxThemes.default(),
        var emphasisLocations: List<PhraseLocation> = emptyList(),
    ) {
        fun code(code: String) = apply { this.code = code }
        fun language(language: SyntaxLanguage) = apply { this.language = language }
        fun theme(theme: SyntaxTheme) = apply { this.theme = theme }
        fun emphasis(vararg locations: PhraseLocation) =
            apply { this.emphasisLocations = locations.toList() }

        fun build() = Highlights(code, language, theme, emphasisLocations)
    }

    fun setCode(code: String) {
        this.code = code
    }

    fun setEmphasis(vararg locations: PhraseLocation) {
        this.emphasisLocations = locations.toList()
    }

    fun getCodeStructure(): CodeStructure = CodeAnalyzer.analyze(code, language)

    fun getHighlights(): List<CodeHighlight> {
        val highlights = mutableListOf<CodeHighlight>()
        val structure = getCodeStructure()
        with(structure) {
            marks.forEach { highlights.add(ColorHighlight(it, theme.mark)) }
            punctuations.forEach { highlights.add(ColorHighlight(it, theme.punctuation)) }
            keywords.forEach { highlights.add(ColorHighlight(it, theme.keyword)) }
            strings.forEach { highlights.add(ColorHighlight(it, theme.string)) }
            literals.forEach { highlights.add(ColorHighlight(it, theme.literal)) }
            annotations.forEach { highlights.add(ColorHighlight(it, theme.metadata)) }
            comments.forEach { highlights.add(ColorHighlight(it, theme.comment)) }
            multilineComments.forEach { highlights.add(ColorHighlight(it, theme.multilineComment)) }
        }

        emphasisLocations.forEach { highlights.add(BoldHighlight(it)) }

        return highlights
    }

    fun getBuilder() = Builder(code, language, theme, emphasisLocations)

    fun getCode() = code

    fun getLanguage() = language

    fun getTheme() = theme

    fun getEmphasis() = emphasisLocations
}
