package ui.tabs.categories

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
import ui.articles.details.ArticleDetailsComponent
import ui.articles.list.ArticlesListComponent
import ui.categories.CategoriesListComponent

class CategoriesComponent(
    private val componentContext: ComponentContext,
    private val onConfigChange: (NavConfig) -> Unit,
    private val onArticleSelected: ((Article) -> Unit),
    ) : ComponentContext by componentContext {

    var categoriesNavigation: StackNavigation<NavConfig.CategoriesConfig> = StackNavigation()
    var categoriesStack: Value<ChildStack<NavConfig.CategoriesConfig, NavChild.CategoriesChild>> = childStack(
        source = categoriesNavigation,
        serializer = NavConfig.CategoriesConfig.serializer(),
        initialConfiguration = NavConfig.CategoriesConfig.CategoriesListConfig,
        handleBackButton = true,
        childFactory = ::categoriesChildFactory,
        key = "sources"
    )

    init {
        categoriesStack.observe {
            onConfigChange(it.active.configuration)
        }
    }

    private fun categoriesChildFactory(config: NavConfig.CategoriesConfig, componentContext: ComponentContext): NavChild.CategoriesChild {
        return when (config) {
            is NavConfig.CategoriesConfig.ArticlesListConfig -> NavChild.CategoriesChild.ArticlesList(
                ArticlesListComponent(
                    componentContext,
                    category = config.category,
                    onArticleSelected = onArticleSelected,
                    onBack = { categoriesNavigation.pop() }
                ))

            NavConfig.CategoriesConfig.CategoriesListConfig -> NavChild.CategoriesChild.CategoriesList(
                CategoriesListComponent(
                    componentContext
                ) {
                    categoriesNavigation.push(NavConfig.CategoriesConfig.ArticlesListConfig(it))
                })
        }
    }
}