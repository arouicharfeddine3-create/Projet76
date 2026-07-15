package com.automation.workflow.data.repository

import com.automation.workflow.data.database.dao.WorkflowDao
import com.automation.workflow.data.database.entities.WorkflowEntity
import com.automation.workflow.domain.models.Workflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WorkflowRepository(private val workflowDao: WorkflowDao) {

    suspend fun createWorkflow(workflow: Workflow) {
        workflowDao.insert(workflow.toEntity())
    }

    suspend fun updateWorkflow(workflow: Workflow) {
        workflowDao.update(workflow.toEntity())
    }

    suspend fun deleteWorkflow(workflowId: String) {
        workflowDao.deleteById(workflowId)
    }

    suspend fun getWorkflow(workflowId: String): Workflow? {
        return workflowDao.getById(workflowId)?.toDomain()
    }

    fun getAllWorkflows(): Flow<List<Workflow>> {
        return workflowDao.getAllFlow().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    fun searchWorkflows(query: String): Flow<List<Workflow>> {
        return workflowDao.searchFlow(query).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    fun getActiveWorkflows(): Flow<List<Workflow>> {
        return workflowDao.getActiveFlow().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    suspend fun getWorkflowCount(): Int {
        return workflowDao.count()
    }

    private fun WorkflowEntity.toDomain(): Workflow {
        return Workflow(
            id = id,
            name = name,
            description = description,
            globalVariables = com.google.gson.Gson().fromJson(globalVariables, Map::class.java) as? Map<String, Any> ?: emptyMap(),
            isActive = isActive,
            tags = com.google.gson.Gson().fromJson(tags, List::class.java) as? List<String> ?: emptyList(),
            version = version,
            createdAt = createdAt,
            updatedAt = updatedAt,
            author = author,
            thumbnail = thumbnail
        )
    }

    private fun Workflow.toEntity(): WorkflowEntity {
        return WorkflowEntity(
            id = id,
            name = name,
            description = description,
            globalVariables = com.google.gson.Gson().toJson(globalVariables),
            isActive = isActive,
            tags = com.google.gson.Gson().toJson(tags),
            version = version,
            createdAt = createdAt,
            updatedAt = updatedAt,
            author = author,
            thumbnail = thumbnail
        )
    }
}
