package ui.articles.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import kmpnewsapp.composeapp.generated.resources.Res
import kmpnewsapp.composeapp.generated.resources.ic_arrow_back_24
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.vectorResource
import ui.shared.ArticleDetails

@ExperimentalMaterial3Api
@ExperimentalResourceApi
@Composable
fun ArticleDetailsContent(
    component: ArticleDetailsComponent
) {

    val state = component.state.subscribeAsState()
    val article = state.value.item

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = article.title.orEmpty(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }, navigationIcon = {
            IconButton(onClick = { component.onBackClicked() }) {
                Icon(
                    imageVector = vectorResource(Res.drawable.ic_arrow_back_24),
                    contentDescription = "back",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }, colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        )
        )
    }) { paddings ->
        Box(
            modifier = Modifier.padding(paddings).fillMaxSize().background(MaterialTheme.colorScheme.background)
        ) {
            ArticleDetails(state.value.item)
        }
    }
}