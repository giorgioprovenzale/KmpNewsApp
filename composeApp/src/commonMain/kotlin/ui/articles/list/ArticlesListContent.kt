package ui.articles.list

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
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
