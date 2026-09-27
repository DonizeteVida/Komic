package com.dv.apps.komic

import androidx.compose.runtime.staticCompositionLocalOf

expect class Folder

expect fun FolderConstructor(path: String): Folder

val LocalFolderPicker = staticCompositionLocalOf<suspend () -> Folder?> {
    { null }
}