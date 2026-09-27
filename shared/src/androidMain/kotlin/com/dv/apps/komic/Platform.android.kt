package com.dv.apps.komic

import androidx.core.net.toUri
import com.dv.apps.komic.data.repository.FolderRepositoryImpl
import com.dv.apps.komic.domain.repository.FolderRepository
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single

actual data class Folder(val uri: android.net.Uri)

actual fun FolderConstructor(path: String) = Folder(uri = path.toUri())

actual val platformModule = module {
    single<FolderRepositoryImpl>() bind FolderRepository::class
}
