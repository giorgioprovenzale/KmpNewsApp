package ui.sources

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

@Composable
fun SourcesListContent(
    component: SourcesListComponent
) {
    val state = component.state.subscribeAsState()

    SourcesList(
        sources = state.value.sources,
        onItemClicked = { component.onItemClicked(it) })
}
