package ui.articles.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import domain.models.Article
import ui.shared.ArticleItem
import ui.shared.ArticlesList

@Composable
fun ArticlesListContent(
    component: ArticlesListComponent
) {
    val state = component.state.subscribeAsState()

    ArticlesList(
        articles = state.value.articles,
        onItemClicked = { component.onItemClicked(it) })
}
