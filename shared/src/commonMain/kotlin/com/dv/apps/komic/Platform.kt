package com.dv.apps.komic

import androidx.compose.runtime.staticCompositionLocalOf
import org.koin.core.module.Module

expect class Folder

expect fun FolderConstructor(path: String): Folder

val LocalFolderPicker = staticCompositionLocalOf<suspend () -> Folder?> {
    { null }
}

expect val platformModule: Module
