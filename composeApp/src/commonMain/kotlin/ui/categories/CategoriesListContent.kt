package ui.categories

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

@Composable
fun CategoriesListContent(
    component: CategoriesListComponent
) {
    val state = component.state.subscribeAsState()

    CategoriesList(
        categories = state.value.categories,
        onItemClicked = { component.onItemClicked(it) })
}
