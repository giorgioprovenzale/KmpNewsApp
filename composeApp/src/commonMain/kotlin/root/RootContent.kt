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
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
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
@OptIn(ExperimentalResourceApi::class)
@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier
) {
    val stack = component.tabsStack.subscribeAsState()
    val state = component.state.collectAsState()

    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primary)
                .windowInsetsPadding(WindowInsets.safeDrawing)
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = state.value.title,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        navigationIcon = if (state.value.showBack) {
                            @Composable {
                                IconButton(onClick = { component.onBackClicked() }) {
                                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
                                }
                            }
                        } else null

                    )
                },
                bottomBar = {
                    BottomNavigation(
                    ) {
                        tabItems().forEach {
                            BottomNavigationItem(
                                icon = { Icon(it.icon, contentDescription = GetTabTitleByKey(it.key)) },
                                label = { Text(text = GetTabTitleByKey(it.key)) },
                                alwaysShowLabel = false,
                                onClick = { component.tabsNavigation.bringToFront(it.type) },
                                selected = stack.value.active.configuration == it.type,
                                selectedContentColor = Color.White,
                                unselectedContentColor = Color.White.copy(alpha = ContentAlpha.medium)
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

