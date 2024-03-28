package root

import list2.ListComponent2
import ui.headlines.detail.ArticleDetailsComponent
import ui.headlines.list.HeadlinesListComponent

sealed class Child {
    sealed class HomeChild : Child() {
        class HeadlinesList(val component: HeadlinesListComponent) : HomeChild()
        class NewsDetails(val component: ArticleDetailsComponent) : HomeChild()
    }

    sealed class SourcesChild : Child() {
        class SourcesList(val component: ListComponent2) : SourcesChild()
    }
}