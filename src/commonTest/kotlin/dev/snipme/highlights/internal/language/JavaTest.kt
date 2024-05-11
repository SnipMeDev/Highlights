import dev.snipme.highlights.Highlights
import dev.snipme.highlights.model.SyntaxLanguage
import kotlin.test.Test
import kotlin.test.assertEquals

class JavaTest {

    @Test
    fun test() {
        val code = """
           this.class.abcd ) new
        """.trimIndent()

        val result = Highlights.Builder(
            language = SyntaxLanguage.JAVA,
            code = code
        ).build().getCodeStructure()

        assertEquals(3, result.keywords.size)
    }
}