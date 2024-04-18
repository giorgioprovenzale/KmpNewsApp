@file:OptIn(ExperimentalResourceApi::class)

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.router.stack.bringToFront
import org.jetbrains.compose.resources.ExperimentalResourceApi
import ui.tabs.categories.CategoriesContent
import ui.tabs.headlines.HeadlinesContent
import ui.tabs.sources.SourcesContent

@ExperimentalDecomposeApi
@ExperimentalMaterial3Api
@OptIn(ExperimentalResourceApi::class)
@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier
) {
    val stack = component.tabsStack.subscribeAsState()

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
                                icon = { Icon(it.icon, contentDescription = GetTabTitleByKey(it.key)) },
                                label = { Text(text = GetTabTitleByKey(it.key)) },
                                alwaysShowLabel = false,
                                onClick = { component.tabsNavigation.bringToFront(it.type) },
                                selected = stack.value.active.configuration == it.type
                            )
                        }
                    }
                }
            ) {
                Box(
                    modifier = Modifier.fillMaxSize().background(Color.LightGray)
                ) {
                    Children(
                        stack = stack.value,
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

