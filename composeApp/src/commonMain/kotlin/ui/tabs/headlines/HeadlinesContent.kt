package ui.tabs.headlines

import NavChild
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import ui.articles.details.ArticleDetailsContent
import ui.articles.list.ArticlesListContent

@ExperimentalDecomposeApi
@Composable
fun HeadlinesContent(
    component: HeadlinesComponent
) {
    val stack = component.headlinesStack.subscribeAsState()

    Children(
        stack = stack.value,
        animation = stackAnimation(fade())
    ) {
        when (val child = it.instance) {
            is NavChild.HeadlinesChild.ArticleDetails -> ArticleDetailsContent(child.component)
            is NavChild.HeadlinesChild.ArticlesList -> ArticlesListContent(child.component)
        }
    }

}
