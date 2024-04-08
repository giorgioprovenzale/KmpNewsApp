package root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import org.koin.core.component.KoinComponent
import ui.tabs.categories.CategoriesComponent
import ui.tabs.headlines.HeadlinesComponent
import ui.tabs.sources.SourcesComponent

class RootComponent(
    private val componentContext: ComponentContext,
) : KoinComponent, ComponentContext by componentContext {

    var tabsNavigation: StackNavigation<TabsConfig> = StackNavigation()
    var tabsStack: Value<ChildStack<TabsConfig, TabsChild>> = childStack(
        source = tabsNavigation,
        serializer = TabsConfig.serializer(),
        initialConfiguration = TabsConfig.HeadlinesTabConfig,
        handleBackButton = true,
        childFactory = ::tabsChildFactory,
        key = "tabs"
    )

    private fun tabsChildFactory(config: TabsConfig, componentContext: ComponentContext): TabsChild {
        return when (config) {
            is TabsConfig.HeadlinesTabConfig -> TabsChild.Headlines(
                HeadlinesComponent(componentContext)
            )

            is TabsConfig.SourcesTabConfig -> TabsChild.SourcesList(
                SourcesComponent(componentContext)
            )

            TabsConfig.CategoriesTabConfig -> TabsChild.CategoriesList(
                CategoriesComponent(componentContext)
            )
        }
    }
}
