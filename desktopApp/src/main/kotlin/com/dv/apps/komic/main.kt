package com.dv.apps.komic

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

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
        CompositionLocalProvider(
            LocalFolderPicker provides registerFolderPicker(window)
        ) {
            App()
        }
    }
}
