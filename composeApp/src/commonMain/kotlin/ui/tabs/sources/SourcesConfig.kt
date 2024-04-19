package ui.tabs.sources

import domain.models.Article
import domain.models.Source
import kotlinx.serialization.Serializable

@Serializable
sealed class SourcesConfig {

    @Serializable
    data object SourcesListConfig : SourcesConfig()
    @Serializable
    data class ArticlesListConfig(val source: Source) : SourcesConfig()

    @Serializable
    data class ArticleDetailsConfig(val article: Article) : SourcesConfig()
}
