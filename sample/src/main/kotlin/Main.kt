import dev.snipme.highlights.DefaultHighlightsResultListener
import dev.snipme.highlights.Highlights
import dev.snipme.highlights.model.BoldHighlight
import dev.snipme.highlights.model.CodeHighlight
import dev.snipme.highlights.model.PhraseLocation
import dev.snipme.highlights.model.SyntaxLanguage
import dev.snipme.highlights.model.SyntaxThemes
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine

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
    runBlocking {
        val highlights = Highlights.Builder()
            .code(sampleClass)
            .theme(SyntaxThemes.monokai())
            .language(SyntaxLanguage.JAVA)
            .build()

        val syncResult = runSync(highlights)
        println("Sync count with emphasis: ${syncResult.size}")

        launch {
            suspendCancellableCoroutine { continuation ->
                runAsync(highlights) { asyncResult ->
                    assert(syncResult == asyncResult)
                    println("Async count: ${asyncResult.size}")
                    continuation.resumeWith(Result.success(Unit))
                }
            }
        }
    }
}

fun runSync(highlights: Highlights): List<CodeHighlight> {
    println("### HIGHLIGHTS ###")
    println()

    println("Available languages:")
    println("${SyntaxLanguage.getNames()}")
    println()

    println("Available themes:")
    println("${SyntaxThemes.getNames()}")
    println()

    println("This is a sample class:")
    println(sampleClass)
    println()

    val structure = highlights.getCodeStructure()

    println("After analysis there has been found:")
    println("${structure.printStructure(sampleClass)}")
    println()

    val newInstance = highlights.getBuilder()
        .emphasis(PhraseLocation(0, 13))
        .build()

    println("The emphasis was put on the word:")
    val result = newInstance.getHighlights()
    val emphasisLocation = result
        .filterIsInstance<BoldHighlight>()
        .first()
        .location

    println(sampleClass.substring(emphasisLocation.start, emphasisLocation.end))

    return result
}

fun runAsync(
    highlights: Highlights,
    emitResult: (List<CodeHighlight>) -> Unit,
) {
    println()
    println("### ASYNC HIGHLIGHTS ###")

    highlights.getHighlightsAsync(
        object : DefaultHighlightsResultListener() {
            // onStart
            // onError
            // onCancel
            override fun onSuccess(result: List<CodeHighlight>) {
                emitResult(result)
            }
        }
    )
}