package ui.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
fun ArticleDetails(article: Article) {
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.padding(spacing_1x).fillMaxWidth(),
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
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = article.title.orEmpty(),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h6,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(all = spacing_2x)
                )
                Text(
                    text = article.description.orEmpty(),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(all = spacing_2x)
                )
                Text(
                    text = article.content.orEmpty(),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(all = spacing_2x)
                )
            }
        }
    }
}