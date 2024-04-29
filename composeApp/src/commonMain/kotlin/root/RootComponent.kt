package root

import TabsToArticleChild
import TabsToArticleConfig
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.core.component.KoinComponent
import ui.articles.details.ArticleDetailsComponent
import ui.tabs.TabsComponent

@ExperimentalResourceApi
class RootComponent(
    private val componentContext: ComponentContext,
) : KoinComponent, ComponentContext by componentContext {

    private var tabsToArticleNavigation: StackNavigation<TabsToArticleConfig> = StackNavigation()
    var tabsToArticleStack: Value<ChildStack<TabsToArticleConfig, TabsToArticleChild>> = childStack(
        source = tabsToArticleNavigation,
        serializer = TabsToArticleConfig.serializer(),
        initialConfiguration = TabsToArticleConfig.TabsConfig,
        handleBackButton = true,
        childFactory = ::tabsToArticleChildFactory,
        key = "tabsToArticles"
    )

    private fun tabsToArticleChildFactory(config: TabsToArticleConfig, componentContext: ComponentContext): TabsToArticleChild {
        return when (config) {
            is TabsToArticleConfig.ArticleConfig -> TabsToArticleChild.ArticleDetails(
                ArticleDetailsComponent(
                    componentContext,
                    config.article
                ) {
                    tabsToArticleNavigation.pop()
                }
            )

            TabsToArticleConfig.TabsConfig -> TabsToArticleChild.Tabs(TabsComponent(componentContext) {
                tabsToArticleNavigation.push(TabsToArticleConfig.ArticleConfig(it))
            })
        }
    }
}
