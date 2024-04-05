package ui.tabs.headlines

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import domain.models.Article
import domain.repositories.ArticlesRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HeadlinesComponent(
    private val componentContext: ComponentContext,
    private val onItemSelected: (item: Article) -> Unit
) : KoinComponent, ComponentContext by componentContext {

    private val articlesRepository: ArticlesRepository by inject()

    private val _state = MutableValue<HeadlinesState>(HeadlinesState(articles = emptyList()))
    val state: Value<HeadlinesState> = _state
    fun onItemClicked(item: Article) {
        onItemSelected(item)
    }

    private val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }

    init {
        CoroutineScope(Dispatchers.Default).launch(handler) {
            val articles = articlesRepository.getHeadlines()
            _state.value = HeadlinesState(articles = articles)
        }
    }
}
