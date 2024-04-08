package ui.articles.details

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import domain.models.Article

class ArticleDetailsComponent(
    private val componentContext: ComponentContext,
    item: Article,
) : ComponentContext by componentContext {

    private val _state = MutableValue(ArticleDetailsState(item = item))
    val state: Value<ArticleDetailsState> = _state
}