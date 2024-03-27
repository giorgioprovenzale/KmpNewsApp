package domain

import domain.repositories.ArticlesRepository
import domain.repositories.HomeRepository

data class DomainComponent(
    val homeRepository: HomeRepository,
    val articlesRepository: ArticlesRepository,
)
