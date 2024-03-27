package root

import list2.ListComponent2
import ui.headlines.detail.NewsDetailsComponent
import ui.headlines.list.HeadlinesListComponent

sealed class Child {
    class HeadlinesList(val component: HeadlinesListComponent) : Child()
    class NewsDetails(val component: NewsDetailsComponent) : Child()
    class SourcesList(val component: ListComponent2) : Child()
}