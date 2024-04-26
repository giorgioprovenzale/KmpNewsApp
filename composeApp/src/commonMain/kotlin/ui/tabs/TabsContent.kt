package ui.tabs

import TabsChild
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.router.stack.bringToFront
import extensions.capitalized
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.vectorResource
import ui.shared.CenteredTopBar
import ui.tabs.categories.CategoriesContent
import ui.tabs.headlines.HeadlinesContent
import ui.tabs.sources.SourcesContent

@ExperimentalMaterial3Api
@ExperimentalResourceApi
@ExperimentalDecomposeApi
@Composable
fun TabsContent(
    component: TabsComponent
) {

    val stack = component.tabsStack.subscribeAsState()
    val state = component.state.collectAsState()

    Scaffold(topBar = {
        CenteredTopBar(
            title = state.value.title,
            showBack = state.value.showBack,
            onBackClicked = { component.onBackClicked() }
        )
    }, bottomBar = {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            tabItems().forEach {
                NavigationBarItem(
                    icon = {
                        Icon(
                            vectorResource(it.icon),
                            contentDescription = GetTabTitleByKey(it.key),
                            tint = if (stack.value.active.configuration == it.type) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    label = { Text(text = GetTabTitleByKey(it.key).capitalized()) },
                    onClick = { component.tabsNavigation.bringToFront(it.type) },
                    selected = stack.value.active.configuration == it.type,
                    colors = NavigationBarItemDefaults.colors().copy(
                        unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                        selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                        selectedIndicatorColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        }
    }) { paddings ->
        Box(
            modifier = Modifier.padding(paddings).fillMaxSize().background(Color.LightGray)
        ) {
            Children(
                stack = stack.value, animation = stackAnimation(fade())
            ) { activeStackItem ->
                when (val child = activeStackItem.instance) {
                    is TabsChild.CategoriesList -> CategoriesContent(child.component)
                    is TabsChild.Headlines -> HeadlinesContent(child.component)
                    is TabsChild.SourcesList -> SourcesContent(child.component)
                }
            }

        }
    }
}

