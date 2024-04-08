package di

import domain.repositories.ArticlesRepository
import domain.repositories.SourcesRepository
import org.koin.dsl.module

fun commonModule() = networkModule() + module {

    single {
        ArticlesRepository(getHeadlinesArticles = get())
    }
    single {
        SourcesRepository(getSourcesInteractor = get())
    }
}