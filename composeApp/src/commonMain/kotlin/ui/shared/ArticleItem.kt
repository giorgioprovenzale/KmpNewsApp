package ui.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun ArticleItem(article: Article, onItemClicked: (Article) -> Unit) {
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.padding(spacing_1x).fillMaxWidth()
            .clickable {
                onItemClicked(article)
            },
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
                    style = MaterialTheme.typography.headlineSmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(all = spacing_2x)
                )
            }
        }
    }
}