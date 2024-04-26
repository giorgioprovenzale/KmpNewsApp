package ui.tabs.headlines

import NavChild
import NavConfig
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import domain.models.Article
import ui.articles.details.ArticleDetailsComponent
import ui.articles.list.ArticlesListComponent

class HeadlinesComponent(
    private val componentContext: ComponentContext,
    private val onConfigChange: (NavConfig) -> Unit,
    private val onArticleSelected: ((Article) -> Unit),
) : ComponentContext by componentContext {

    var headlinesNavigation: StackNavigation<NavConfig.HeadlinesConfig> = StackNavigation()
    var headlinesStack: Value<ChildStack<NavConfig.HeadlinesConfig, NavChild.HeadlinesChild>> = childStack(
        source = headlinesNavigation,
        serializer = NavConfig.HeadlinesConfig.serializer(),
        initialConfiguration = NavConfig.HeadlinesConfig.ArticlesListConfig,
        handleBackButton = true,
        childFactory = ::headlinesChildFactory,
        key = "headlines"
    )

    init {
        headlinesStack.observe {
            onConfigChange(it.active.configuration)
        }
    }

    private fun headlinesChildFactory(config: NavConfig.HeadlinesConfig, componentContext: ComponentContext): NavChild.HeadlinesChild {
        return when (config) {
            NavConfig.HeadlinesConfig.ArticlesListConfig ->
                NavChild.HeadlinesChild.ArticlesList(ArticlesListComponent(
                    componentContext = componentContext,
                    onArticleSelected = onArticleSelected,
                    onBack = { headlinesNavigation.pop() }
                ))
        }
    }
}
