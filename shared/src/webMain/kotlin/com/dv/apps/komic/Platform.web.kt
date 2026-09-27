package com.dv.apps.komic

actual class Folder(val fileSystemDirectoryHandle: FileSystemDirectoryHandle)

actual fun FolderConstructor(path: String) = Folder(FileSystemDirectoryHandle(path))
