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
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import list2.ListContent2
import ui.headlines.detail.DetailContent
import ui.headlines.list.HeadlinesContent

@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier
) {
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
                        title = { Text(text = state.value.selectedTab) },
                        navigationIcon = if (state.value.showBack) {
                            {
                                IconButton(
                                    onClick = { component.onBackClicked() },
                                    content = { Icon(Icons.Default.ArrowBack, contentDescription = "back") })
                            }
                        } else null
                    )
                },
                bottomBar = {
                    BottomNavigation(

                    ) {
                        BottomNavigationItem(
                            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                            onClick = { component.onTabChange("home") },
                            selected = state.value.selectedTab == "home"

                        )
                        BottomNavigationItem(
                            icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Source") },
                            onClick = { component.onTabChange("source") },
                            selected = state.value.selectedTab == "source"
                        )
                    }
                }
            ) {

                Box(
                    modifier = Modifier.fillMaxSize().background(Color.LightGray)
                ) {
                    Children(
                        stack = state.value.stack,
                        modifier = modifier,
                        animation = stackAnimation(fade())
                    ) {
                        when (val child = it.instance) {
                            is Child.HomeChild.HeadlinesList -> HeadlinesContent(child.component)
                            is Child.HomeChild.NewsDetails -> DetailContent(child.component)
                            is Child.SourcesChild.SourcesList -> ListContent2(child.component)
                        }
                    }
                }
            }
        }

    }


}

