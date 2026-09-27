package com.dv.apps.komic

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

private fun registerFolderPicker(
    parent: androidx.compose.ui.awt.ComposeWindow
): suspend () -> Folder? = lambda@{
    val fc = javax.swing.JFileChooser().apply {
        fileSelectionMode = javax.swing.JFileChooser.DIRECTORIES_ONLY
    }

    val file = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
        val ret = fc.showOpenDialog(parent)
        when (ret) {
            javax.swing.JFileChooser.APPROVE_OPTION -> fc.selectedFile
            else -> null
        }
    } ?: return@lambda null

    Folder(file)
}

fun main() = application {
    startKoin {
        printLogger(Level.DEBUG)
        modules()
    }
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
