package com.automation.workflow.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class ScriptLibraryItem(
    val id: String,
    val name: String,
    val type: ScriptType,
    val content: String,
    val description: String = "",
    val tags: List<String> = emptyList(),
    val version: String = "1.0.0",
    val versions: List<ScriptVersion> = emptyList(),
    val isPublic: Boolean = false,
    val rating: Float = 0f,
    val downloads: Int = 0,
    val author: String = "",
    val createdAt: String = "",
    val updatedAt: String = "",
    val dependencies: List<String> = emptyList(),
    val testData: String? = null
)

enum class ScriptType {
    JAVASCRIPT,
    PYTHON
}

@Serializable
data class ScriptVersion(
    val version: String,
    val content: String,
    val createdAt: String,
    val changelog: String = ""
)

@Serializable
data class BrowserConnector(
    val id: String,
    val name: String,
    val description: String = "",
    val steps: List<BrowserStep> = emptyList(),
    val tags: List<String> = emptyList(),
    val version: String = "1.0.0",
    val variables: List<ConnectorVariable> = emptyList(),
    val createdAt: String = "",
    val updatedAt: String = "",
    val isPublic: Boolean = false
)

@Serializable
data class BrowserStep(
    val id: String,
    val type: BrowserStepType,
    val selector: String? = null,
    val value: String? = null,
    val delay: Long = 0L,
    val timeout: Long = 5000L,
    val isOptional: Boolean = false,
    val config: Map<String, Any> = emptyMap()
)

enum class BrowserStepType {
    CLICK,
    TYPE,
    SCROLL,
    WAIT_FOR_SELECTOR,
    WAIT_FOR_NAVIGATION,
    SCREENSHOT,
    EXTRACT_TEXT,
    EXTRACT_HTML,
    EXTRACT_ATTRIBUTE,
    EXECUTE_SCRIPT,
    HOVER,
    DRAG_DROP,
    KEYBOARD_PRESS,
    FILE_UPLOAD
}

@Serializable
data class ConnectorVariable(
    val name: String,
    val type: DataType,
    val defaultValue: Any? = null,
    val description: String = ""
)

@Serializable
data class Selector(
    val id: String,
    val name: String,
    val type: SelectorType,
    val value: String,
    val testText: String? = null,
    val tags: List<String> = emptyList(),
    val createdAt: String = "",
    val isPublic: Boolean = false
)

enum class SelectorType {
    CSS,
    XPATH,
    ID,
    CLASS_NAME,
    TAG_NAME,
    ATTRIBUTE
}
