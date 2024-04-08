package ui.tabs.headlines

import ui.articles.details.ArticleDetailsComponent
import ui.articles.list.ArticlesListComponent
import ui.tabs.sources.SourcesComponent

sealed class HeadlinesChild {
    class ArticlesList(val component: ArticlesListComponent) : HeadlinesChild()
    class ArticleDetails(val component: ArticleDetailsComponent) : HeadlinesChild()
}