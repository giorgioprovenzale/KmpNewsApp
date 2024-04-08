package ui.tabs.sources

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import ui.articles.details.ArticleDetailsComponent
import ui.articles.list.ArticlesListComponent
import ui.sources.SourcesListComponent

class SourcesComponent(
    private val componentContext: ComponentContext,
) : ComponentContext by componentContext {

    private var sourcesNavigation: StackNavigation<SourcesConfig> = StackNavigation()
    var sourcesStack: Value<ChildStack<SourcesConfig, SourcesChild>> = childStack(
        source = sourcesNavigation,
        serializer = SourcesConfig.serializer(),
        initialConfiguration = SourcesConfig.SourcesListConfig,
        handleBackButton = true,
        childFactory = ::sourcesChildFactory,
        key = "sources"
    )

    private fun sourcesChildFactory(config: SourcesConfig, componentContext: ComponentContext): SourcesChild {
        return when (config) {

            is SourcesConfig.ArticleDetailsConfig -> SourcesChild.ArticleDetails(ArticleDetailsComponent(componentContext, config.article))
            SourcesConfig.ArticlesListConfig -> SourcesChild.ArticlesList(ArticlesListComponent(componentContext) {
                sourcesNavigation.push(SourcesConfig.ArticleDetailsConfig(it))
            })

            SourcesConfig.SourcesListConfig -> SourcesChild.SourcesList(SourcesListComponent(componentContext) {

            })
        }
    }
}