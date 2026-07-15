package com.automation.workflow.data.database.dao

import androidx.room.*
import com.automation.workflow.data.database.entities.NodeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(node: NodeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(nodes: List<NodeEntity>)

    @Update
    suspend fun update(node: NodeEntity)

    @Delete
    suspend fun delete(node: NodeEntity)

    @Query("SELECT * FROM nodes WHERE id = :id")
    suspend fun getById(id: String): NodeEntity?

    @Query("SELECT * FROM nodes WHERE workflowId = :workflowId ORDER BY name")
    fun getByWorkflowFlow(workflowId: String): Flow<List<NodeEntity>>

    @Query("SELECT * FROM nodes WHERE workflowId = :workflowId ORDER BY name")
    suspend fun getByWorkflow(workflowId: String): List<NodeEntity>

    @Query("SELECT * FROM nodes WHERE workflowId = :workflowId AND type = :type")
    suspend fun getByType(workflowId: String, type: String): List<NodeEntity>

    @Query("DELETE FROM nodes WHERE workflowId = :workflowId")
    suspend fun deleteByWorkflow(workflowId: String)

    @Query("DELETE FROM nodes WHERE id = :id")
    suspend fun deleteById(id: String)
}
