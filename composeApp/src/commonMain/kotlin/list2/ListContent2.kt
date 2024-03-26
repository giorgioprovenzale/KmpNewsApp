package list2

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

@Composable
fun ListContent2(
    component: ListComponent2
) {
    val products = component.model.subscribeAsState()

    Text("ListContent2")

}