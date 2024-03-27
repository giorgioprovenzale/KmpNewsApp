package list2

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

interface ListComponent2 {
    val model: Value<String>


}

class DefaultListComponent2(
    private val componentContext: ComponentContext,
) : ListComponent2, ComponentContext by componentContext {

    private val _model = MutableValue<String>("")
    override val model: Value<String> = _model


}