package root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import domain.DomainComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import list2.DefaultListComponent2
import ui.headlines.detail.ArticleDetailsComponent
import ui.headlines.list.HeadlinesListComponent

class RootComponent(
    private val componentContext: ComponentContext,
    private val domainComponent: DomainComponent,
) : ComponentContext by componentContext {

    private val homeTabNavigation = StackNavigation<HomeTabConfigs>()
    private val sourcesTabNavigation = StackNavigation<SourcesTabConfigs>()

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
            stack = homeTabStack,
            showBack = false
        )

    private val _state = MutableStateFlow<RootState>(initialState)
    val state: StateFlow<RootState> = _state

    init {
        homeTabStack.observe { stack ->
            _state.update {
                it.copy(
                    showBack = stack.active.instance is Child.HomeChild.NewsDetails
                )
            }
        }
    }

    private fun homeTabChildFactory(config: HomeTabConfigs, componentContext: ComponentContext): Child.HomeChild {
        return when (config) {
            is HomeTabConfigs.HeadlinesListConfig -> Child.HomeChild.HeadlinesList(
                HeadlinesListComponent(componentContext, domainComponent.articlesRepository) { item ->
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
                DefaultListComponent2(componentContext)
            )
        }
    }

    fun onTabChange(tab: String) {
        val stack = if (tab == "home") homeTabStack else sourcesTabStack
        _state.update {
            it.copy(
                selectedTab = tab,
                stack = stack,
                showBack = stack.value.active.instance is Child.HomeChild.NewsDetails
            )
        }
    }

    fun onBackClicked() {
        if (state.value.stack == homeTabStack && state.value.stack.value.backStack.isNotEmpty()) {
            homeTabNavigation.pop()
        }
        if (state.value.stack == sourcesTabStack && state.value.stack.value.backStack.isNotEmpty()) {
            sourcesTabNavigation.pop()
        }
        _state.update {
            it.copy(
                showBack = it.stack.value.active.instance is Child.HomeChild.NewsDetails
            )
        }
    }
}
