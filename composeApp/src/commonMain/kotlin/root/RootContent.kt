package root

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import ui.tabs.categories.CategoriesContent
import ui.tabs.headlines.HeadlinesContent
import ui.tabs.sources.SourcesContent

@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier
) {
    val state = component.state.subscribeAsState()

    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primary)
                .windowInsetsPadding(WindowInsets.safeDrawing)
        ) {
            Scaffold(
                bottomBar = {
                    BottomNavigation(
                    ) {
                        tabItems().forEach {
                            BottomNavigationItem(
                                icon = { Icon(it.icon, contentDescription = it.label) },
                                label = { Text(text = it.label) },
                                alwaysShowLabel = false,
                                onClick = { component.onTabChange(it.type) },
                                selected = state.value.stack?.active?.configuration == it.type
                            )
                        }
                    }
                }
            ) {

                Box(
                    modifier = Modifier.fillMaxSize().background(Color.LightGray)
                ) {
                    state.value.stack?.let { stack ->
                        Children(
                            stack = stack,
                            modifier = modifier,
                            animation = stackAnimation(fade())
                        ) {
                            when (val child = it.instance) {
                                is TabsChild.CategoriesList -> CategoriesContent(child.component)
                                is TabsChild.Headlines -> HeadlinesContent(child.component)
                                is TabsChild.SourcesList -> SourcesContent(child.component)
                            }
                        }
                    }
                }
            }
        }

    }
}

