package ui.headlines.detail

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import domain.models.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleDetailsComponent(
    private val componentContext: ComponentContext,
    private val item: Article,
    private val onBack: () -> Unit,
) : ComponentContext by componentContext {

    private val _state = MutableValue(ArticleDetailsState(item = item))
    val state: Value<ArticleDetailsState> = _state
    fun onBackPressed() {
        onBack()
    }
}