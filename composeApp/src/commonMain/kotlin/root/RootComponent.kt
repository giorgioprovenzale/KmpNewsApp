package root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import data.Product
import detail.DefaultDetailComponent
import detail.DetailComponent
import domain.DomainComponent
import kotlinx.serialization.Serializable
import list.DefaultListComponent
import list.ListComponent
import list2.DefaultListComponent2
import list2.ListComponent2

interface RootComponent {

    val tab1Stack: Value<ChildStack<*, Child>>
    val tab2Stack: Value<ChildStack<*, Child>>

    fun onBackClicked()

    sealed class Child {
        class ListChild(val component: ListComponent) : Child()

        class ListChild2(val component: ListComponent2) : Child()

        class DetailChild(val component: DetailComponent) : Child()
    }
}

//Component -> Config(pushing in navigation and passing data) -> Deciding the Child(childFactory) -> Deciding The UI(RootContent)

class DefaultRootComponent(
    private val componentContext: ComponentContext,
    private val domainComponent: DomainComponent,
) : RootComponent, ComponentContext by componentContext {

    private val navigation1 = StackNavigation<Config1>()
    private val navigation2 = StackNavigation<Config2>()

    override val tab1Stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation1,
            serializer = Config1.serializer(),
            initialConfiguration = Config1.List,
            handleBackButton = true,
            childFactory = ::child1Factory,
            key = "home"
        )

    override val tab2Stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation2,
            serializer = Config2.serializer(),
            initialConfiguration = Config2.List,
            handleBackButton = true,
            childFactory = ::child2Factory,
            key = "profile"
        )

    private fun child1Factory(config: Config1, componentContext: ComponentContext): RootComponent.Child {
        return when (config) {
            is Config1.List -> RootComponent.Child.ListChild(
                DefaultListComponent(componentContext, domainComponent.homeRepository) { item ->
                    navigation1.push(Config1.Detail(item))
                    //it will change the content to Detail
                }
            )

            is Config1.Detail -> RootComponent.Child.DetailChild(
                DefaultDetailComponent(componentContext, config.item) {
                    onBackClicked()
                }
            )
        }
    }

    private fun child2Factory(config: Config2, componentContext: ComponentContext): RootComponent.Child {
        return when (config) {
            is Config2.List -> RootComponent.Child.ListChild2(
                DefaultListComponent2(componentContext, domainComponent.homeRepository) { item ->

                }
            )
        }
    }

    override fun onBackClicked() {
        navigation1.pop()
    }

    @Serializable
    sealed interface Config1 {

        @Serializable
        data object List : Config1

        @Serializable
        data class Detail(val item: Product) : Config1
    }

    @Serializable
    sealed interface Config2 {

        @Serializable
        data object List : Config2
    }


}