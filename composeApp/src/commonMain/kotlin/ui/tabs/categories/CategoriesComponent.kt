package ui.tabs.categories

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class CategoriesComponent(
    private val componentContext: ComponentContext,
) : ComponentContext by componentContext {

    private val _model = MutableValue<String>("")
    val model: Value<String> = _model


}