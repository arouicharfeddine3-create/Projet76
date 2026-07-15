package com.automation.workflow.data.database.dao

import androidx.room.*
import com.automation.workflow.data.database.entities.ConnectionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConnectionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(connection: ConnectionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(connections: List<ConnectionEntity>)

    @Update
    suspend fun update(connection: ConnectionEntity)

    @Delete
    suspend fun delete(connection: ConnectionEntity)

    @Query("SELECT * FROM connections WHERE id = :id")
    suspend fun getById(id: String): ConnectionEntity?

    @Query("SELECT * FROM connections WHERE workflowId = :workflowId")
    fun getByWorkflowFlow(workflowId: String): Flow<List<ConnectionEntity>>

    @Query("SELECT * FROM connections WHERE workflowId = :workflowId")
    suspend fun getByWorkflow(workflowId: String): List<ConnectionEntity>

    @Query("SELECT * FROM connections WHERE fromNodeId = :nodeId")
    suspend fun getOutgoing(nodeId: String): List<ConnectionEntity>

    @Query("SELECT * FROM connections WHERE toNodeId = :nodeId")
    suspend fun getIncoming(nodeId: String): List<ConnectionEntity>

    @Query("DELETE FROM connections WHERE workflowId = :workflowId")
    suspend fun deleteByWorkflow(workflowId: String)

    @Query("DELETE FROM connections WHERE id = :id")
    suspend fun deleteById(id: String)
}
