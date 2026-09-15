package com.dv.apps.komic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import kotlinx.coroutines.channels.Channel

@Composable
private fun registerFolderPicker(): suspend () -> Folder? {
    val response = Channel<Folder?>()
    val request = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocumentTree()
    ) {
        response.trySend(it?.run(::Folder))
    }
    return {
        request.launch(null)
        response.receive()
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val folderPicker = registerFolderPicker()

            CompositionLocalProvider(
                LocalFolderPicker provides folderPicker
            ) {
                App()
            }
        }
    }
}
