package ui.headlines.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.seiko.imageloader.rememberImagePainter
import domain.models.Article

@Composable
fun HeadlinesContent(
    component: HeadlinesListComponent
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

@Composable
fun ArticleItem(article: Article, onItemClicked: (Article) -> Unit) {
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.padding(8.dp).fillMaxWidth().clickable {
            onItemClicked(article)
        },
        elevation = 2.dp
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            article.urlToImage?.let {
                val painter = rememberImagePainter(url = it)
                Image(
                    painter,
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    contentDescription = article.title,
                    contentScale = ContentScale.Crop
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    article.title.orEmpty(),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h6,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(all = 16.dp)
                )
            }
        }
    }
}