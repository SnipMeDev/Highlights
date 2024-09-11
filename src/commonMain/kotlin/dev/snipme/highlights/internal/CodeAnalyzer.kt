package dev.snipme.highlights.internal

import dev.snipme.highlights.internal.SyntaxTokens.ALL_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.COFFEE_SCRIPT_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.CPP_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.CSHARP_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.C_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.DART_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.GO_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.JAVASCRIPT_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.JAVA_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.KOTLIN_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.PERL_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.PHP_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.PYTHON_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.RUBY_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.RUST_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.SH_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.SWIFT_KEYWORDS
import dev.snipme.highlights.internal.SyntaxTokens.TYPESCRIPT_KEYWORDS
import dev.snipme.highlights.internal.locator.AnnotationLocator
import dev.snipme.highlights.internal.locator.CommentLocator
import dev.snipme.highlights.internal.locator.KeywordLocator
import dev.snipme.highlights.internal.locator.MarkLocator
import dev.snipme.highlights.internal.locator.MultilineCommentLocator
import dev.snipme.highlights.internal.locator.NumericLiteralLocator
import dev.snipme.highlights.internal.locator.PunctuationLocator
import dev.snipme.highlights.internal.locator.StringLocator
import dev.snipme.highlights.model.CodeStructure
import dev.snipme.highlights.model.SyntaxLanguage
import dev.snipme.highlights.model.SyntaxLanguage.C
import dev.snipme.highlights.model.SyntaxLanguage.COFFEESCRIPT
import dev.snipme.highlights.model.SyntaxLanguage.CPP
import dev.snipme.highlights.model.SyntaxLanguage.CSHARP
import dev.snipme.highlights.model.SyntaxLanguage.DART
import dev.snipme.highlights.model.SyntaxLanguage.DEFAULT
import dev.snipme.highlights.model.SyntaxLanguage.GO
import dev.snipme.highlights.model.SyntaxLanguage.JAVA
import dev.snipme.highlights.model.SyntaxLanguage.JAVASCRIPT
import dev.snipme.highlights.model.SyntaxLanguage.KOTLIN
import dev.snipme.highlights.model.SyntaxLanguage.PERL
import dev.snipme.highlights.model.SyntaxLanguage.PHP
import dev.snipme.highlights.model.SyntaxLanguage.PYTHON
import dev.snipme.highlights.model.SyntaxLanguage.RUBY
import dev.snipme.highlights.model.SyntaxLanguage.RUST
import dev.snipme.highlights.model.SyntaxLanguage.SHELL
import dev.snipme.highlights.model.SyntaxLanguage.SWIFT
import dev.snipme.highlights.model.SyntaxLanguage.TYPESCRIPT

data class CodeSnapshot(
    val code: String,
    val structure: CodeStructure,
    val language: SyntaxLanguage,
)

internal object CodeAnalyzer {
    fun analyze(
        code: String,
        language: SyntaxLanguage = DEFAULT,
        snapshot: CodeSnapshot? = null,
    ): CodeStructure =
        when {
            snapshot == null -> analyzeFull(code, language)
            language != snapshot.language -> analyzeFull(code, language)
            code != snapshot.code -> analyzePartial(snapshot, code)
            else -> snapshot.structure
        }

    private fun analyzeFull(code: String, language: SyntaxLanguage): CodeStructure {
        return analyzeForLanguage(code, language)
    }

    private fun analyzePartial(codeSnapshot: CodeSnapshot, code: String): CodeStructure {
        val difference = CodeComparator.difference(codeSnapshot.code, code)
        val structure = when (difference) {
            is CodeDifference.Increase -> {
                val newStructure = analyzeForLanguage(difference.change, codeSnapshot.language)
                codeSnapshot.structure + newStructure.move(codeSnapshot.code.length + 1)
            }

            is CodeDifference.Decrease -> {
                val newStructure = analyzeForLanguage(difference.change, codeSnapshot.language)
                val lengthDifference = codeSnapshot.code.length - difference.change.length
                codeSnapshot.structure - newStructure.move(lengthDifference)
            }

            is CodeDifference.Swap -> {
                val newStructure = analyzeForLanguage(difference.new, codeSnapshot.language)
                codeSnapshot.structure.replace(difference.old, newStructure)
            }

            CodeDifference.None -> return codeSnapshot.structure
        }

        return structure
    }

    private fun analyzeForLanguage(code: String, language: SyntaxLanguage) =
        when (language) {
            DEFAULT -> analyzeCodeWithKeywords(code, ALL_KEYWORDS)
            C -> analyzeCodeWithKeywords(code, C_KEYWORDS)
            CPP -> analyzeCodeWithKeywords(code, CPP_KEYWORDS)
            JAVA -> analyzeCodeWithKeywords(code, JAVA_KEYWORDS)
            KOTLIN -> analyzeCodeWithKeywords(code, KOTLIN_KEYWORDS)
            RUST -> analyzeCodeWithKeywords(code, RUST_KEYWORDS)
            CSHARP -> analyzeCodeWithKeywords(code, CSHARP_KEYWORDS)
            COFFEESCRIPT -> analyzeCodeWithKeywords(code, COFFEE_SCRIPT_KEYWORDS)
            JAVASCRIPT -> analyzeCodeWithKeywords(code, JAVASCRIPT_KEYWORDS)
            PERL -> analyzeCodeWithKeywords(code, PERL_KEYWORDS)
            PYTHON -> analyzeCodeWithKeywords(code, PYTHON_KEYWORDS)
            RUBY -> analyzeCodeWithKeywords(code, RUBY_KEYWORDS)
            SHELL -> analyzeCodeWithKeywords(code, SH_KEYWORDS)
            SWIFT -> analyzeCodeWithKeywords(code, SWIFT_KEYWORDS)
            DART -> analyzeCodeWithKeywords(code, DART_KEYWORDS)
            TYPESCRIPT -> analyzeCodeWithKeywords(code, TYPESCRIPT_KEYWORDS)
            GO -> analyzeCodeWithKeywords(code, GO_KEYWORDS)
            PHP -> analyzeCodeWithKeywords(code, PHP_KEYWORDS)
        }

    private fun analyzeCodeWithKeywords(code: String, keywords: Set<String>): CodeStructure {
        val comments = CommentLocator.locate(code)
        val multiLineComments = MultilineCommentLocator.locate(code)
        val commentRanges = (comments + multiLineComments).toRangeSet()

        val strings = StringLocator.locate(code, commentRanges)
        val plainTextRanges = (comments + multiLineComments + strings).toRangeSet()

        // TODO Apply ignored ranges to other locators
        return CodeStructure(
            marks = MarkLocator.locate(code),
            punctuations = PunctuationLocator.locate(code),
            keywords = KeywordLocator.locate(code, keywords, plainTextRanges),
            strings = strings,
            literals = NumericLiteralLocator.locate(code),
            comments = comments,
            multilineComments = multiLineComments,
            annotations = AnnotationLocator.locate(code),
            incremental = false,
        )
    }
}