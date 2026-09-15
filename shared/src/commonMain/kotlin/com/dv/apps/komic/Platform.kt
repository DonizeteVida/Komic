package com.dv.apps.komic

import androidx.compose.runtime.staticCompositionLocalOf

expect class Folder

val LocalFolderPicker = staticCompositionLocalOf<suspend () -> Folder?> {
    { null }
}