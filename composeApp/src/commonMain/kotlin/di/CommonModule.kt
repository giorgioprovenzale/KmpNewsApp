package di

import domain.HomeRepository
import domain.DomainComponent
import org.koin.dsl.module
import root.DefaultRootComponent
import root.RootComponent

fun commonModule() = networkModule() + module {

    single {
        HomeRepository(httpClient = get())
    }

    single {
        DomainComponent(homeRepository = get())
    }

    single<RootComponent> {
        DefaultRootComponent(
            componentContext = get(),
            domainComponent = get()
        )
    }

}