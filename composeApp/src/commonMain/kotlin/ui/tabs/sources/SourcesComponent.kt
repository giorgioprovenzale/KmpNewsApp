package ui.tabs.sources

import NavChild
import NavConfig
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import domain.models.Article
import ui.articles.list.ArticlesListComponent
import ui.sources.SourcesListComponent

class SourcesComponent(
    private val componentContext: ComponentContext,
    private val onConfigChange: (NavConfig) -> Unit,
    private val onArticleSelected: ((Article) -> Unit),
) : ComponentContext by componentContext {

    var sourcesNavigation: StackNavigation<NavConfig.SourcesConfig> = StackNavigation()
    var sourcesStack: Value<ChildStack<NavConfig.SourcesConfig, NavChild.SourcesChild>> = childStack(
        source = sourcesNavigation,
        serializer = NavConfig.SourcesConfig.serializer(),
        initialConfiguration = NavConfig.SourcesConfig.SourcesListConfig,
        handleBackButton = true,
        childFactory = ::sourcesChildFactory,
        key = "sources"
    )

    init {
        sourcesStack.observe {
            onConfigChange(it.active.configuration)
        }
    }

    private fun sourcesChildFactory(config: NavConfig.SourcesConfig, componentContext: ComponentContext): NavChild.SourcesChild {
        return when (config) {
            is NavConfig.SourcesConfig.ArticlesListConfig -> NavChild.SourcesChild.ArticlesList(
                ArticlesListComponent(
                    componentContext,
                    source = config.source,
                    onArticleSelected = onArticleSelected,
                    onBack = { sourcesNavigation.pop() }
                ))

            NavConfig.SourcesConfig.SourcesListConfig -> NavChild.SourcesChild.SourcesList(SourcesListComponent(componentContext) {
                sourcesNavigation.push(NavConfig.SourcesConfig.ArticlesListConfig(it))
            })
        }
    }
}