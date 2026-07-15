package com.automation.workflow.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.automation.workflow.data.repository.LibraryRepository
import com.automation.workflow.domain.models.ScriptLibraryItem
import com.automation.workflow.domain.models.BrowserConnector
import com.automation.workflow.domain.models.Selector
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LibraryViewModel(
    private val libraryRepository: LibraryRepository
) : ViewModel() {

    private val _scripts = MutableStateFlow<List<ScriptLibraryItem>>(emptyList())
    val scripts: StateFlow<List<ScriptLibraryItem>> = _scripts.asStateFlow()

    private val _connectors = MutableStateFlow<List<BrowserConnector>>(emptyList())
    val connectors: StateFlow<List<BrowserConnector>> = _connectors.asStateFlow()

    private val _selectors = MutableStateFlow<List<Selector>>(emptyList())
    val selectors: StateFlow<List<Selector>> = _selectors.asStateFlow()

    private val _selectedItem = MutableStateFlow<Any?>(null)
    val selectedItem: StateFlow<Any?> = _selectedItem.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadAllLibraries()
    }

    private fun loadAllLibraries() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                libraryRepository.getAllConnectors().collect { connectorList ->
                    _connectors.value = connectorList
                }
                libraryRepository.getAllSelectors().collect { selectorList ->
                    _selectors.value = selectorList
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addScript(script: ScriptLibraryItem) {
        viewModelScope.launch {
            try {
                libraryRepository.addScript(script)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun addConnector(connector: BrowserConnector) {
        viewModelScope.launch {
            try {
                libraryRepository.addConnector(connector)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun addSelector(selector: Selector) {
        viewModelScope.launch {
            try {
                libraryRepository.addSelector(selector)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun deleteConnector(connectorId: String) {
        viewModelScope.launch {
            try {
                libraryRepository.deleteConnector(connectorId)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun deleteSelector(selectorId: String) {
        viewModelScope.launch {
            try {
                libraryRepository.deleteSelector(selectorId)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun selectItem(item: Any?) {
        _selectedItem.value = item
    }
}
