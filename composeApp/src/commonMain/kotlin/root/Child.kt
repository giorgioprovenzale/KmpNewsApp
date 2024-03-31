package root

import ui.headlines.detail.ArticleDetailsComponent
import ui.tabs.categories.CategoriesComponent
import ui.tabs.headlines.HeadlinesComponent
import ui.tabs.sources.SourcesComponent

sealed class Child {

    sealed class TabsChild : Child() {
        class Headlines(val component: HeadlinesComponent) : TabsChild()
        class SourcesList(val component: SourcesComponent) : TabsChild()
        class CategoriesList(val component: CategoriesComponent) : TabsChild()
    }

    sealed class HomeChild : Child() {
        class HeadlinesList(val component: HeadlinesComponent) : HomeChild()
        class NewsDetails(val component: ArticleDetailsComponent) : HomeChild()
    }

    sealed class SourcesChild : Child() {
        class SourcesList(val component: SourcesComponent) : SourcesChild()
    }
}