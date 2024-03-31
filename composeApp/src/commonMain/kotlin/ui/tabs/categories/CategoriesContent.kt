package ui.tabs.categories

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

@Composable
fun CategoriesContent(
    component: CategoriesComponent
) {
    val products = component.model.subscribeAsState()

    Text("Categories")

}