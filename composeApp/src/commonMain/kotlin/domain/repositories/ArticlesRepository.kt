package domain.repositories

import source.data.toDomain
import domain.interactors.GetHeadlinesArticlesInteractor
import domain.models.Article

class ArticlesRepository(
    private val getHeadlinesArticles: GetHeadlinesArticlesInteractor
) {

    suspend fun getHeadlines(): List<Article> = getHeadlinesArticles().map { it.toDomain() }
}