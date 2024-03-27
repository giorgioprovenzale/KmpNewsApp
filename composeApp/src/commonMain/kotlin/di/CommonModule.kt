package di

import domain.DomainComponent
import domain.repositories.ArticlesRepository
import org.koin.dsl.module
import root.DefaultRootComponent
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
        DefaultRootComponent(
            componentContext = get(),
            domainComponent = get()
        )
    }

}