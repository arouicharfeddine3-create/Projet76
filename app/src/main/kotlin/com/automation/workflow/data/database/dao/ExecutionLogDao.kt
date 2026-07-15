package com.automation.workflow.data.database.dao

import androidx.room.*
import com.automation.workflow.data.database.entities.ExecutionLogEntity
import com.automation.workflow.data.database.entities.LogEntryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExecutionLogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(log: ExecutionLogEntity)

    @Update
    suspend fun update(log: ExecutionLogEntity)

    @Delete
    suspend fun delete(log: ExecutionLogEntity)

    @Query("SELECT * FROM execution_logs WHERE id = :id")
    suspend fun getById(id: String): ExecutionLogEntity?

    @Query("SELECT * FROM execution_logs WHERE workflowId = :workflowId ORDER BY startTime DESC LIMIT 50")
    fun getByWorkflowFlow(workflowId: String): Flow<List<ExecutionLogEntity>>

    @Query("SELECT * FROM execution_logs WHERE workflowId = :workflowId ORDER BY startTime DESC LIMIT 50")
    suspend fun getByWorkflow(workflowId: String): List<ExecutionLogEntity>

    @Query("SELECT * FROM execution_logs WHERE status = :status ORDER BY startTime DESC LIMIT 20")
    fun getByStatusFlow(status: String): Flow<List<ExecutionLogEntity>>

    @Query("DELETE FROM execution_logs WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM execution_logs WHERE startTime < :beforeTime")
    suspend fun deleteOlderThan(beforeTime: String)
}

@Dao
interface LogEntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: LogEntryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entries: List<LogEntryEntity>)

    @Query("SELECT * FROM log_entries WHERE executionLogId = :executionLogId ORDER BY timestamp ASC")
    fun getByExecutionFlow(executionLogId: String): Flow<List<LogEntryEntity>>

    @Query("SELECT * FROM log_entries WHERE executionLogId = :executionLogId AND level = :level ORDER BY timestamp ASC")
    fun getByLevelFlow(executionLogId: String, level: String): Flow<List<LogEntryEntity>>

    @Query("DELETE FROM log_entries WHERE executionLogId = :executionLogId")
    suspend fun deleteByExecution(executionLogId: String)
}
