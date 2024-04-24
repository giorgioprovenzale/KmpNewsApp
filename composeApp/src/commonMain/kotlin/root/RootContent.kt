package root

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import extensions.capitalized
import kmpnewsapp.composeapp.generated.resources.Res
import kmpnewsapp.composeapp.generated.resources.ic_arrow_back_24px
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.vectorResource
import theme.AppTheme
import ui.tabs.categories.CategoriesContent
import ui.tabs.headlines.HeadlinesContent
import ui.tabs.sources.SourcesContent

@ExperimentalDecomposeApi
@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier
) {
    val stack = component.tabsStack.subscribeAsState()
    val state = component.state.collectAsState()

    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
                .windowInsetsPadding(WindowInsets.safeDrawing)
        ) {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = state.value.title.capitalized(),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        },
                        navigationIcon = {
                            if (state.value.showBack) {
                                IconButton(onClick = { component.onBackClicked() }) {
                                    Icon(
                                        imageVector = vectorResource(Res.drawable.ic_arrow_back_24px),
                                        contentDescription = "back",
                                        tint = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors().copy(
                            containerColor = MaterialTheme.colorScheme.primary,
                            titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        )
                    )
                },
                bottomBar = {
                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.primary
                    ) {
                        tabItems().forEach {
                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        it.icon,
                                        contentDescription = GetTabTitleByKey(it.key),
                                        tint = if (stack.value.active.configuration == it.type)
                                            MaterialTheme.colorScheme.primary
                                        else
                                            MaterialTheme.colorScheme.onPrimary
                                    )
                                },
                                label = { Text(text = GetTabTitleByKey(it.key).capitalized()) },
                                alwaysShowLabel = false,
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

