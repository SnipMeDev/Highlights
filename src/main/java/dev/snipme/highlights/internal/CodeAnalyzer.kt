package dev.snipme.highlights.internal

import dev.snipme.highlights.internal.locator.AnnotationLocator
import dev.snipme.highlights.model.CodeStructure
import dev.snipme.highlights.model.SyntaxLanguage
import dev.snipme.highlights.model.SyntaxLanguage.*
import dev.snipme.highlights.internal.SyntaxTokens.ALL_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.ALL_MIXED_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.COFFEE_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.CPP_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.CSHARP_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.C_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.JAVA_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.JSCRIPT_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.KOTLIN_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.PERL_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.PYTHON_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.RUBY_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.RUST_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.SH_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.SWIFT_KEYWORDS
import dev.snipme.highlights.internal.locator.CommentLocator
import dev.snipme.highlights.internal.locator.KeywordLocator
import dev.snipme.highlights.internal.locator.NumericLiteralLocator
import dev.snipme.highlights.internal.locator.MarkLocator
import dev.snipme.highlights.internal.locator.MultilineCommentLocator
import dev.snipme.highlights.internal.locator.PunctuationLocator
import dev.snipme.highlights.internal.locator.StringLocator

data class CodeSnapshot(
    val code: String,
    val structure: CodeStructure,
    val language: SyntaxLanguage,
)

internal object CodeAnalyzer {
    var snapshot: CodeSnapshot? = null
        private set

    fun analyze(code: String, language: SyntaxLanguage = DEFAULT): CodeStructure =
        when {
            snapshot == null -> analyzeFull(code, language)
            language != snapshot!!.language -> analyzeFull(code, language)
            code != snapshot!!.code -> analyzePartial(snapshot!!, code)
            else -> snapshot!!.structure
        }

    fun clearSnapshot() {
        snapshot = null
    }

    private fun analyzeFull(code: String, language: SyntaxLanguage): CodeStructure {
        val structure = analyzeForLanguage(code, language)
        snapshot = CodeSnapshot(code, structure, language)
        return structure
    }

    private fun analyzePartial(codeSnapshot: CodeSnapshot, code: String): CodeStructure {
        val difference = CodeComparator.difference(codeSnapshot.code, code)
        val structure = when (difference) {
            is CodeDifference.Increase -> {
                println(CodeAnalyzer.snapshot?.code)
                println("DIFF")
                println(difference.change)
                println("DIFF")
                val newStructure = analyzeForLanguage(difference.change, codeSnapshot.language)
                newStructure.printPhrases(difference.change)
                codeSnapshot.structure + newStructure.move(codeSnapshot.code.length + 1)
            }

            is CodeDifference.Decrease -> {
                val newStructure = analyzeForLanguage(difference.change, codeSnapshot.language)
                codeSnapshot.structure - newStructure
            }

            CodeDifference.None -> return codeSnapshot.structure
        }

        snapshot = CodeSnapshot(code, structure, codeSnapshot.language)

        return structure
    }

    private fun analyzeForLanguage(code: String, language: SyntaxLanguage) =
        when (language) {
            DEFAULT -> analyzeCodeWithKeywords(code, ALL_KEYWORDS)
            MIXED -> analyzeCodeWithKeywords(code, ALL_MIXED_KEYWORDS)
            C -> analyzeCodeWithKeywords(code, C_KEYWORDS)
            CPP -> analyzeCodeWithKeywords(code, CPP_KEYWORDS)
            JAVA -> analyzeCodeWithKeywords(code, JAVA_KEYWORDS)
            KOTLIN -> analyzeCodeWithKeywords(code, KOTLIN_KEYWORDS)
            RUST -> analyzeCodeWithKeywords(code, RUST_KEYWORDS)
            CSHARP -> analyzeCodeWithKeywords(code, CSHARP_KEYWORDS)
            COFFEESCRIPT -> analyzeCodeWithKeywords(code, COFFEE_KEYWORDS)
            JAVASCRIPT -> analyzeCodeWithKeywords(code, JSCRIPT_KEYWORDS)
            PERL -> analyzeCodeWithKeywords(code, PERL_KEYWORDS)
            PYTHON -> analyzeCodeWithKeywords(code, PYTHON_KEYWORDS)
            RUBY -> analyzeCodeWithKeywords(code, RUBY_KEYWORDS)
            SHELL -> analyzeCodeWithKeywords(code, SH_KEYWORDS)
            SWIFT -> analyzeCodeWithKeywords(code, SWIFT_KEYWORDS)
        }

    private fun analyzeCodeWithKeywords(code: String, keywords: List<String>): CodeStructure =
        CodeStructure(
            marks = MarkLocator.locate(code),
            punctuations = PunctuationLocator.locate(code),
            keywords = KeywordLocator.locate(code, keywords),
            strings = StringLocator.locate(code),
            literals = NumericLiteralLocator.locate(code),
            comments = CommentLocator.locate(code),
            multilineComments = MultilineCommentLocator.locate(code),
            annotations = AnnotationLocator.locate(code),
            incremental = false,
        )
}