package root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import domain.DomainComponent
import list2.DefaultListComponent2
import ui.headlines.detail.ArticleDetailsComponent
import ui.headlines.list.HeadlinesListComponent

class RootComponent(
    private val componentContext: ComponentContext,
    private val domainComponent: DomainComponent,
) : ComponentContext by componentContext {

    private val homeTabNavigation = StackNavigation<HomeTabConfigs>()
    private val sourcesTabNavigation = StackNavigation<SourcesTabConfigs>()

    val homeTabStack: Value<ChildStack<*, Child>> =
        childStack(
            source = homeTabNavigation,
            serializer = HomeTabConfigs.serializer(),
            initialConfiguration = HomeTabConfigs.HeadlinesListConfig,
            handleBackButton = true,
            childFactory = ::homeTabChildFactory,
            key = "home"
        )

    val sourcesTabStack: Value<ChildStack<*, Child>> =
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
            is HomeTabConfigs.HeadlinesListConfig -> Child.HeadlinesList(
                HeadlinesListComponent(componentContext, domainComponent.articlesRepository) { item ->
                    homeTabNavigation.push(HomeTabConfigs.ArticleDetailsConfig(item))
                }
            )

            is HomeTabConfigs.ArticleDetailsConfig -> Child.NewsDetails(
                ArticleDetailsComponent(componentContext, config.item) {
                    homeTabNavigation.pop()
                }
            )
        }
    }

    private fun sourcesTabChildFactory(config: SourcesTabConfigs, componentContext: ComponentContext): Child {
        return when (config) {
            is SourcesTabConfigs.SourcesListConfig -> Child.SourcesList(
                DefaultListComponent2(componentContext)
            )
        }
    }

    fun onBackClicked() {
        homeTabNavigation.pop()
    }
}
