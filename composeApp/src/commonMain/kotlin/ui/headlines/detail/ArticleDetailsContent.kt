package ui.headlines.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import ui.shared.ArticleItem

@Composable
fun DetailContent(
    component: ArticleDetailsComponent
) {

    val state = component.state.subscribeAsState()

    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp),
    ) {

        ArticleItem(state.value.item) {}
    }
}