package dev.snipme.highlights.internal

import dev.snipme.highlights.Highlights
import dev.snipme.highlights.HighlightsResultListener
import dev.snipme.highlights.model.CodeHighlight
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.time.Duration
import kotlin.time.TimeSource.Monotonic.markNow
import kotlin.time.measureTime

@OptIn(ExperimentalCoroutinesApi::class)
class HighlightsTest {
    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `returns list of code highlights for sync call`() {
        val default = Highlights.default().apply {
            setCode(longJavaCode)
        }

        val highlights = default.getHighlights()
        assertTrue { highlights.isNotEmpty() }
    }

    @Test
    fun `returns error for exception during analysis`() = runTest {
        val default = Highlights.default().apply {
            setCode(longJavaCode)
        }

        var error: Throwable? = null
        suspendCancellableCoroutine { continuation ->
            invokeHighlightsRequest(
                default,
                onStart = { throw IllegalStateException() },
                onError = {
                    error = it
                    continuation.resume(Unit) {}
                },
            )
        }

        assertTrue { error != null }
    }

    @Test
    fun `cancels first analysis when second is invoked`() = runTest {
        val default = Highlights.default().apply {
            setCode(longJavaCode)
        }

        var wasCancelled = false
        suspendCancellableCoroutine { continuation ->
            invokeHighlightsRequest(
                default,
                onCancel = { wasCancelled = true },
                onStart = {
                    invokeHighlightsRequest(default) {
                        assertTrue { wasCancelled }
                        continuation.resume(Unit) {}
                    }
                },
            )
        }
    }

    @Test
    fun `returns list of code highlights asynchronously`() = runTest {
        val default = Highlights.default().apply {
            setCode(longJavaCode)
        }

        val result = suspendCancellableCoroutine { continuation ->
            invokeHighlightsRequest(default) {
                continuation.resume(it) {}
            }
        }

        assertTrue { result.isNotEmpty() }
    }

    @Test
    fun `returns asynchronous results one by one`() = runTest {
        val default = Highlights.default().apply {
            setCode(longJavaCode)
        }

        var result1: List<CodeHighlight>
        val time1 = measureTime {
            result1 = suspendCancellableCoroutine { continuation ->
                invokeHighlightsRequest(default) {
                    continuation.resume(it) {}
                }
            }
        }
        println("Time1: ${time1.inWholeMilliseconds} ms")
        assertTrue { result1.isNotEmpty() }

        default.setCode(longJavaCode.replace("static", "statac"))

        var result2: List<CodeHighlight>
        val time2 = measureTime {
            result2 = suspendCancellableCoroutine { continuation ->
                invokeHighlightsRequest(default) {
                    continuation.resume(it) {}
                }
            }
        }
        println("Time2: ${time2.inWholeMilliseconds} ms")
        assertTrue { result2.isNotEmpty() }

        assertTrue { time2.inWholeMilliseconds < time1.inWholeMilliseconds }
    }

    @Test
    fun `returns immediately result from second invocation`() = runTest {
        val default = Highlights.default().apply {
            setCode(longJavaCode)
        }

        var time1: Duration
        var time2: Duration

        launch {
            suspendCancellableCoroutine { c ->
                invokeAndMeasureTime(default) {
                    time1 = it
                    c.resume(Unit) {}
                    println("Time1: ${time1.inWholeMilliseconds} ms")
                }
            }
        }

        launch {
            suspendCancellableCoroutine { c ->
                invokeAndMeasureTime(default) {
                    time2 = it
                    c.resume(Unit) {}
                    println("Time2: ${time2.inWholeMilliseconds} ms")
                }
            }
        }
    }

    private fun invokeAndMeasureTime(
        highlights: Highlights,
        onFinish: (Duration) -> Unit = {}
    ) {
        var result: Duration? = null
        val now = markNow()

        fun updateFirstTime() {
            if (result == null) {
                result = now.elapsedNow()
                onFinish(result!!)
            }
        }

        highlights.clearSnapshot()
        invokeHighlightsRequest(
            highlights,
            onStart = { println("Start"); },
            onCancel = { println("Cancel"); updateFirstTime() },
            onError = { println("Error: $it"); updateFirstTime() },
            onCompleted = { println("Completed"); updateFirstTime() },
        )
    }

    private fun invokeHighlightsRequest(
        highlights: Highlights,
        onStart: () -> Unit = {},
        onCancel: () -> Unit = {},
        onError: (Throwable) -> Unit = {},
        onCompleted: (List<CodeHighlight>) -> Unit = {},
    ) {
        highlights.getHighlightsAsync(object : HighlightsResultListener {
            override fun onStart() = onStart()
            override fun onComplete(result: List<CodeHighlight>) = onCompleted(result)
            override fun onError(exception: Throwable) = onError(exception)
            override fun onCancel() = onCancel()
        })
    }
}