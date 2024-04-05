package di

import org.koin.dsl.module

val iosModule = module {}

fun initKoinIOS() = initKoin(additionalModules = listOf(iosModule))