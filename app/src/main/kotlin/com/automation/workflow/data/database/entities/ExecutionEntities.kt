package com.automation.workflow.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "execution_logs",
    foreignKeys = [
        ForeignKey(
            entity = WorkflowEntity::class,
            parentColumns = ["id"],
            childColumns = ["workflowId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("workflowId"), Index("startTime"), Index("status")]
)
data class ExecutionLogEntity(
    @PrimaryKey
    val id: String,
    val workflowId: String,
    val startTime: String,
    val endTime: String?,
    val status: String,
    val nodeExecutions: String,
    val totalDuration: Long,
    val memoryUsed: Long,
    val batteryConsumed: Float
)

@Entity(
    tableName = "log_entries",
    foreignKeys = [
        ForeignKey(
            entity = ExecutionLogEntity::class,
            parentColumns = ["id"],
            childColumns = ["executionLogId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("executionLogId"), Index("timestamp"), Index("level")]
)
data class LogEntryEntity(
    @PrimaryKey
    val id: String,
    val executionLogId: String,
    val timestamp: String,
    val level: String,
    val nodeId: String?,
    val nodeName: String?,
    val message: String,
    val details: String?
)
