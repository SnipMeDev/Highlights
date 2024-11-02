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

        val results = mutableListOf<List<CodeHighlight>>()

        suspendCancellableCoroutine { continuation ->
            invokeHighlightsRequest(
                default,
                onSuccess = { results.add(it) },
                onStart = {
                    invokeHighlightsRequest(default) {
                        results.add(it)
                        continuation.resume(Unit) {}
                    }
                },
            )
        }

        assertTrue { results.size == 1 }
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
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class HighlightsCancellationTest {
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
    fun `returns immediately result from second invocation`() = runTest {
        val default = Highlights.default().apply {
            setCode(longJavaCode)
        }

        var time1 = Duration.ZERO
        var time2 = Duration.ZERO

        val job1 = launch {
            suspendCancellableCoroutine { c ->
                invokeAndMeasureTime(default, "#1") {
                    time1 = it
                    c.resume(Unit) {}
                    println("Time1: ${it.inWholeMilliseconds} ms")
                }
            }
        }

        val job2 = launch {
            suspendCancellableCoroutine { c ->
                invokeAndMeasureTime(default, "#2") {
                    time2 = it
                    c.resume(Unit) {}
                    println("Time2: ${it.inWholeMilliseconds} ms")
                }
            }
        }

        job1.join()
        job2.join()
        assertTrue { time1.inWholeMilliseconds < time2.inWholeMilliseconds }
    }
}

private fun invokeAndMeasureTime(
    highlights: Highlights,
    name: String,
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
        onStart = { println("Start $name"); },
        onCancel = { println("Cancel $name"); updateFirstTime() },
        onError = { println("Error $name: $it"); updateFirstTime() },
        onSuccess = { println("Success $name"); updateFirstTime() },
    )
}

private fun invokeHighlightsRequest(
    highlights: Highlights,
    onStart: () -> Unit = {},
    onCancel: () -> Unit = {},
    onError: (Throwable) -> Unit = {},
    onSuccess: (List<CodeHighlight>) -> Unit = {}
) {
    highlights.getHighlightsAsync(object : HighlightsResultListener {
        override fun onStart() = onStart()
        override fun onSuccess(result: List<CodeHighlight>) = onSuccess(result)
        override fun onError(exception: Throwable) = onError(exception)
        override fun onCancel() = onCancel()
    })
}