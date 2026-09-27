package com.dv.apps.komic.data.repository

import com.dv.apps.komic.Folder
import com.dv.apps.komic.domain.repository.FolderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class FolderRepositoryImpl : FolderRepository {
    override suspend fun addFolder(folder: Folder) {
        TODO("Not yet implemented")
    }

    override fun getFolders(): Flow<List<Folder>> {
        return emptyFlow()
    }

    override suspend fun deleteFolder(folder: Folder) {
        TODO("Not yet implemented")
    }
}
