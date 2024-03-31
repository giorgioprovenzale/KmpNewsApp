package ui.tabs.sources

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

@Composable
fun SourcesContent(
    component: SourcesComponent
) {
    val products = component.model.subscribeAsState()

    Text("Sources")

}