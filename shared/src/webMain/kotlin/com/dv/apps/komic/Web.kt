@file:OptIn(kotlin.js.ExperimentalWasmJsInterop::class)

package com.dv.apps.komic

import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.js.Promise

suspend fun <T: JsAny> Promise<T>.await(): T? = suspendCancellableCoroutine { cont ->
    then(
        onFulfilled = {
            cont.resume(it)
            null
        },
        onRejected = {
            cont.resume(null)
            null
        }
    )
}

external class FileSystemDirectoryHandle : JsAny {
    val name: String
}

fun createDirectoryPickerOptions(
    startIn: String? = null,
): JsAny = js(
    "({ startIn })"
)

external fun showDirectoryPicker(
    options: JsAny = definedExternally
): Promise<FileSystemDirectoryHandle>
