package di

import domain.repositories.ArticlesRepository
import org.koin.dsl.module

fun commonModule() = networkModule() + module {

    single {
        ArticlesRepository(getHeadlinesArticles = get())
    }
}