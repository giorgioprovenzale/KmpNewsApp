package ui.tabs.sources

import ui.articles.details.ArticleDetailsComponent
import ui.articles.list.ArticlesListComponent
import ui.sources.SourcesListComponent

sealed class SourcesChild {

    class SourcesList(val component: SourcesListComponent) : SourcesChild()
    class ArticlesList(val component: ArticlesListComponent) : SourcesChild()
    class ArticleDetails(val component: ArticleDetailsComponent) : SourcesChild()
}