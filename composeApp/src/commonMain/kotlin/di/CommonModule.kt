package di

import domain.DomainComponent
import domain.repositories.ArticlesRepository
import org.koin.dsl.module
import root.RootComponent

fun commonModule() = networkModule() + module {

    single {
        ArticlesRepository(getHeadlinesArticles = get())
    }

    single {
        DomainComponent(
            articlesRepository = get()
        )
    }

    single<RootComponent> {
        RootComponent(
            componentContext = get(),
            domainComponent = get()
        )
    }

}