package root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import domain.DomainComponent
import ui.tabs.categories.CategoriesComponent
import ui.tabs.headlines.HeadlinesComponent
import ui.tabs.sources.SourcesComponent

class RootComponent(
    private val componentContext: ComponentContext,
    private val domainComponent: DomainComponent,
) : ComponentContext by componentContext {

    private var tabsNavigation: StackNavigation<TabsConfig> = StackNavigation()
    private var tabsStack: Value<ChildStack<TabsConfig, TabsChild>>

    private val _state = MutableValue(RootState())
    val state: Value<RootState> = _state

    init {
        tabsStack = childStack(
            source = tabsNavigation,
            serializer = TabsConfig.serializer(),
            initialConfiguration = TabsConfig.HeadlinesConfig,
            handleBackButton = true,
            childFactory = ::tabsChildFactory,
            key = "tabs"
        ).apply {
            observe { stack ->
                _state.update {
                    it.copy(
                        selectedTab = when (stack.active.instance) {
                            is TabsChild.CategoriesList -> "category"
                            is TabsChild.Headlines -> "home"
                            is TabsChild.SourcesList -> "source"
                        },
                        stack = stack
                    )
                }
            }
        }
    }

    private fun tabsChildFactory(config: TabsConfig, componentContext: ComponentContext): TabsChild {
        return when (config) {
            is TabsConfig.HeadlinesConfig -> TabsChild.Headlines(
                HeadlinesComponent(componentContext, domainComponent.articlesRepository) { item ->
                    // open details
                }
            )

            is TabsConfig.SourcesConfig -> TabsChild.SourcesList(
                SourcesComponent(componentContext)
            )

            TabsConfig.CategoriesConfig -> TabsChild.CategoriesList(
                CategoriesComponent(componentContext)
            )
        }
    }

    fun onTabChange(tab: TabsConfig) {
        tabsNavigation.bringToFront(tab)
    }
}
