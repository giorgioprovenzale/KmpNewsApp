package root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popWhile
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import domain.DomainComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ui.headlines.detail.ArticleDetailsComponent
import ui.tabs.categories.CategoriesComponent
import ui.tabs.headlines.HeadlinesComponent
import ui.tabs.sources.SourcesComponent

class RootComponent(
    private val componentContext: ComponentContext,
    private val domainComponent: DomainComponent,
) : ComponentContext by componentContext {

    private val homeTabNavigation = StackNavigation<HomeTabConfigs>()
    private val sourcesTabNavigation = StackNavigation<SourcesTabConfigs>()

    private val tabsNavigation = StackNavigation<TabsConfigs>()

    val tabsStack: Value<ChildStack<TabsConfigs, Child.TabsChild>> =
        childStack(
            source = tabsNavigation,
            serializer = TabsConfigs.serializer(),
            initialConfiguration = TabsConfigs.HeadlinesConfig,
            childFactory = ::tabsChildFactory,
            key = "tabs"
        )

    private val homeTabStack: Value<ChildStack<HomeTabConfigs, Child.HomeChild>> =
        childStack(
            source = homeTabNavigation,
            serializer = HomeTabConfigs.serializer(),
            initialConfiguration = HomeTabConfigs.HeadlinesListConfig,
            handleBackButton = true,
            childFactory = ::homeTabChildFactory,
            key = "home"
        )

    private val sourcesTabStack: Value<ChildStack<SourcesTabConfigs, Child.SourcesChild>> =
        childStack(
            source = sourcesTabNavigation,
            serializer = SourcesTabConfigs.serializer(),
            initialConfiguration = SourcesTabConfigs.SourcesListConfig,
            handleBackButton = true,
            childFactory = ::sourcesTabChildFactory,
            key = "profile"
        )
    private val initialState: RootState
        get() = RootState(
            selectedTab = "home",
        )

    private val _state = MutableStateFlow<RootState>(initialState)
    val state: StateFlow<RootState> = _state

    init {
        tabsStack.observe { stack ->
            _state.update {
                it.copy(
                    selectedTab = when (stack.active.instance) {
                        is Child.TabsChild.CategoriesList -> "category"
                        is Child.TabsChild.Headlines -> "home"
                        is Child.TabsChild.SourcesList -> "source"
                    }
                )
            }
        }
    }

    private fun tabsChildFactory(config: TabsConfigs, componentContext: ComponentContext): Child.TabsChild {
        return when (config) {
            is TabsConfigs.HeadlinesConfig -> Child.TabsChild.Headlines(
                HeadlinesComponent(componentContext, domainComponent.articlesRepository) { item ->
                    homeTabNavigation.push(HomeTabConfigs.ArticleDetailsConfig(item))
                }
            )

            is TabsConfigs.SourcesConfig -> Child.TabsChild.SourcesList(
                SourcesComponent(componentContext)
            )

            TabsConfigs.CategoriesConfig -> Child.TabsChild.CategoriesList(
                CategoriesComponent(componentContext)
            )
        }
    }

    private fun homeTabChildFactory(config: HomeTabConfigs, componentContext: ComponentContext): Child.HomeChild {
        return when (config) {
            is HomeTabConfigs.HeadlinesListConfig -> Child.HomeChild.HeadlinesList(
                HeadlinesComponent(componentContext, domainComponent.articlesRepository) { item ->
                    homeTabNavigation.push(HomeTabConfigs.ArticleDetailsConfig(item))
                }
            )

            is HomeTabConfigs.ArticleDetailsConfig -> Child.HomeChild.NewsDetails(
                ArticleDetailsComponent(componentContext, config.item) {
                    homeTabNavigation.pop()
                }
            )
        }
    }

    private fun sourcesTabChildFactory(config: SourcesTabConfigs, componentContext: ComponentContext): Child.SourcesChild {
        return when (config) {
            is SourcesTabConfigs.SourcesListConfig -> Child.SourcesChild.SourcesList(
                SourcesComponent(componentContext)
            )
        }
    }

    fun onTabChange(tab: String) {
        tabsNavigation.bringToFront(
            when (tab) {
                "home" -> TabsConfigs.HeadlinesConfig
                "source" -> TabsConfigs.SourcesConfig
                else -> TabsConfigs.CategoriesConfig
            }
        )
    }

    fun onBackClicked() {
        if (state.value.selectedTab == "home") {
            tabsNavigation.popWhile { it != root.TabsConfigs.HeadlinesConfig }
            tabsNavigation.pop()
        } else {
            tabsNavigation.pop()
        }
    }
}
