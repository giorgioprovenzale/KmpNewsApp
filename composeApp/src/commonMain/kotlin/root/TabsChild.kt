package root

import ui.tabs.categories.CategoriesComponent
import ui.tabs.headlines.HeadlinesComponent
import ui.tabs.sources.SourcesComponent

sealed class TabsChild {
    class Headlines(val component: HeadlinesComponent) : TabsChild()
    class SourcesList(val component: SourcesComponent) : TabsChild()
    class CategoriesList(val component: CategoriesComponent) : TabsChild()
}