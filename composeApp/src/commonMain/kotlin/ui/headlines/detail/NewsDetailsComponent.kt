package ui.headlines.detail

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import source.data.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsDetailsComponent(
    private val componentContext: ComponentContext,
    private val item: Product,
    private val onBack: () -> Unit,
) : ComponentContext by componentContext {

    private val _state = MutableValue(NewsDetailsState(item = item))
    val state: Value<NewsDetailsState> = _state
    fun onBackPressed() {
        onBack()
    }

    init {
        CoroutineScope(Dispatchers.Default).launch {
            //IF we need to call api in Detail Component, call here
        }
    }


}