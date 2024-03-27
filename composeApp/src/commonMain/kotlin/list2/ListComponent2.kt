package list2

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import source.data.Product
import domain.repositories.HomeRepository

interface ListComponent2 {
    val model: Value<Model>

    data class Model(
        val items: List<Product>
    )
}

class DefaultListComponent2(
    private val componentContext: ComponentContext,
    private val homeRepository: HomeRepository,
    private val onItemSelected: (item: Product) -> Unit
) : ListComponent2, ComponentContext by componentContext {

    private val _model = MutableValue<ListComponent2.Model>(ListComponent2.Model(items = emptyList()))
    override val model: Value<ListComponent2.Model> = _model


}