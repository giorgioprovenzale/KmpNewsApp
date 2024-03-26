package ui.news.list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import data.Product
import domain.HomeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsListComponent(
    private val componentContext: ComponentContext,
    private val homeRepository: HomeRepository,
    private val onItemSelected: (item: Product) -> Unit
) : ComponentContext by componentContext {

    private val _state = MutableValue<NewsListState>(NewsListState(items = emptyList()))
    val state: Value<NewsListState> = _state
    fun onItemClicked(item: Product) {
        onItemSelected(item)
    }

    init {
        CoroutineScope(Dispatchers.Default).launch {
            homeRepository.getProducts().collect {
                _state.value = NewsListState(items = it)
            }
        }
    }
}
