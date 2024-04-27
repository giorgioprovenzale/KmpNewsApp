package ui.tabs

import NavConfig
import TabsChild
import TabsConfig
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.active
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import domain.models.Article
import kmpnewsapp.composeapp.generated.resources.Res
import kmpnewsapp.composeapp.generated.resources.categories
import kmpnewsapp.composeapp.generated.resources.home
import kmpnewsapp.composeapp.generated.resources.sources
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.getString
import org.koin.core.component.KoinComponent
import ui.tabs.categories.CategoriesComponent
import ui.tabs.headlines.HeadlinesComponent
import ui.tabs.sources.SourcesComponent

@ExperimentalResourceApi
class TabsComponent(
    private val componentContext: ComponentContext,
    private val onArticleSelected: ((Article) -> Unit)
) : KoinComponent, ComponentContext by componentContext {

    private val _state = MutableStateFlow(TabsState(title = "", showBack = false))
    val state: StateFlow<TabsState> = _state

    private val currentConfigByTabMap = HashMap<TabsConfig, NavConfig>()
    private val navigationByTabMap = HashMap<TabsConfig, StackNavigation<out NavConfig>>()

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
            is TabsConfig.HeadlinesTabConfig -> {
                val component = HeadlinesComponent(
                    componentContext,
                    onConfigChange = {
                        onConfigChange(TabsConfig.HeadlinesTabConfig, it)
                    },
                    onArticleSelected = onArticleSelected
                )
                navigationByTabMap[config] = component.headlinesNavigation
                TabsChild.Headlines(component)
            }

            is TabsConfig.SourcesTabConfig -> {
                val component = SourcesComponent(
                    componentContext,
                    onConfigChange = {
                        onConfigChange(TabsConfig.SourcesTabConfig, it)
                    },
                    onArticleSelected = onArticleSelected
                )
                navigationByTabMap[config] = component.sourcesNavigation
                TabsChild.SourcesList(component)
            }

            TabsConfig.CategoriesTabConfig -> {
                val component = CategoriesComponent(
                    componentContext,
                    onConfigChange = {
                        onConfigChange(TabsConfig.CategoriesTabConfig, it)
                    },
                    onArticleSelected = onArticleSelected
                )
                navigationByTabMap[config] = component.categoriesNavigation
                TabsChild.CategoriesList(component)
            }
        }
    }

    private fun onConfigChange(tabsConfig: TabsConfig, navConfig: NavConfig) {
        currentConfigByTabMap[tabsConfig] = navConfig
        updateStateByNestedConfig(navConfig)
    }

    private fun updateStateByNestedConfig(navConfig: NavConfig?) {
        navConfig?.let {
            CoroutineScope(Dispatchers.Default).launch {
                when (navConfig) {
                    NavConfig.HeadlinesConfig.ArticlesListConfig -> _state.update {
                        it.copy(
                            title = getString(Res.string.home),
                            showBack = false
                        )
                    }

                    is NavConfig.SourcesConfig.ArticlesListConfig -> _state.update {
                        it.copy(
                            title = navConfig.source.name,
                            showBack = true
                        )
                    }

                    NavConfig.SourcesConfig.SourcesListConfig -> _state.update {
                        it.copy(
                            title = getString(Res.string.sources),
                            showBack = false
                        )
                    }

                    is NavConfig.CategoriesConfig.ArticlesListConfig -> _state.update {
                        it.copy(
                            title = navConfig.category.name,
                            showBack = true
                        )
                    }

                    NavConfig.CategoriesConfig.CategoriesListConfig -> _state.update {
                        it.copy(
                            title = getString(Res.string.categories),
                            showBack = false
                        )
                    }
                }
            }
        }
    }

    fun onBackClicked() {
        navigationByTabMap[tabsStack.active.configuration]?.pop()
    }
}
