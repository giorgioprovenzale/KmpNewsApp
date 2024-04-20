package ui.articles.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import ui.shared.ArticlesList

@Composable
fun ArticlesListContent(
    component: ArticlesListComponent
) {
    val state = component.state.subscribeAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ArticlesList(
            articles = state.value.articles,
            onItemClicked = { component.onItemClicked(it) })
    }
}
