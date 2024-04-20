package ui.tabs.sources

import NavChild
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import ui.articles.details.ArticleDetailsContent
import ui.articles.list.ArticlesListContent
import ui.sources.SourcesListContent

@Composable
fun SourcesContent(
    component: SourcesComponent
) {
    val stack = component.sourcesStack.subscribeAsState()

    Children(
        stack = stack.value,
        animation = stackAnimation(fade())
    ) {
        when (val child = it.instance) {
            is NavChild.SourcesChild.ArticleDetails -> ArticleDetailsContent(child.component)
            is NavChild.SourcesChild.ArticlesList -> ArticlesListContent(child.component)
            is NavChild.SourcesChild.SourcesList -> SourcesListContent(child.component)
        }
    }

}