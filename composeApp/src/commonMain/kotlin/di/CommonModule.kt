package di

import domain.repositories.ArticlesRepository
import domain.repositories.CategoriesRepository
import domain.repositories.SourcesRepository
import org.koin.dsl.module

fun commonModule() = networkModule() + module {

    single {
        ArticlesRepository(
            getHeadlinesArticles = get(),
            getHeadlinesArticlesBySource = get(),
            getHeadlinesArticlesByCategory = get()
        )
    }
    single {
        SourcesRepository(getSourcesInteractor = get())
    }
    single {
        CategoriesRepository(getCategoriesInteractor = get())
    }
}