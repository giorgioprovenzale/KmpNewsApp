package root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import domain.repositories.ArticlesRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ui.tabs.categories.CategoriesComponent
import ui.tabs.headlines.HeadlinesComponent
import ui.tabs.sources.SourcesComponent

class RootComponent(
    private val componentContext: ComponentContext,
) : KoinComponent, ComponentContext by componentContext {

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
                        stack = stack
                    )
                }
            }
        }
    }

    private fun tabsChildFactory(config: TabsConfig, componentContext: ComponentContext): TabsChild {
        return when (config) {
            is TabsConfig.HeadlinesConfig -> TabsChild.Headlines(
                HeadlinesComponent(componentContext) { item ->
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
