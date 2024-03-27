package di

import domain.DomainComponent
import domain.repositories.ArticlesRepository
import domain.repositories.HomeRepository
import org.koin.dsl.module
import root.DefaultRootComponent
import root.RootComponent

fun commonModule() = networkModule() + module {

    single {
        HomeRepository(httpClient = get())
    }
    single {
        ArticlesRepository(getHeadlinesArticles = get())
    }

    single {
        DomainComponent(
            homeRepository = get(),
            articlesRepository = get()
        )
    }

    single<RootComponent> {
        DefaultRootComponent(
            componentContext = get(),
            domainComponent = get()
        )
    }

}