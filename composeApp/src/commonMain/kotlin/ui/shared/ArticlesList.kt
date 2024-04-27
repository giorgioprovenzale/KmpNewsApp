package ui.shared

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import domain.models.Article

@Composable
fun ArticlesList(
    articles: List<Article>,
    onItemClicked: (Article) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {

        items(articles) { article ->
            ArticleItem(
                article = article,
                onItemClicked = onItemClicked
            )
        }
    }
}