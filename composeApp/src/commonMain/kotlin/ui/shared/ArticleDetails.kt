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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import domain.models.Article
import theme.spacing_2x

@Composable
fun ArticleDetails(
    article: Article,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = article.title.orEmpty(),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(all = spacing_2x)
        )
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
        Text(
            text = article.description.orEmpty(),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(all = spacing_2x)
        )
        Text(
            text = article.content.orEmpty(),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(all = spacing_2x)
        )
        Spacer(modifier = Modifier.height(100.dp))
    }
}