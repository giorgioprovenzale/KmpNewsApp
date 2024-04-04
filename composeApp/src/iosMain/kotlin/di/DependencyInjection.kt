package di

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import domain.DomainComponent
import org.koin.core.Koin
import org.koin.dsl.module
import root.RootComponent

val iosModule = module {
}

fun initKoinIOS() = initKoin(additionalModules = listOf())

val Koin.domainComponent: DomainComponent
    get() = get()