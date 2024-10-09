package dev.snipme.highlights.internal.language

import dev.snipme.highlights.Highlights
import dev.snipme.highlights.model.SyntaxLanguage
import kotlin.test.Test
import kotlin.test.assertEquals

class CommentTest {

    @Test
    fun test() {
        val code = """
            /**
            * Class's start
            */
            class PagesComponent(
                context: ComponentContext,
                container: () -> PagesContainer, // inject using DI or create
            ) : ComponentContext by context,
                PagesStore by context.retainedStore(factory = container) {

                'a'

                private val navigator = PagesNavigation<PageConfig>()

                val pages = childPages(
                    source = navigator,
                    serializer = PageConfig.serializer(),
                    initialPages = { Pages(items = List(5) { PageConfig(it) }, selectedIndex = 0) },
                    childFactory = ::PageComponent,
                )
                
                "b"

                init {
                    // subscribe to the store following the component 's lifecycle
                    subscribe {
                        actions.collect { action ->
                            when (action) {
                                is SelectPage -> navigator.select(action.index)
                            }
                        }
                    }
                }
            }

            class PageComponent(
                page: PageConfig,
                context: ComponentContext,
            ) : ComponentContext by context,
                Container<PageState, PageIntent, Nothing> {

                override val store = store(PageState(page.page), coroutineScope()) {

                `c`

                    // state keeper will preserve the store's state
                    keepState(context.stateKeeper, PageState.serializer())
                    reduce { intent ->
                        when (intent) {
                            is ClickedIncrementCounter -> updateState {
                                copy(counter = counter + 1)
                            }
                        }
                    }
                }
            }
        """.trimIndent()

        val result = Highlights.Builder(
            language = SyntaxLanguage.KOTLIN,
            code = code
        ).build().getCodeStructure()

        result.printStructure(code)
        assertEquals(2, result.strings.size)
    }
}