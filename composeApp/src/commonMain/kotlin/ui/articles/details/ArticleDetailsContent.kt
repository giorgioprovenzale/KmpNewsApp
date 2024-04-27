package ui.articles.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import org.jetbrains.compose.resources.ExperimentalResourceApi
import ui.shared.ArticleDetails
import ui.shared.TopBar

@ExperimentalMaterial3Api
@ExperimentalResourceApi
@Composable
fun ArticleDetailsContent(
    component: ArticleDetailsComponent
) {

    val state = component.state.subscribeAsState()
    val article = state.value.item

    Scaffold(topBar = {
        TopBar(
            title = article.title.orEmpty(),
            showBack = true,
            onBackClicked = { component.onBackClicked() }
        )
    }) { paddings ->
        Box(
            modifier = Modifier.padding(paddings).fillMaxSize().background(MaterialTheme.colorScheme.background)
        ) {
            ArticleDetails(state.value.item)
        }
    }
}