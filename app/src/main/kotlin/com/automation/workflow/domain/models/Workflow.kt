package com.automation.workflow.domain.models

import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Workflow(
    val id: String,
    val name: String,
    val description: String = "",
    val nodes: List<Node> = emptyList(),
    val connections: List<Connection> = emptyList(),
    val globalVariables: Map<String, Any> = emptyMap(),
    val triggers: List<WorkflowTrigger> = emptyList(),
    val isActive: Boolean = true,
    val tags: List<String> = emptyList(),
    val version: String = "1.0.0",
    val createdAt: String = LocalDateTime.now().toString(),
    val updatedAt: String = LocalDateTime.now().toString(),
    val author: String = "",
    val thumbnail: String? = null
)

@Serializable
data class WorkflowTrigger(
    val id: String,
    val nodeId: String,
    val cronExpression: String? = null,
    val webhookUrl: String? = null,
    val isEnabled: Boolean = true,
    val executionMode: ExecutionMode = ExecutionMode.FULL
)

enum class ExecutionMode {
    FULL,
    SELECTIVE,
    DEBUG,
    PARALLEL
}

@Serializable
data class ExecutionLog(
    val id: String,
    val workflowId: String,
    val startTime: String,
    val endTime: String? = null,
    val status: ExecutionStatus,
    val logs: List<LogEntry> = emptyList(),
    val nodeExecutions: Map<String, NodeExecution> = emptyMap(),
    val totalDuration: Long = 0L,
    val memoryUsed: Long = 0L,
    val batteryConsumed: Float = 0f
)

enum class ExecutionStatus {
    PENDING,
    RUNNING,
    SUCCESS,
    FAILED,
    CANCELLED,
    PAUSED
}

@Serializable
data class LogEntry(
    val timestamp: String,
    val level: LogLevel,
    val nodeId: String? = null,
    val nodeName: String? = null,
    val message: String,
    val details: Map<String, Any>? = null
)

enum class LogLevel {
    DEBUG,
    INFO,
    WARNING,
    ERROR,
    SUCCESS
}

@Serializable
data class NodeExecution(
    val nodeId: String,
    val nodeName: String,
    val nodeType: NodeType,
    val status: ExecutionStatus,
    val startTime: String,
    val endTime: String? = null,
    val duration: Long = 0L,
    val inputs: Map<String, Any>? = null,
    val outputs: Map<String, Any>? = null,
    val error: String? = null,
    val retryCount: Int = 0
)
