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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import detail.DetailContent
import list.ListContent
import list2.ListContent2

@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier
) {

    val selectedTab = remember { mutableStateOf("home") }

    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primary)
                .windowInsetsPadding(WindowInsets.safeDrawing)
        ) {
            Scaffold(
                topBar = { TopAppBar(title = { Text(text = selectedTab.value) }) },
                bottomBar = {
                    BottomNavigation(

                    ) {
                        BottomNavigationItem(
                            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                            onClick = { selectedTab.value = "home" },
                            selected = selectedTab.value == "home"

                        )
                        BottomNavigationItem(
                            icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Profile") },
                            onClick = { selectedTab.value = "profile" },
                            selected = selectedTab.value == "profile"
                        )
                    }
                }
            ) {

                val stack = if (selectedTab.value == "home") component.tab1Stack else component.tab2Stack
                Children(
                    stack = stack,
                    modifier = modifier,
                    animation = stackAnimation(fade())
                ) {
                    when (val child = it.instance) {
                        is RootComponent.Child.ListChild -> ListContent(child.component)
                        is RootComponent.Child.ListChild2 -> ListContent2(child.component)
                        is RootComponent.Child.DetailChild -> DetailContent(child.component)
                    }
                }
            }
        }

    }


}

