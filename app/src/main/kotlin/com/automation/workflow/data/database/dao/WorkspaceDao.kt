package com.automation.workflow.data.database.dao

import androidx.room.*
import com.automation.workflow.data.database.entities.WorkspaceFileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkspaceFileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(file: WorkspaceFileEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(files: List<WorkspaceFileEntity>)

    @Update
    suspend fun update(file: WorkspaceFileEntity)

    @Delete
    suspend fun delete(file: WorkspaceFileEntity)

    @Query("SELECT * FROM workspace_files WHERE id = :id")
    suspend fun getById(id: String): WorkspaceFileEntity?

    @Query("SELECT * FROM workspace_files WHERE path = :path")
    suspend fun getByPath(path: String): WorkspaceFileEntity?

    @Query("SELECT * FROM workspace_files WHERE parentId = :parentId ORDER BY isDirectory DESC, name ASC")
    fun getChildrenFlow(parentId: String?): Flow<List<WorkspaceFileEntity>>

    @Query("SELECT * FROM workspace_files WHERE isFavorite = 1 ORDER BY name")
    fun getFavoritesFlow(): Flow<List<WorkspaceFileEntity>>

    @Query("SELECT * FROM workspace_files WHERE name LIKE '%' || :query || '%' ORDER BY name")
    fun searchFlow(query: String): Flow<List<WorkspaceFileEntity>>

    @Query("DELETE FROM workspace_files WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM workspace_files WHERE parentId = :parentId")
    suspend fun deleteChildren(parentId: String)
}
