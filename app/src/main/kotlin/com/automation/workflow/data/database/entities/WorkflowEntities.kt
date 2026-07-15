package com.automation.workflow.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "workflows",
    indices = [Index("name"), Index("createdAt")]
)
data class WorkflowEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val globalVariables: String,
    val isActive: Boolean,
    val tags: String,
    val version: String,
    val createdAt: String,
    val updatedAt: String,
    val author: String,
    val thumbnail: String?
)

@Entity(
    tableName = "nodes",
    foreignKeys = [
        ForeignKey(
            entity = WorkflowEntity::class,
            parentColumns = ["id"],
            childColumns = ["workflowId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("workflowId"), Index("type")]
)
data class NodeEntity(
    @PrimaryKey
    val id: String,
    val workflowId: String,
    val type: String,
    val name: String,
    val description: String,
    val x: Float,
    val y: Float,
    val width: Float,
    val height: Float,
    val config: String,
    val inputPorts: String,
    val outputPorts: String,
    val script: String?,
    val tags: String,
    val isBreakpoint: Boolean,
    val errorOutput: String?,
    val timeout: Long,
    val retryCount: Int
)

@Entity(
    tableName = "connections",
    foreignKeys = [
        ForeignKey(
            entity = WorkflowEntity::class,
            parentColumns = ["id"],
            childColumns = ["workflowId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("workflowId"), Index("fromNodeId"), Index("toNodeId")]
)
data class ConnectionEntity(
    @PrimaryKey
    val id: String,
    val workflowId: String,
    val fromNodeId: String,
    val fromPortId: String,
    val toNodeId: String,
    val toPortId: String,
    val dataType: String,
    val waypoints: String
)
