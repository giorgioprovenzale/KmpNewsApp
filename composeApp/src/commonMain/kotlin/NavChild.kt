import ui.articles.details.ArticleDetailsComponent
import ui.articles.list.ArticlesListComponent
import ui.categories.CategoriesListComponent
import ui.sources.SourcesListComponent

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