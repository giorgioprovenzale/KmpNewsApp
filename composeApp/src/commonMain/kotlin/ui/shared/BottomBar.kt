package ui.shared

import TabsChild
import TabsConfig
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.router.stack.ChildStack
import extensions.capitalized
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.vectorResource
import ui.tabs.GetTabTitleByKey
import ui.tabs.TabItem
import ui.tabs.tabItems

@ExperimentalResourceApi
@Composable
fun BottomBar(
    stack: ChildStack<TabsConfig, TabsChild>,
    onTabItemClicked: (TabItem) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        tabItems().forEach {
            NavigationBarItem(
                icon = {
                    Icon(
                        vectorResource(it.icon),
                        contentDescription = GetTabTitleByKey(it.key),
                        tint = if (stack.active.configuration == it.type) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onPrimary
                    )
                },
                label = { Text(text = GetTabTitleByKey(it.key).capitalized()) },
                onClick = { onTabItemClicked(it) },
                selected = stack.active.configuration == it.type,
                colors = NavigationBarItemDefaults.colors().copy(
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    selectedIndicatorColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    }
}