package ui

import ui.headlines.detail.ArticleDetailsComponent
import ui.tabs.headlines.HeadlinesComponent
import ui.tabs.sources.SourcesComponent

sealed class Child {

    sealed class HomeChild : Child() {
        class HeadlinesList(val component: HeadlinesComponent) : HomeChild()
        class NewsDetails(val component: ArticleDetailsComponent) : HomeChild()
    }

    sealed class SourcesChild : Child() {
        class SourcesList(val component: SourcesComponent) : SourcesChild()
    }
}