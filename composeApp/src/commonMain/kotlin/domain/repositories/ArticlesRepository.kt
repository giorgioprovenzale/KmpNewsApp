package domain.repositories

import domain.interactors.GetHeadlinesArticlesByCategoryInteractor
import domain.interactors.GetHeadlinesArticlesBySourceInteractor
import domain.interactors.GetHeadlinesArticlesInteractor
import domain.models.Article
import domain.models.Category
import domain.models.Source
import source.data.toDomain

class ArticlesRepository(
    private val getHeadlinesArticles: GetHeadlinesArticlesInteractor,
    private val getHeadlinesArticlesBySource: GetHeadlinesArticlesBySourceInteractor,
    private val getHeadlinesArticlesByCategory: GetHeadlinesArticlesByCategoryInteractor
) {

    suspend fun getHeadlines(): List<Article> = getHeadlinesArticles().map { it.toDomain() }
    suspend fun getHeadlinesBySource(source: Source): List<Article> = getHeadlinesArticlesBySource(source.id).map { it.toDomain() }
    suspend fun getHeadlinesByCategory(category: Category): List<Article> = getHeadlinesArticlesByCategory(category).map { it.toDomain() }
}