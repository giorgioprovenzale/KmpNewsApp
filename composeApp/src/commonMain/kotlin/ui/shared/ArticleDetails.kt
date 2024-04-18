package ui.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import theme.spacing_2x

@Composable
fun ArticleDetails(article: Article) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        article.urlToImage?.let {
            val painter = rememberImagePainter(url = it)
            Image(
                painter,
                modifier = Modifier.fillMaxWidth(),
                contentDescription = article.title,
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.TopStart
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
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}