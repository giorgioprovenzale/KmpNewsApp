package ui.tabs.sources

import domain.models.Article
import kotlinx.serialization.Serializable

@Serializable
sealed class SourcesConfig {

    @Serializable
    data object SourcesListConfig : SourcesConfig()
    @Serializable
    data object ArticlesListConfig : SourcesConfig()

    @Serializable
    data class ArticleDetailsConfig(val article: Article) : SourcesConfig()
}
