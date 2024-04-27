package ui.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import domain.models.Article
import theme.spacing_1x
import theme.spacing_2x

@Composable
fun ArticleItem(
    article: Article,
    modifier: Modifier = Modifier,
    onItemClicked: (Article) -> Unit
) {
    Card(
        modifier = modifier
            .padding(spacing_1x)
            .fillMaxWidth()
            .clickable {
                onItemClicked(article)
            },
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            article.urlToImage?.let {
                Image(
                    painter = rememberImagePainter(url = it),
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    contentDescription = article.title,
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = article.title.orEmpty(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(all = spacing_2x)
            )
        }
    }
}