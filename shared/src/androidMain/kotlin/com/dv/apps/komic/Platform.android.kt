package com.dv.apps.komic

import androidx.core.net.toUri

actual data class Folder(val uri: android.net.Uri)

actual fun FolderConstructor(path: String) = Folder(uri = path.toUri())