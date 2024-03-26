package root

import list2.ListComponent2
import ui.news.detail.DetailComponent
import ui.news.list.ListComponent

sealed class Child {
    class NewsList(val component: ListComponent) : Child()
    class NewsDetails(val component: DetailComponent) : Child()
    class SourcesList(val component: ListComponent2) : Child()
}