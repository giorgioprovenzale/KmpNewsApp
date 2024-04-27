import domain.models.Article
import domain.models.Category
import domain.models.Source
import kotlinx.serialization.Serializable

@Serializable
sealed class TabsToArticleConfig {

    @Serializable
    data object TabsConfig : TabsToArticleConfig()

    @Serializable
    data class ArticleConfig(val article: Article) : TabsToArticleConfig()
}

@Serializable
sealed class TabsConfig {

    @Serializable
    data object HeadlinesTabConfig : TabsConfig()

    @Serializable
    data object SourcesTabConfig : TabsConfig()

    @Serializable
    data object CategoriesTabConfig : TabsConfig()
}

sealed interface NavConfig {

    @Serializable
    sealed class HeadlinesConfig : NavConfig {

        @Serializable
        data object ArticlesListConfig : HeadlinesConfig()
    }

    @Serializable
    sealed class SourcesConfig : NavConfig {

        @Serializable
        data object SourcesListConfig : SourcesConfig()

        @Serializable
        data class ArticlesListConfig(val source: Source) : SourcesConfig()
    }

    @Serializable
    sealed class CategoriesConfig : NavConfig {

        @Serializable
        data object CategoriesListConfig : CategoriesConfig()

        @Serializable
        data class ArticlesListConfig(val category: Category) : CategoriesConfig()
    }
}