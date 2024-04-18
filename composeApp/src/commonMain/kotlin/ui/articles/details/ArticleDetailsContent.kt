package ui.articles.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import theme.spacing_2x
import ui.shared.ArticleDetails
import ui.shared.ArticleItem

@Composable
fun ArticleDetailsContent(
    component: ArticleDetailsComponent
) {

    val state = component.state.subscribeAsState()

    Box(
        modifier = Modifier.fillMaxSize().padding(spacing_2x),
    ) {

        ArticleDetails(state.value.item)
    }
}