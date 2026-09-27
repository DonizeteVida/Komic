package com.dv.apps.komic.domain.repository

import com.dv.apps.komic.Folder
import kotlinx.coroutines.flow.Flow

interface FolderRepository {

    suspend fun addFolder(folder: Folder)

    fun getFolders(): Flow<List<Folder>>

    suspend fun deleteFolder(folder: Folder)
}
