@file:OptIn(
    androidx.compose.ui.ExperimentalComposeUiApi::class,
    kotlin.js.ExperimentalWasmJsInterop::class
)

package com.dv.apps.komic

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ComposeViewport
import com.dv.apps.komic.di.mainModule
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

private val folderPicker: suspend () -> Folder? = lambda@{
    val options = createDirectoryPickerOptions(
        startIn = "downloads"
    )

    val fileSystemDirectoryHandle = showDirectoryPicker(
        options
    ).await() ?: return@lambda null

    Folder(fileSystemDirectoryHandle)
}

fun main() {
    startKoin {
        printLogger(Level.DEBUG)
        modules(mainModule)
    }
    ComposeViewport {
        CompositionLocalProvider(
            LocalFolderPicker provides folderPicker
        ) {
            App()
        }
    }
}
