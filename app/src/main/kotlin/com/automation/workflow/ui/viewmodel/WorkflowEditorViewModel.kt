package com.automation.workflow.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.automation.workflow.data.repository.WorkflowRepository
import com.automation.workflow.data.repository.GraphRepository
import com.automation.workflow.domain.models.Workflow
import com.automation.workflow.domain.models.Node
import com.automation.workflow.domain.models.Connection
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WorkflowEditorViewModel(
    private val workflowRepository: WorkflowRepository,
    private val graphRepository: GraphRepository
) : ViewModel() {

    private val _currentWorkflow = MutableStateFlow<Workflow?>(null)
    val currentWorkflow: StateFlow<Workflow?> = _currentWorkflow.asStateFlow()

    private val _nodes = MutableStateFlow<List<Node>>(emptyList())
    val nodes: StateFlow<List<Node>> = _nodes.asStateFlow()

    private val _connections = MutableStateFlow<List<Connection>>(emptyList())
    val connections: StateFlow<List<Connection>> = _connections.asStateFlow()

    private val _selectedNodeId = MutableStateFlow<String?>(null)
    val selectedNodeId: StateFlow<String?> = _selectedNodeId.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadWorkflow(workflowId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val workflow = workflowRepository.getWorkflow(workflowId)
                _currentWorkflow.value = workflow
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addNode(node: Node) {
        viewModelScope.launch {
            try {
                val workflowId = _currentWorkflow.value?.id ?: return@launch
                graphRepository.addNode(workflowId, node)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun updateNode(node: Node) {
        viewModelScope.launch {
            try {
                val workflowId = _currentWorkflow.value?.id ?: return@launch
                graphRepository.updateNode(workflowId, node)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun deleteNode(nodeId: String) {
        viewModelScope.launch {
            try {
                graphRepository.deleteNode(nodeId)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun addConnection(connection: Connection) {
        viewModelScope.launch {
            try {
                val workflowId = _currentWorkflow.value?.id ?: return@launch
                graphRepository.addConnection(workflowId, connection)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun deleteConnection(connectionId: String) {
        viewModelScope.launch {
            try {
                graphRepository.deleteConnection(connectionId)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun selectNode(nodeId: String?) {
        _selectedNodeId.value = nodeId
    }

    fun saveWorkflow(workflow: Workflow) {
        viewModelScope.launch {
            try {
                workflowRepository.updateWorkflow(workflow)
                _currentWorkflow.value = workflow
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}
