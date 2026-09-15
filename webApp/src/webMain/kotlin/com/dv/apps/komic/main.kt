@file:OptIn(
    androidx.compose.ui.ExperimentalComposeUiApi::class,
    kotlin.js.ExperimentalWasmJsInterop::class
)

package com.dv.apps.komic

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ComposeViewport

private val folderPicker: suspend () -> Folder? = {
    val options = createDirectoryPickerOptions(
        startIn = "downloads"
    )

    val fileSystemDirectoryHandle = showDirectoryPicker(
        options
    ).await()

    fileSystemDirectoryHandle
}

fun main() {
    ComposeViewport {
        CompositionLocalProvider(
            LocalFolderPicker provides folderPicker
        ) {
            App()
        }
    }
}
