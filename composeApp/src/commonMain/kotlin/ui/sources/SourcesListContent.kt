package ui.sources

import androidx.compose.runtime.Composable
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
