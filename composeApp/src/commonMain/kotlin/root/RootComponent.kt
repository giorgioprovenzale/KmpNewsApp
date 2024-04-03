package root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
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

    private var tabsNavigation: StackNavigation<TabsConfigs> = StackNavigation()
    private var tabsStack: Value<ChildStack<TabsConfigs, Child.TabsChild>>

    private val _state = MutableValue(RootState())
    val state: Value<RootState> = _state

    init {
        tabsStack = childStack(
            source = tabsNavigation,
            serializer = TabsConfigs.serializer(),
            initialConfiguration = TabsConfigs.HeadlinesConfig,
            handleBackButton = true,
            childFactory = ::tabsChildFactory,
            key = "tabs"
        ).apply {
            observe { stack ->
                _state.update {
                    it.copy(
                        selectedTab = when (stack.active.instance) {
                            is Child.TabsChild.CategoriesList -> "category"
                            is Child.TabsChild.Headlines -> "home"
                            is Child.TabsChild.SourcesList -> "source"
                        },
                        stack = stack
                    )
                }
            }
        }
    }

    private fun tabsChildFactory(config: TabsConfigs, componentContext: ComponentContext): Child.TabsChild {
        return when (config) {
            is TabsConfigs.HeadlinesConfig -> Child.TabsChild.Headlines(
                HeadlinesComponent(componentContext, domainComponent.articlesRepository) { item ->
                    // open details
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

    fun onTabChange(tab: String) {
        tabsNavigation.bringToFront(
            when (tab) {
                "home" -> TabsConfigs.HeadlinesConfig
                "source" -> TabsConfigs.SourcesConfig
                else -> TabsConfigs.CategoriesConfig
            }
        )
    }
}