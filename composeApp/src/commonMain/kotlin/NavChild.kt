import ui.articles.details.ArticleDetailsComponent
import ui.articles.list.ArticlesListComponent
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
}