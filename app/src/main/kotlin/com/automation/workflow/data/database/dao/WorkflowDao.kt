package com.automation.workflow.data.database.dao

import androidx.room.*
import com.automation.workflow.data.database.entities.WorkflowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkflowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(workflow: WorkflowEntity)

    @Update
    suspend fun update(workflow: WorkflowEntity)

    @Delete
    suspend fun delete(workflow: WorkflowEntity)

    @Query("SELECT * FROM workflows WHERE id = :id")
    suspend fun getById(id: String): WorkflowEntity?

    @Query("SELECT * FROM workflows ORDER BY updatedAt DESC")
    fun getAllFlow(): Flow<List<WorkflowEntity>>

    @Query("SELECT * FROM workflows ORDER BY updatedAt DESC")
    suspend fun getAll(): List<WorkflowEntity>

    @Query("SELECT * FROM workflows WHERE name LIKE '%' || :query || '%' ORDER BY updatedAt DESC")
    fun searchFlow(query: String): Flow<List<WorkflowEntity>>

    @Query("SELECT * FROM workflows WHERE isActive = 1 ORDER BY updatedAt DESC")
    fun getActiveFlow(): Flow<List<WorkflowEntity>>

    @Query("DELETE FROM workflows WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT COUNT(*) FROM workflows")
    suspend fun count(): Int
}
