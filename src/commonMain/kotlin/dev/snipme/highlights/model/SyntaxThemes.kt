package dev.snipme.highlights.model

private const val DARCULA_KEY = "darcula"
private const val MONOKAI_KEY = "monokai"
private const val NOTEPAD_KEY = "notepad"
private const val MATRIX_KEY = "matrix"
private const val PASTEL_KEY = "pastel"
private const val ATOM_ONE_KEY = "atomone"

object SyntaxThemes {

    val dark = mapOf(
        DARCULA_KEY to SyntaxTheme(
            key = DARCULA_KEY,
            code = 0xEDEDED,
            keyword = 0xCC7832,
            string = 0x6A8759,
            literal = 0x6897BB,
            comment = 0x909090,
            metadata = 0xBBB529,
            multilineComment = 0x629755,
            punctuation = 0xCC7832,
            mark = 0xEDEDED
        ),
        MONOKAI_KEY to SyntaxTheme(
            key = MONOKAI_KEY,
            code = 0xF8F8F2,
            keyword = 0xF92672,
            string = 0xE6DB74,
            literal = 0xAE81FF,
            comment = 0xFD971F,
            metadata = 0xB8F4B8,
            multilineComment = 0xFD971F,
            punctuation = 0xF8F8F2,
            mark = 0xF8F8F2
        ),
        NOTEPAD_KEY to SyntaxTheme(
            key = NOTEPAD_KEY,
            code = 0x000080,
            keyword = 0x0000FF,
            string = 0x808080,
            literal = 0xFF8000,
            comment = 0x008000,
            metadata = 0x000080,
            multilineComment = 0x008000,
            punctuation = 0xAA2C8C,
            mark = 0xAA2C8C
        ),
        MATRIX_KEY to SyntaxTheme(
            key = MATRIX_KEY,
            code = 0x008500,
            keyword = 0x008500,
            string = 0x269926,
            literal = 0x39E639,
            comment = 0x67E667,
            metadata = 0x008500,
            multilineComment = 0x67E667,
            punctuation = 0x008500,
            mark = 0x008500
        ),
        PASTEL_KEY to SyntaxTheme(
            key = PASTEL_KEY,
            code = 0xDFDEE0,
            keyword = 0x729FCF,
            string = 0x93CF55,
            literal = 0x8AE234,
            comment = 0x888A85,
            metadata = 0x5DB895,
            multilineComment = 0x888A85,
            punctuation = 0xCB956D,
            mark = 0xCB956D
        ),
        ATOM_ONE_KEY to SyntaxTheme(
            key = ATOM_ONE_KEY,
            code = 0xBBBBBB,
            keyword = 0xD55FDE,
            string = 0x89CA78,
            literal = 0xD19A66,
            comment = 0x5C6370,
            metadata = 0xE5C07B,
            multilineComment = 0x5C6370,
            punctuation = 0xEF596F,
            mark = 0x2BBAC5
        )
    )

    val light = mapOf(
        DARCULA_KEY to SyntaxTheme(
            key = DARCULA_KEY,
            code = 0x121212,
            keyword = 0xCC7832,
            string = 0x6A8759,
            literal = 0x6897BB,
            comment = 0x909090,
            metadata = 0xBBB529,
            multilineComment = 0x629755,
            punctuation = 0xCC7832,
            mark = 0x121212
        ),
        MONOKAI_KEY to SyntaxTheme(
            key = MONOKAI_KEY,
            code = 0x07070D,
            keyword = 0xF92672,
            string = 0xE6DB74,
            literal = 0xAE81FF,
            comment = 0xFD971F,
            metadata = 0xB8F4B8,
            multilineComment = 0xFD971F,
            punctuation = 0x07070D,
            mark = 0x07070D
        ),
        NOTEPAD_KEY to SyntaxTheme(
            key = NOTEPAD_KEY,
            code = 0x000080,
            keyword = 0x0000FF,
            string = 0x808080,
            literal = 0xFF8000,
            comment = 0x008000,
            metadata = 0x000080,
            multilineComment = 0x008000,
            punctuation = 0xAA2C8C,
            mark = 0xAA2C8C
        ),
        MATRIX_KEY to SyntaxTheme(
            key = MATRIX_KEY,
            code = 0x008500,
            keyword = 0x008500,
            string = 0x269926,
            literal = 0x39E639,
            comment = 0x67E667,
            metadata = 0x008500,
            multilineComment = 0x67E667,
            punctuation = 0x008500,
            mark = 0x008500
        ),
        PASTEL_KEY to SyntaxTheme(
            key = PASTEL_KEY,
            code = 0x20211F,
            keyword = 0x729FCF,
            string = 0x93CF55,
            literal = 0x8AE234,
            comment = 0x888A85,
            metadata = 0x5DB895,
            multilineComment = 0x888A85,
            punctuation = 0xCB956D,
            mark = 0xCB956D
        ),
        ATOM_ONE_KEY to SyntaxTheme(
            key = ATOM_ONE_KEY,
            code = 0x383A42,
            keyword = 0xA626A4,
            string = 0x50A14F,
            literal = 0x986801,
            comment = 0xA1A1A1,
            metadata = 0xC18401,
            multilineComment = 0xA1A1A1,
            punctuation = 0xE45649,
            mark = 0x526FFF,
        )

    )

    fun themes(darkMode: Boolean = false) = if (darkMode) dark else light

    fun default(darkMode: Boolean = false) = themes(darkMode)[DARCULA_KEY]!!

    fun darcula(darkMode: Boolean = false) = themes(darkMode)[DARCULA_KEY]!!
    fun monokai(darkMode: Boolean = false) = themes(darkMode)[MONOKAI_KEY]!!
    fun notepad(darkMode: Boolean = false) = themes(darkMode)[NOTEPAD_KEY]!!
    fun matrix(darkMode: Boolean = false) = themes(darkMode)[MATRIX_KEY]!!
    fun pastel(darkMode: Boolean = false) = themes(darkMode)[PASTEL_KEY]!!
    fun atom(darkMode: Boolean = false) = themes(darkMode)[ATOM_ONE_KEY]!!

    fun getNames(darkMode: Boolean = false): List<String> {
        val source = if (darkMode) dark else light

        return source.map {
            it.key
                .lowercase()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }
    }

    fun getByName(name: String, darkMode: Boolean = false): SyntaxTheme? {
        val source = if (darkMode) dark else light
        return source[name.lowercase()]
    }

    fun SyntaxTheme.useDark(darkMode: Boolean) = if (darkMode) dark[key] else light[key]
}
