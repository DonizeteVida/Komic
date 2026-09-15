package com.dv.apps.komic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
private fun registerFolderPicker(
    parent: androidx.compose.ui.awt.ComposeWindow
): suspend () -> Folder? = {
    val fc = javax.swing.JFileChooser().apply {
        fileSelectionMode = javax.swing.JFileChooser.DIRECTORIES_ONLY
    }
    kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
        val ret = fc.showOpenDialog(parent)
        when (ret) {
            javax.swing.JFileChooser.APPROVE_OPTION -> fc.selectedFile
            else -> null
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Komic",
    ) {
        val folderPicker = registerFolderPicker(window)

        CompositionLocalProvider(
            LocalFolderPicker provides folderPicker
        ) {
            App()
        }
    }
}
