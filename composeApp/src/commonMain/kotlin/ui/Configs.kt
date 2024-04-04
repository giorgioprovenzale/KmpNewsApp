package ui

import domain.models.Article
import kotlinx.serialization.Serializable


@Serializable
sealed interface HomeTabConfigs {

    @Serializable
    data object HeadlinesListConfig : HomeTabConfigs

    @Serializable
    data class ArticleDetailsConfig(val item: Article) : HomeTabConfigs
}

@Serializable
sealed interface SourcesTabConfigs {

    @Serializable
    data object SourcesListConfig : SourcesTabConfigs
}