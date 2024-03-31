package ui.tabs.sources

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class SourcesComponent(
    private val componentContext: ComponentContext,
) : ComponentContext by componentContext {

    private val _model = MutableValue<String>("")
    val model: Value<String> = _model


}