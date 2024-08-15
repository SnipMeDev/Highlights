package dev.snipme.highlights.model

enum class SyntaxLanguage {
    DEFAULT,
    C,
    CPP,
    DART,
    JAVA,
    KOTLIN,
    RUST,
    CSHARP,
    COFFEESCRIPT,
    JAVASCRIPT,
    PERL,
    PYTHON,
    RUBY,
    SHELL,
    SWIFT,
    TYPESCRIPT,
    GO,
    PHP;

    companion object {
        fun getNames(): List<String> = values().map {
            it.name
                .lowercase()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }

        fun getByName(name: String): SyntaxLanguage? =
            entries.find { it.name.equals(name, ignoreCase = true) }
    }
}