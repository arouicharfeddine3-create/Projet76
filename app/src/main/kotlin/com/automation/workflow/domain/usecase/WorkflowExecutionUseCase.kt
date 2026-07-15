package com.automation.workflow.domain.usecase

import com.automation.workflow.data.repository.WorkflowRepository
import com.automation.workflow.data.repository.GraphRepository
import com.automation.workflow.domain.models.*
import kotlinx.coroutines.flow.Flow

class WorkflowExecutionUseCase(
    private val workflowRepository: WorkflowRepository,
    private val graphRepository: GraphRepository
) {

    suspend fun executeWorkflow(
        workflowId: String,
        mode: ExecutionMode = ExecutionMode.FULL,
        startNodeId: String? = null,
        endNodeId: String? = null,
        breakpoints: Set<String> = emptySet(),
        mockData: Map<String, Any> = emptyMap()
    ): ExecutionLog {
        val workflow = workflowRepository.getWorkflow(workflowId)
            ?: throw IllegalArgumentException("Workflow not found")

        val log = ExecutionLog(
            id = "exec_${System.currentTimeMillis()}",
            workflowId = workflowId,
            startTime = java.time.LocalDateTime.now().toString(),
            status = ExecutionStatus.PENDING,
            logs = emptyList(),
            nodeExecutions = emptyMap()
        )

        return log
    }

    suspend fun validateWorkflow(workflowId: String): ValidationResult {
        val workflow = workflowRepository.getWorkflow(workflowId)
            ?: return ValidationResult(false, listOf("Workflow not found"))

        val errors = mutableListOf<String>()

        // Validate nodes
        if (workflow.nodes.isEmpty()) {
            errors.add("Workflow has no nodes")
        }

        // Check for triggers
        val hasTrigger = workflow.nodes.any { it.type.name.startsWith("TRIGGER") }
        if (!hasTrigger) {
            errors.add("Workflow must have at least one trigger")
        }

        // Validate connections
        for (connection in workflow.connections) {
            val fromNode = workflow.nodes.find { it.id == connection.fromNodeId }
            val toNode = workflow.nodes.find { it.id == connection.toNodeId }

            if (fromNode == null) errors.add("Connection from unknown node: ${connection.fromNodeId}")
            if (toNode == null) errors.add("Connection to unknown node: ${connection.toNodeId}")
        }

        return ValidationResult(errors.isEmpty(), errors)
    }

    data class ValidationResult(
        val isValid: Boolean,
        val errors: List<String>
    )
}
