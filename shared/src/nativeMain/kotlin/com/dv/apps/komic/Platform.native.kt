package com.dv.apps.komic

import com.dv.apps.komic.data.repository.FolderRepositoryImpl
import com.dv.apps.komic.di.mainModule
import com.dv.apps.komic.domain.repository.FolderRepository
import org.koin.core.KoinApplication
import org.koin.core.logger.Level
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single
import org.koin.plugin.module.dsl.startKoin

actual typealias Folder = String

actual fun FolderConstructor(path: String) = path

fun initKoin() {
    startKoin<KoinApplication> {
        printLogger(Level.DEBUG)
        modules(mainModule)
    }
}

actual val platformModule = module {
    single<FolderRepositoryImpl>() bind FolderRepository::class
}
