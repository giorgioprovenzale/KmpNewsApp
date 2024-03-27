package ui.headlines.list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import domain.models.Article
import domain.repositories.ArticlesRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HeadlinesListComponent(
    private val componentContext: ComponentContext,
    private val articlesRepository: ArticlesRepository,
    private val onItemSelected: (item: Article) -> Unit
) : ComponentContext by componentContext {

    private val _state = MutableValue<HeadlinesListState>(HeadlinesListState(articles = emptyList()))
    val state: Value<HeadlinesListState> = _state
    fun onItemClicked(item: Article) {
        onItemSelected(item)
    }

    private val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }

    init {
        CoroutineScope(Dispatchers.Default).launch(handler) {
            val articles = articlesRepository.getHeadlines()
            _state.value = HeadlinesListState(articles = articles)
        }
    }
}
