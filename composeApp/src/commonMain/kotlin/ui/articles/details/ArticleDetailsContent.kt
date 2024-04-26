package ui.articles.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import ui.shared.ArticleDetails

@Composable
fun ArticleDetailsContent(
    component: ArticleDetailsComponent
) {

    val state = component.state.subscribeAsState()
    val article = state.value.item

    Box(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
    ) {
        ArticleDetails(state.value.item)
    }
}