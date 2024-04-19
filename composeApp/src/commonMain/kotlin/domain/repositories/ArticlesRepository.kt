package domain.repositories

import domain.interactors.GetHeadlinesArticlesBySourceInteractor
import domain.interactors.GetHeadlinesArticlesInteractor
import domain.models.Article
import domain.models.Source
import source.data.toDomain

class ArticlesRepository(
    private val getHeadlinesArticles: GetHeadlinesArticlesInteractor,
    private val getHeadlinesArticlesBySource: GetHeadlinesArticlesBySourceInteractor
) {

    suspend fun getHeadlines(): List<Article> = getHeadlinesArticles().map { it.toDomain() }
    suspend fun getHeadlinesBySource(source: Source): List<Article> = getHeadlinesArticlesBySource(source.id).map { it.toDomain() }
}