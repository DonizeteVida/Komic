@file:OptIn(
    androidx.compose.ui.ExperimentalComposeUiApi::class,
    kotlin.js.ExperimentalWasmJsInterop::class
)

package com.dv.apps.komic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ComposeViewport
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@Composable
fun registerFolderPicker(): suspend () -> Folder? = {
    val options = createDirectoryPickerOptions(
        startIn = "downloads"
    )

    val fileSystemDirectoryHandle = showDirectoryPicker(
        options
    ).await()

    fileSystemDirectoryHandle
}

fun main() {
    startKoin {
        printLogger(Level.DEBUG)
        modules()
    }
    ComposeViewport {
        val folderPicker = registerFolderPicker()

        CompositionLocalProvider(
            LocalFolderPicker provides folderPicker
        ) {
            App()
        }
    }
}
