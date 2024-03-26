package ui.news.list

import AppContent
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

@Composable
fun NewsListContent(
    component: NewsListComponent
) {
    val products = component.state.subscribeAsState()

    AppContent(products) {
        component.onItemClicked(it)
    }

}