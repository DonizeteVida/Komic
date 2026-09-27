package com.dv.apps.komic

actual class Folder(val file: java.io.File)

actual fun FolderConstructor(path: String) = Folder(file = java.io.File(path))