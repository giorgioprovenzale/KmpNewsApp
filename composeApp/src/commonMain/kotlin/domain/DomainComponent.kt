package domain

import domain.repositories.ArticlesRepository

data class DomainComponent(
    val articlesRepository: ArticlesRepository,
)
