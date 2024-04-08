package ui.articles.list

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
import ui.tabs.headlines.HeadlinesState

class ArticlesListComponent(
    private val componentContext: ComponentContext,
    private val onArticleClicked: (Article) -> Unit,
) : KoinComponent, ComponentContext by componentContext {

    private val articlesRepository: ArticlesRepository by inject()

    private val _state = MutableValue(HeadlinesState(articles = emptyList()))
    val state: Value<HeadlinesState> = _state

    private val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }

    init {
        CoroutineScope(Dispatchers.Default).launch(handler) {
            val articles = articlesRepository.getHeadlines()
            _state.value = HeadlinesState(articles = articles)
        }
    }
    fun onItemClicked(item: Article) {
        onArticleClicked(item)
    }
}