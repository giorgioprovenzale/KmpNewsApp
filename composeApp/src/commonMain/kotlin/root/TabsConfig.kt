package root

import androidx.compose.runtime.Composable
import kmpnewsapp.composeapp.generated.resources.Res
import kmpnewsapp.composeapp.generated.resources.categories
import kmpnewsapp.composeapp.generated.resources.home
import kmpnewsapp.composeapp.generated.resources.ic_categories_24
import kmpnewsapp.composeapp.generated.resources.ic_news_24p
import kmpnewsapp.composeapp.generated.resources.ic_sources_24
import kmpnewsapp.composeapp.generated.resources.sources
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@Serializable
sealed class TabsConfig {

    @Serializable
    data object HeadlinesTabConfig : TabsConfig()

    @Serializable
    data object SourcesTabConfig : TabsConfig()

    @Serializable
    data object CategoriesTabConfig : TabsConfig()
}

enum class TabKey {
    Home, Sources, Categories
}

@ExperimentalResourceApi
data class TabItem(
    val type: TabsConfig,
    val icon: DrawableResource,
    val key: TabKey,
)

@ExperimentalResourceApi
fun tabItems() = listOf(
    TabItem(
        type = TabsConfig.HeadlinesTabConfig,
        icon = Res.drawable.ic_news_24p,
        key = TabKey.Home,
    ),
    TabItem(
        type = TabsConfig.SourcesTabConfig,
        icon = Res.drawable.ic_sources_24,
        key = TabKey.Sources,
    ),
    TabItem(
        type = TabsConfig.CategoriesTabConfig,
        icon = Res.drawable.ic_categories_24,
        key = TabKey.Categories,
    )
)

@ExperimentalResourceApi
@Composable
fun GetTabTitleByKey(key: TabKey) = when (key) {
    TabKey.Home -> stringResource(Res.string.home)
    TabKey.Sources -> stringResource(Res.string.sources)
    TabKey.Categories -> stringResource(Res.string.categories)
}
