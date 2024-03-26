package root

import list2.ListComponent2
import ui.news.detail.NewsDetailsComponent
import ui.news.list.NewsListComponent

sealed class Child {
    class NewsList(val component: NewsListComponent) : Child()
    class NewsDetails(val component: NewsDetailsComponent) : Child()
    class SourcesList(val component: ListComponent2) : Child()
}