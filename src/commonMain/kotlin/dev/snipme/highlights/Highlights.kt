package dev.snipme.highlights

import dev.snipme.highlights.internal.CodeAnalyzer
import dev.snipme.highlights.internal.CodeSnapshot
import dev.snipme.highlights.model.BoldHighlight
import dev.snipme.highlights.model.CodeHighlight
import dev.snipme.highlights.model.CodeStructure
import dev.snipme.highlights.model.ColorHighlight
import dev.snipme.highlights.model.PhraseLocation
import dev.snipme.highlights.model.SyntaxLanguage
import dev.snipme.highlights.model.SyntaxTheme
import dev.snipme.highlights.model.SyntaxThemes
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface HighlightsResultListener {
    fun onStart()
    fun onCompleted(highlights: List<CodeHighlight>)
    fun onError(exception: Throwable)
}

class Highlights private constructor(
    private var code: String,
    private val language: SyntaxLanguage,
    private val theme: SyntaxTheme,
    private var emphasisLocations: List<PhraseLocation>
) {
    var snapshot: CodeSnapshot? = null
        private set

    var analysisJob: Job? = null
        private set

    companion object {
        fun default() = fromBuilder(Builder())

        fun fromBuilder(builder: Builder) = builder.build()

        fun themes(darkMode: Boolean) = SyntaxThemes.themes(darkMode)

        fun languages() = SyntaxLanguage.values().toList()
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

    fun getCodeStructure(): CodeStructure {
        val structure = CodeAnalyzer.analyze(code, language, snapshot)
        snapshot = CodeSnapshot(code, structure, language)
        return structure
    }

    fun getHighlights(): List<CodeHighlight> {
        val structure = getCodeStructure()
        return constructHighlights(structure)
    }

    fun getHighlightsAsync(listener: HighlightsResultListener) {
        analysisJob?.cancel()

        val errorHandler = CoroutineExceptionHandler { _, exception ->
            listener.onError(exception)
        }

        analysisJob = CoroutineScope(Dispatchers.Default + errorHandler).launch {
            val structure = getCodeStructure()
            val highlights = constructHighlights(structure)
            withContext(Dispatchers.Main) {
                listener.onCompleted(highlights)
            }
        }

        listener.onStart()
    }

    fun getBuilder() = Builder(code, language, theme, emphasisLocations)

    fun getCode() = code

    fun getLanguage() = language

    fun getTheme() = theme

    fun getEmphasis() = emphasisLocations

    private fun constructHighlights(structure: CodeStructure): List<CodeHighlight> =
        mutableListOf<CodeHighlight>().apply {
            structure.marks.forEach { add(ColorHighlight(it, theme.mark)) }
            structure.punctuations.forEach { add(ColorHighlight(it, theme.punctuation)) }
            structure.keywords.forEach { add(ColorHighlight(it, theme.keyword)) }
            structure.strings.forEach { add(ColorHighlight(it, theme.string)) }
            structure.literals.forEach { add(ColorHighlight(it, theme.literal)) }
            structure.annotations.forEach { add(ColorHighlight(it, theme.metadata)) }
            structure.comments.forEach { add(ColorHighlight(it, theme.comment)) }
            structure.multilineComments.forEach { add(ColorHighlight(it, theme.multilineComment)) }
            emphasisLocations.forEach { add(BoldHighlight(it)) }
        }
}
