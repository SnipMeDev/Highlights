import dev.snipme.highlights.Highlights
import dev.snipme.highlights.model.BoldHighlight
import dev.snipme.highlights.model.PhraseLocation
import dev.snipme.highlights.model.SyntaxLanguage

val sampleClass = """
    @Serializable
    public class ExampleClass {
        // Single-line comment
        
        /* Multi-line
         * comment */
        
        // Class variables
        private int x;
        protected float y;
        public static final String MESSAGE = "Hello!";
        
        // Constructor
        public ExampleClass() {
            this.x = 0;
            this.y = 0.0f;
        }
        
        // Method with parameters and return type
        public static int calculateSum(int a, int b) {
            int sum = a + b;
            return sum;
        }
    }
""".trimIndent()

fun main() {
    println("### HIGHLIGHTS ###")
    println()

    println("Available languages:")
    println("${SyntaxLanguage.getNames()}")
    println()

    println("Available themes:")
    println("${Highlights.themes(darkMode = false).keys}")
    println()

    println("This is a sample class:")
    println(sampleClass)
    println()

    val highlights = Highlights.Builder()
        .code(sampleClass)
        .language(SyntaxLanguage.JAVA)
        .build()

    val structure = highlights.getCodeStructure()

    println("After analysis there has been found:")
    println("${structure.printPhrases(sampleClass)}")
    println()

    val newInstance = highlights.getBuilder()
        .emphasis(PhraseLocation(0, 13))
        .build()

    println("The emphasis was put on the word:")
    val emphasisLocation = newInstance
        .getHighlights()
        .filterIsInstance<BoldHighlight>()
        .first()
        .location

    println(sampleClass.substring(emphasisLocation.start, emphasisLocation.end))
}