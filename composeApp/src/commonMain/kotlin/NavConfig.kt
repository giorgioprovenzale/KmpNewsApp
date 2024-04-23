import domain.models.Article
import domain.models.Category
import domain.models.Source
import kotlinx.serialization.Serializable

sealed interface NavConfig {

    @Serializable
    sealed class HeadlinesConfig : NavConfig {

        @Serializable
        data object ArticlesListConfig : HeadlinesConfig()

        @Serializable
        data class ArticleDetailsConfig(val article: Article) : HeadlinesConfig()
    }

    @Serializable
    sealed class SourcesConfig : NavConfig {

        @Serializable
        data object SourcesListConfig : SourcesConfig()

        @Serializable
        data class ArticlesListConfig(val source: Source) : SourcesConfig()

        @Serializable
        data class ArticleDetailsConfig(val article: Article) : SourcesConfig()
    }

    @Serializable
    sealed class CategoriesConfig : NavConfig {

        @Serializable
        data object CategoriesListConfig : CategoriesConfig()

        @Serializable
        data class ArticlesListConfig(val category: Category) : CategoriesConfig()

        @Serializable
        data class ArticleDetailsConfig(val article: Article) : CategoriesConfig()
    }
}