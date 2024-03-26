package root

import data.Product
import kotlinx.serialization.Serializable

@Serializable
sealed interface HomeTabConfigs {

    @Serializable
    data object NewsListConfig : HomeTabConfigs

    @Serializable
    data class NewsDetailsConfig(val item: Product) : HomeTabConfigs
}

@Serializable
sealed interface SourcesTabConfigs {

    @Serializable
    data object SourcesListConfig : SourcesTabConfigs
}