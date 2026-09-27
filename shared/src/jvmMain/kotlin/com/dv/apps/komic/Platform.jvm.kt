package com.dv.apps.komic

import com.dv.apps.komic.data.repository.FolderRepositoryImpl
import com.dv.apps.komic.domain.repository.FolderRepository
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single

actual class Folder(val file: java.io.File)

actual fun FolderConstructor(path: String) = Folder(file = java.io.File(path))

actual val platformModule = module {
    single<FolderRepositoryImpl>() bind FolderRepository::class
}
