package ui.tabs.headlines

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import ui.articles.details.ArticleDetailsComponent
import ui.articles.list.ArticlesListComponent

class HeadlinesComponent(
    private val componentContext: ComponentContext,
) : ComponentContext by componentContext {

    private var headlinesNavigation: StackNavigation<HeadlinesConfig> = StackNavigation()
    var headlinesStack: Value<ChildStack<HeadlinesConfig, HeadlinesChild>> = childStack(
        source = headlinesNavigation,
        serializer = HeadlinesConfig.serializer(),
        initialConfiguration = HeadlinesConfig.ArticlesListConfig,
        handleBackButton = true,
        childFactory = ::headlinesChildFactory,
        key = "headlines"
    )

    private fun headlinesChildFactory(config: HeadlinesConfig, componentContext: ComponentContext): HeadlinesChild {
        return when (config) {
            is HeadlinesConfig.ArticleDetailsConfig -> HeadlinesChild.ArticleDetails(
                ArticleDetailsComponent(
                    componentContext,
                    config.article
                ) {
                    headlinesNavigation.pop()
                }
            )

            HeadlinesConfig.ArticlesListConfig ->
                HeadlinesChild.ArticlesList(ArticlesListComponent(
                    componentContext = componentContext,
                    onArticleSelected = { headlinesNavigation.push(HeadlinesConfig.ArticleDetailsConfig(it)) },
                    onBack = { headlinesNavigation.pop() }
                ))
        }
    }
}
