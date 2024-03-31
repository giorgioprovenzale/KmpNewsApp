package ui.tabs.headlines

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

@Composable
fun HeadlinesContent(
    component: HeadlinesComponent
) {
    val state = component.state.subscribeAsState()

    ArticlesList(
        articles = state.value.articles,
        onItemClicked = { component.onItemClicked(it) })
}

@Composable
fun ArticlesList(articles: List<Article>, onItemClicked: (Article) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 64.dp)
    ) {

        items(articles) { article ->
            ArticleItem(
                article = article,
                onItemClicked = onItemClicked
            )
        }
    }
}
