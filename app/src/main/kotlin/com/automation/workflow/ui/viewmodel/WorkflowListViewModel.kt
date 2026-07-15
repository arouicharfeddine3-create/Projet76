package com.automation.workflow.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.automation.workflow.data.repository.WorkflowRepository
import com.automation.workflow.domain.models.Workflow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WorkflowListViewModel(
    private val workflowRepository: WorkflowRepository
) : ViewModel() {

    private val _workflows = MutableStateFlow<List<Workflow>>(emptyList())
    val workflows: StateFlow<List<Workflow>> = _workflows.asStateFlow()

    private val _activeWorkflows = MutableStateFlow<List<Workflow>>(emptyList())
    val activeWorkflows: StateFlow<List<Workflow>> = _activeWorkflows.asStateFlow()

    private val _searchResults = MutableStateFlow<List<Workflow>>(emptyList())
    val searchResults: StateFlow<List<Workflow>> = _searchResults.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadWorkflows()
    }

    private fun loadWorkflows() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                workflowRepository.getAllWorkflows().collect { workflows ->
                    _workflows.value = workflows
                }
                workflowRepository.getActiveWorkflows().collect { active ->
                    _activeWorkflows.value = active
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchWorkflows(query: String) {
        if (query.isEmpty()) {
            _searchResults.value = emptyList()
            return
        }

        viewModelScope.launch {
            try {
                workflowRepository.searchWorkflows(query).collect { results ->
                    _searchResults.value = results
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun deleteWorkflow(workflowId: String) {
        viewModelScope.launch {
            try {
                workflowRepository.deleteWorkflow(workflowId)
                loadWorkflows()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun createNewWorkflow(): Workflow {
        return Workflow(
            id = "workflow_${System.currentTimeMillis()}",
            name = "New Workflow",
            description = "",
            nodes = emptyList(),
            connections = emptyList()
        )
    }
}
