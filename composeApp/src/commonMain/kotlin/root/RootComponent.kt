package root

import NavConfig
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import org.koin.core.component.KoinComponent
import ui.tabs.categories.CategoriesComponent
import ui.tabs.headlines.HeadlinesComponent
import ui.tabs.sources.SourcesComponent

class RootComponent(
    private val componentContext: ComponentContext,
) : KoinComponent, ComponentContext by componentContext {

    private val _state = MutableValue(RootState(title = "", showBack = false))
    val state: Value<RootState> = _state

    private val currentConfigByTabMap = HashMap<TabsConfig, NavConfig>()

    var tabsNavigation: StackNavigation<TabsConfig> = StackNavigation()
    var tabsStack: Value<ChildStack<TabsConfig, TabsChild>> = childStack(
        source = tabsNavigation,
        serializer = TabsConfig.serializer(),
        initialConfiguration = TabsConfig.HeadlinesTabConfig,
        handleBackButton = true,
        childFactory = ::tabsChildFactory,
        key = "tabs"
    )

    init {
        tabsStack.observe {
            updateStateByNestedConfig(currentConfigByTabMap[it.active.configuration])
        }
    }

    private fun tabsChildFactory(config: TabsConfig, componentContext: ComponentContext): TabsChild {
        return when (config) {
            is TabsConfig.HeadlinesTabConfig -> TabsChild.Headlines(
                HeadlinesComponent(componentContext) {
                    onConfigChange(TabsConfig.HeadlinesTabConfig, it)
                }
            )

            is TabsConfig.SourcesTabConfig -> TabsChild.SourcesList(
                SourcesComponent(componentContext) {
                    onConfigChange(TabsConfig.SourcesTabConfig, it)
                }
            )

            TabsConfig.CategoriesTabConfig -> TabsChild.CategoriesList(
                CategoriesComponent(componentContext)
            )
        }
    }

    private fun onConfigChange(tabsConfig: TabsConfig, navConfig: NavConfig) {
        currentConfigByTabMap[tabsConfig] = navConfig
        updateStateByNestedConfig(navConfig)
    }

    private fun updateStateByNestedConfig(navConfig: NavConfig?) {
        navConfig?.let {
            when (navConfig) {
                is NavConfig.HeadlinesConfig.ArticleDetailsConfig -> _state.update { it.copy(title = navConfig.article.title.orEmpty(), showBack = true) }
                NavConfig.HeadlinesConfig.ArticlesListConfig -> _state.update { it.copy(title = "Home", showBack = false) }
                is NavConfig.SourcesConfig.ArticleDetailsConfig -> _state.update { it.copy(title = navConfig.article.title.orEmpty(), showBack = true) }
                is NavConfig.SourcesConfig.ArticlesListConfig -> _state.update { it.copy(title = navConfig.source.name, showBack = true) }
                NavConfig.SourcesConfig.SourcesListConfig -> _state.update { it.copy(title = "Sources", showBack = false) }
            }
        }
    }
}
