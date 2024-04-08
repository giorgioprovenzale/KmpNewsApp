package ui.tabs.headlines

import domain.models.Article
import kotlinx.serialization.Serializable

@Serializable
sealed class HeadlinesConfig {

    @Serializable
    data object ArticlesListConfig : HeadlinesConfig()

    @Serializable
    data class ArticleDetailsConfig(val article: Article) : HeadlinesConfig()
}
