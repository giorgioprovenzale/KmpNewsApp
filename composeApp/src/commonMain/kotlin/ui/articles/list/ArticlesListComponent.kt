package ui.articles.list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import domain.models.Article
import domain.models.Category
import domain.models.Source
import domain.repositories.ArticlesRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ArticlesListComponent(
    private val componentContext: ComponentContext,
    private val source: Source? = null,
    private val category: Category? = null,
    private val onArticleSelected: ((Article) -> Unit)? = null,
    private val onCategorySelected: ((Category) -> Unit)? = null,
    private val onBack: () -> Unit,
) : KoinComponent, ComponentContext by componentContext {

    private val articlesRepository: ArticlesRepository by inject()

    private val _state = MutableValue(ArticlesState(articles = emptyList(), topBarTitle = null, showBackButton = source != null))
    val state: Value<ArticlesState> = _state

    private val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }

    init {
        CoroutineScope(Dispatchers.Default).launch(handler) {
            val articles = when {
                source != null -> articlesRepository.getHeadlinesBySource(source)
                category != null -> articlesRepository.getHeadlinesByCategory(category)
                else -> articlesRepository.getHeadlines()
            }
            _state.value =
                ArticlesState(articles = articles, topBarTitle = getTopBarTitle(), showBackButton = source != null || category != null)
        }
    }

    fun onItemClicked(item: Article) {
        onArticleSelected?.invoke(item)
    }

    fun onItemClicked(item: Category) {
        onCategorySelected?.invoke(item)
    }

    fun onBackClicked() {
        onBack()
    }

    private fun getTopBarTitle(): String = when {
        source != null -> source.name
        category != null -> category.name
        else -> "Home"
    }
}
