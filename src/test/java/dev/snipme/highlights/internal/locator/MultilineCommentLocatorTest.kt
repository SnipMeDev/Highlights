package dev.snipme.highlights.internal.locator

import dev.snipme.highlights.internal.printResults
import dev.snipme.highlights.model.PhraseLocation
import kotlin.test.Test
import kotlin.test.assertEquals

internal class MultilineCommentLocatorTest {

    @Test
    fun `Returns location of one-line comment`() {
        val testCode = """
            /* Comment */
            /*** Longer comment **/
            '''Different comment'''
            "ABCD"
            // ABCD
            /// ABCD
        """.trimIndent()

        val result = MultilineCommentLocator.locate(testCode)

        assertEquals(2, result.size)
        assertEquals(PhraseLocation(0, 13), result[0])
        assertEquals(PhraseLocation(14, 37), result[1])
    }

    @Test
    fun `Returns location of multi-line comment`() {
        val testCode = """
            /***
             Comment
            */
        """.trimIndent()

        val result = MultilineCommentLocator.locate(testCode)

        result.printResults(testCode)

        assertEquals(1, result.size)
        assertEquals(PhraseLocation(0, 16), result[0])
    }

    @Test
    fun `Returns full location of complex comment`() {
        val testCode = complexComment.trimIndent()

        val result = MultilineCommentLocator.locate(testCode)

        assertEquals(1, result.size)
        assertEquals(PhraseLocation(0, 8747), result[0])
    }
}

private val complexComment =
    """
        /**
         * {@code Activity} which displays a fullscreen Flutter UI.
         *
         * <p>{@code FlutterActivity} is the simplest and most direct way to integrate Flutter within an
         * Android app.
         *
         * <p><strong>FlutterActivity responsibilities</strong>
         *
         * <p>{@code FlutterActivity} maintains the following responsibilities:
         *
         * <ul>
         *   <li>Displays an Android launch screen.
         *   <li>Displays a Flutter splash screen.
         *   <li>Configures the status bar appearance.
         *   <li>Chooses the Dart execution app bundle path, entrypoint and entrypoint arguments.
         *   <li>Chooses Flutter's initial route.
         *   <li>Renders {@code Activity} transparently, if desired.
         *   <li>Offers hooks for subclasses to provide and configure a {@link
         *       io.flutter.embedding.engine.FlutterEngine}.
         *   <li>Save and restore instance state, see {@code #shouldRestoreAndSaveState()};
         * </ul>
         *
         * <p><strong>Dart entrypoint, entrypoint arguments, initial route, and app bundle path</strong>
         *
         * <p>The Dart entrypoint executed within this {@code Activity} is "main()" by default. To change
         * the entrypoint that a {@code FlutterActivity} executes, subclass {@code FlutterActivity} and
         * override {@link #getDartEntrypointFunctionName()}. For non-main Dart entrypoints to not be
         * tree-shaken away, you need to annotate those functions with {@code @pragma('vm:entry-point')} in
         * Dart.
         *
         * <p>The Dart entrypoint arguments will be passed as a list of string to Dart's entrypoint
         * function. It can be passed using a {@link NewEngineIntentBuilder} via {@link
         * NewEngineIntentBuilder#dartEntrypointArgs}.
         *
         * <p>The Flutter route that is initially loaded within this {@code Activity} is "/". The initial
         * route may be specified explicitly by passing the name of the route as a {@code String} in {@link
         * FlutterActivityLaunchConfigs#EXTRA_INITIAL_ROUTE}, e.g., "my/deep/link".
         *
         * <p>The initial route can each be controlled using a {@link NewEngineIntentBuilder} via {@link
         * NewEngineIntentBuilder#initialRoute}.
         *
         * <p>The app bundle path, Dart entrypoint, Dart entrypoint arguments, and initial route can also be
         * controlled in a subclass of {@code FlutterActivity} by overriding their respective methods:
         *
         * <ul>
         *   <li>{@link #getAppBundlePath()}
         *   <li>{@link #getDartEntrypointFunctionName()}
         *   <li>{@link #getDartEntrypointArgs()}
         *   <li>{@link #getInitialRoute()}
         * </ul>
         *
         * <p>The Dart entrypoint and app bundle path are not supported as {@code Intent} parameters since
         * your Dart library entrypoints are your private APIs and Intents are invocable by other processes.
         *
         * <p><strong>Using a cached FlutterEngine</strong>
         *
         * <p>{@code FlutterActivity} can be used with a cached {@link
         * io.flutter.embedding.engine.FlutterEngine} instead of creating a new one. Use {@link
         * #withCachedEngine(String)} to build a {@code FlutterActivity} {@code Intent} that is configured
         * to use an existing, cached {@link io.flutter.embedding.engine.FlutterEngine}. {@link
         * io.flutter.embedding.engine.FlutterEngineCache} is the cache that is used to obtain a given
         * cached {@link io.flutter.embedding.engine.FlutterEngine}. You must create and put a {@link
         * io.flutter.embedding.engine.FlutterEngine} into the {@link
         * io.flutter.embedding.engine.FlutterEngineCache} yourself before using the {@link
         * #withCachedEngine(String)} builder. An {@code IllegalStateException} will be thrown if a cached
         * engine is requested but does not exist in the cache.
         *
         * <p>When using a cached {@link io.flutter.embedding.engine.FlutterEngine}, that {@link
         * io.flutter.embedding.engine.FlutterEngine} should already be executing Dart code, which means
         * that the Dart entrypoint and initial route have already been defined. Therefore, {@link
         * CachedEngineIntentBuilder} does not offer configuration of these properties.
         *
         * <p>It is generally recommended to use a cached {@link io.flutter.embedding.engine.FlutterEngine}
         * to avoid a momentary delay when initializing a new {@link
         * io.flutter.embedding.engine.FlutterEngine}. The two exceptions to using a cached {@link
         * FlutterEngine} are:
         *
         * <ul>
         *   <li>When {@code FlutterActivity} is the first {@code Activity} displayed by the app, because
         *       pre-warming a {@link io.flutter.embedding.engine.FlutterEngine} would have no impact in
         *       this situation.
         *   <li>When you are unsure when/if you will need to display a Flutter experience.
         * </ul>
         *
         * <p>See https://flutter.dev/docs/development/add-to-app/performance for additional performance
         * explorations on engine loading.
         *
         * <p>The following illustrates how to pre-warm and cache a {@link
         * io.flutter.embedding.engine.FlutterEngine}:
         *
         * <pre>{@code
         * // Create and pre-warm a FlutterEngine.
         * FlutterEngine flutterEngine = new FlutterEngine(context);
         * flutterEngine.getDartExecutor().executeDartEntrypoint(DartEntrypoint.createDefault());
         *
         * // Cache the pre-warmed FlutterEngine in the FlutterEngineCache.
         * FlutterEngineCache.getInstance().put("my_engine", flutterEngine);
         * }</pre>
         *
         * <p><strong>Alternatives to FlutterActivity</strong>
         *
         * <p>If Flutter is needed in a location that cannot use an {@code Activity}, consider using a
         * {@link FlutterFragment}. Using a {@link FlutterFragment} requires forwarding some calls from an
         * {@code Activity} to the {@link FlutterFragment}.
         *
         * <p>If Flutter is needed in a location that can only use a {@code View}, consider using a {@link
         * FlutterView}. Using a {@link FlutterView} requires forwarding some calls from an {@code
         * Activity}, as well as forwarding lifecycle calls from an {@code Activity} or a {@code Fragment}.
         *
         * <p><strong>Launch Screen and Splash Screen</strong>
         *
         * <p>{@code FlutterActivity} supports the display of an Android "launch screen" as well as a
         * Flutter-specific "splash screen". The launch screen is displayed while the Android application
         * loads. It is only applicable if {@code FlutterActivity} is the first {@code Activity} displayed
         * upon loading the app. After the launch screen passes, a splash screen is optionally displayed.
         * The splash screen is displayed for as long as it takes Flutter to initialize and render its first
         * frame.
         *
         * <p>Use Android themes to display a launch screen. Create two themes: a launch theme and a normal
         * theme. In the launch theme, set {@code windowBackground} to the desired {@code Drawable} for the
         * launch screen. In the normal theme, set {@code windowBackground} to any desired background color
         * that should normally appear behind your Flutter content. In most cases this background color will
         * never be seen, but for possible transition edge cases it is a good idea to explicitly replace the
         * launch screen window background with a neutral color.
         *
         * <p>Do not change aspects of system chrome between a launch theme and normal theme. Either define
         * both themes to be fullscreen or not, and define both themes to display the same status bar and
         * navigation bar settings. To adjust system chrome once the Flutter app renders, use platform
         * channels to instruct Android to do so at the appropriate time. This will avoid any jarring visual
         * changes during app startup.
         *
         * <p>In the AndroidManifest.xml, set the theme of {@code FlutterActivity} to the defined launch
         * theme. In the metadata section for {@code FlutterActivity}, defined the following reference to
         * your normal theme:
         *
         * <p>{@code <meta-data android:name="io.flutter.embedding.android.NormalTheme"
         * android:resource="@style/YourNormalTheme" /> }
         *
         * <p>With themes defined, and AndroidManifest.xml updated, Flutter displays the specified launch
         * screen until the Android application is initialized.
         *
         * <p>Flutter also requires initialization time. To specify a splash screen for Flutter
         * initialization, subclass {@code FlutterActivity} and override {@link #provideSplashScreen()}. See
         * {@link SplashScreen} for details on implementing a splash screen.
         *
         * <p>Flutter ships with a splash screen that automatically displays the exact same {@code
         * windowBackground} as the launch theme discussed previously. To use that splash screen, include
         * the following metadata in AndroidManifest.xml for this {@code FlutterActivity}:
         *
         * <p>{@code <meta-data android:name="io.flutter.embedding.android.SplashScreenDrawable"
         * android:resource="@drawable/launch_background" /> }
         *
         * <p><strong>Alternative Activity</strong> {@link FlutterFragmentActivity} is also available, which
         * is similar to {@code FlutterActivity} but it extends {@code FragmentActivity}. You should use
         * {@code FlutterActivity}, if possible, but if you need a {@code FragmentActivity} then you should
         * use {@link FlutterFragmentActivity}.
         */
        // A number of methods in this class have the same implementation as FlutterFragmentActivity. These
        // methods are duplicated for readability purposes. Be sure to replicate any change in this class in
        // FlutterFragmentActivity, too.
    """.trimIndent()