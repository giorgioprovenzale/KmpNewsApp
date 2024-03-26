package root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import ui.news.detail.DefaultDetailComponent
import domain.DomainComponent
import ui.news.list.DefaultListComponent
import list2.DefaultListComponent2

class DefaultRootComponent(
    private val componentContext: ComponentContext,
    private val domainComponent: DomainComponent,
) : RootComponent, ComponentContext by componentContext {

    private val homeTabNavigation = StackNavigation<HomeTabConfigs>()
    private val sourcesTabNavigation = StackNavigation<SourcesTabConfigs>()

    override val homeTabStack: Value<ChildStack<*, Child>> =
        childStack(
            source = homeTabNavigation,
            serializer = HomeTabConfigs.serializer(),
            initialConfiguration = HomeTabConfigs.NewsListConfig,
            handleBackButton = true,
            childFactory = ::homeTabChildFactory,
            key = "home"
        )

    override val sourcesTabStack: Value<ChildStack<*, Child>> =
        childStack(
            source = sourcesTabNavigation,
            serializer = SourcesTabConfigs.serializer(),
            initialConfiguration = SourcesTabConfigs.SourcesListConfig,
            handleBackButton = true,
            childFactory = ::sourcesTabChildFactory,
            key = "profile"
        )

    private fun homeTabChildFactory(config: HomeTabConfigs, componentContext: ComponentContext): Child {
        return when (config) {
            is HomeTabConfigs.NewsListConfig -> Child.NewsList(
                DefaultListComponent(componentContext, domainComponent.homeRepository) { item ->
                    homeTabNavigation.push(HomeTabConfigs.NewsDetailsConfig(item))
                    //it will change the content to Detail
                }
            )

            is HomeTabConfigs.NewsDetailsConfig -> Child.NewsDetails(
                DefaultDetailComponent(componentContext, config.item) {
                    onBackClicked()
                }
            )
        }
    }

    private fun sourcesTabChildFactory(config: SourcesTabConfigs, componentContext: ComponentContext): Child {
        return when (config) {
            is SourcesTabConfigs.SourcesListConfig -> Child.SourcesList(
                DefaultListComponent2(componentContext, domainComponent.homeRepository) { item ->

                }
            )
        }
    }

    override fun onBackClicked() {
        homeTabNavigation.pop()
    }
}
