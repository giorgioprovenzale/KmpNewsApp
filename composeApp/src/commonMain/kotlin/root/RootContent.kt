package root

import TabsToArticleChild
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import org.jetbrains.compose.resources.ExperimentalResourceApi
import theme.AppTheme
import ui.articles.details.ArticleDetailsContent
import ui.tabs.TabsContent

@ExperimentalDecomposeApi
@ExperimentalMaterial3Api
@ExperimentalResourceApi
@Composable
fun RootContent(
    component: RootComponent,
) {
    val stack = component.tabsToArticleStack.subscribeAsState()
    var useDarkTheme by remember { mutableStateOf(false) }

    AppTheme(
        useDarkTheme = useDarkTheme
    ) {
        Surface {
            Children(
                stack = stack.value, animation = stackAnimation(fade() + scale())
            ) { activeStackItem ->
                when (val child = activeStackItem.instance) {
                    is TabsToArticleChild.ArticleDetails -> ArticleDetailsContent(
                        component = child.component,
                        useDarkTheme = useDarkTheme
                    ) {
                        useDarkTheme = !useDarkTheme
                    }

                    is TabsToArticleChild.Tabs -> TabsContent(
                        component = child.component,
                        useDarkTheme = useDarkTheme
                    ) {
                        useDarkTheme = !useDarkTheme
                    }
                }
            }
        }

    }
}

