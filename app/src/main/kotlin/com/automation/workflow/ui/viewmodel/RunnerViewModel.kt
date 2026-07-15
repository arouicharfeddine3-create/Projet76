package com.automation.workflow.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.automation.workflow.data.repository.WorkflowRepository
import com.automation.workflow.domain.models.ExecutionLog
import com.automation.workflow.domain.models.ExecutionStatus
import com.automation.workflow.domain.models.LogLevel
import com.automation.workflow.domain.models.ExecutionMode
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RunnerViewModel(
    private val workflowRepository: WorkflowRepository
) : ViewModel() {

    private val _selectedWorkflowId = MutableStateFlow<String?>(null)
    val selectedWorkflowId: StateFlow<String?> = _selectedWorkflowId.asStateFlow()

    private val _executionMode = MutableStateFlow(ExecutionMode.FULL)
    val executionMode: StateFlow<ExecutionMode> = _executionMode.asStateFlow()

    private val _currentExecution = MutableStateFlow<ExecutionLog?>(null)
    val currentExecution: StateFlow<ExecutionLog?> = _currentExecution.asStateFlow()

    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning.asStateFlow()

    private val _payloadJson = MutableStateFlow("")
    val payloadJson: StateFlow<String> = _payloadJson.asStateFlow()

    private val _cronExpression = MutableStateFlow("")
    val cronExpression: StateFlow<String> = _cronExpression.asStateFlow()

    private val _logFilter = MutableStateFlow<LogLevel?>(null)
    val logFilter: StateFlow<LogLevel?> = _logFilter.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun selectWorkflow(workflowId: String) {
        _selectedWorkflowId.value = workflowId
    }

    fun setExecutionMode(mode: ExecutionMode) {
        _executionMode.value = mode
    }

    fun setPayload(json: String) {
        _payloadJson.value = json
    }

    fun setCronExpression(expr: String) {
        _cronExpression.value = expr
    }

    fun setLogFilter(level: LogLevel?) {
        _logFilter.value = level
    }

    fun executeWorkflow() {
        viewModelScope.launch {
            _isRunning.value = true
            try {
                val workflowId = _selectedWorkflowId.value ?: return@launch
                // Execution logic will be implemented in WorkflowExecutionService
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isRunning.value = false
            }
        }
    }

    fun pauseExecution() {
        // Pause logic
    }

    fun stopExecution() {
        // Stop logic
    }

    fun retryExecution() {
        executeWorkflow()
    }
}
