package ui.tabs.categories

import NavChild
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import ui.articles.details.ArticleDetailsContent
import ui.articles.list.ArticlesListContent
import ui.categories.CategoriesListContent

@Composable
fun CategoriesContent(
    component: CategoriesComponent
) {
    val stack = component.categoriesStack.subscribeAsState()

    Children(
        stack = stack.value,
        animation = stackAnimation(fade())
    ) {
        when (val child = it.instance) {
            is NavChild.CategoriesChild.ArticleDetails -> ArticleDetailsContent(child.component)
            is NavChild.CategoriesChild.ArticlesList -> ArticlesListContent(child.component)
            is NavChild.CategoriesChild.CategoriesList -> CategoriesListContent(child.component)
        }
    }

}