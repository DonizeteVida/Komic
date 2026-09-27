package com.dv.apps.komic.domain.model

import com.dv.apps.komic.Folder

data class FolderSettings(
    val folders: List<Folder> = emptyList()
)