import ui.articles.details.ArticleDetailsComponent
import ui.articles.list.ArticlesListComponent
import ui.categories.CategoriesListComponent
import ui.sources.SourcesListComponent
import ui.tabs.categories.CategoriesComponent
import ui.tabs.headlines.HeadlinesComponent
import ui.tabs.sources.SourcesComponent

sealed class TabsChild {
    class Headlines(val component: HeadlinesComponent) : TabsChild()
    class SourcesList(val component: SourcesComponent) : TabsChild()
    class CategoriesList(val component: CategoriesComponent) : TabsChild()
}

sealed interface NavChild {

    sealed class HeadlinesChild : NavChild {
        class ArticlesList(val component: ArticlesListComponent) : HeadlinesChild()
        class ArticleDetails(val component: ArticleDetailsComponent) : HeadlinesChild()
    }

    sealed class SourcesChild : NavChild {

        class SourcesList(val component: SourcesListComponent) : SourcesChild()
        class ArticlesList(val component: ArticlesListComponent) : SourcesChild()
        class ArticleDetails(val component: ArticleDetailsComponent) : SourcesChild()
    }

    sealed class CategoriesChild : NavChild {

        class CategoriesList(val component: CategoriesListComponent) : CategoriesChild()
        class ArticlesList(val component: ArticlesListComponent) : CategoriesChild()
        class ArticleDetails(val component: ArticleDetailsComponent) : CategoriesChild()
    }
}