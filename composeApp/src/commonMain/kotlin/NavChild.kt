@file:OptIn(ExperimentalResourceApi::class)

import org.jetbrains.compose.resources.ExperimentalResourceApi
import ui.articles.details.ArticleDetailsComponent
import ui.articles.list.ArticlesListComponent
import ui.categories.CategoriesListComponent
import ui.sources.SourcesListComponent
import ui.tabs.TabsComponent
import ui.tabs.categories.CategoriesComponent
import ui.tabs.headlines.HeadlinesComponent
import ui.tabs.sources.SourcesComponent

sealed class TabsToArticleChild {
    class Tabs(val component: TabsComponent) : TabsToArticleChild()
    class ArticleDetails(val component: ArticleDetailsComponent) : TabsToArticleChild()
}

sealed class TabsChild {
    class Headlines(val component: HeadlinesComponent) : TabsChild()
    class SourcesList(val component: SourcesComponent) : TabsChild()
    class CategoriesList(val component: CategoriesComponent) : TabsChild()
}

sealed interface NavChild {

    sealed class HeadlinesChild : NavChild {
        class ArticlesList(val component: ArticlesListComponent) : HeadlinesChild()
    }

    sealed class SourcesChild : NavChild {

        class SourcesList(val component: SourcesListComponent) : SourcesChild()
        class ArticlesList(val component: ArticlesListComponent) : SourcesChild()
    }

    sealed class CategoriesChild : NavChild {

        class CategoriesList(val component: CategoriesListComponent) : CategoriesChild()
        class ArticlesList(val component: ArticlesListComponent) : CategoriesChild()
    }
}