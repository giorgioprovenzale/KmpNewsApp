package root

import TabsToArticleChild
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary).windowInsetsPadding(WindowInsets.safeDrawing)
        ) {
            Children(
                stack = stack.value, animation = stackAnimation(fade() + scale())
            ) { activeStackItem ->
                when (val child = activeStackItem.instance) {
                    is TabsToArticleChild.ArticleDetails -> ArticleDetailsContent(child.component)
                    is TabsToArticleChild.Tabs -> TabsContent(
                        component = child.component
                    )
                }
            }
        }

    }
}

