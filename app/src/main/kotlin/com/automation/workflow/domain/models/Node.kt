package com.automation.workflow.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Node(
    val id: String,
    val type: NodeType,
    val name: String,
    val description: String = "",
    val x: Float = 0f,
    val y: Float = 0f,
    val width: Float = 200f,
    val height: Float = 100f,
    val config: Map<String, Any> = emptyMap(),
    val inputPorts: List<Port> = emptyList(),
    val outputPorts: List<Port> = emptyList(),
    val script: String? = null,
    val tags: List<String> = emptyList(),
    val isBreakpoint: Boolean = false,
    val errorOutput: String? = null,
    val timeout: Long = 30000L,
    val retryCount: Int = 0
)

@Serializable
data class Port(
    val id: String,
    val name: String,
    val type: DataType,
    val isInput: Boolean,
    val defaultValue: Any? = null,
    val isRequired: Boolean = false
)

@Serializable
data class Connection(
    val id: String,
    val fromNodeId: String,
    val fromPortId: String,
    val toNodeId: String,
    val toPortId: String,
    val dataType: DataType = DataType.ANY,
    val waypoints: List<Waypoint> = emptyList()
)

@Serializable
data class Waypoint(
    val x: Float,
    val y: Float
)

enum class NodeType {
    // Triggers
    TRIGGER_MANUAL,
    TRIGGER_WEBHOOK,
    TRIGGER_CRON,
    TRIGGER_BROWSER_EVENT,
    TRIGGER_FILE_WATCHER,
    TRIGGER_NFC,
    TRIGGER_QR_CODE,

    // HTTP
    HTTP_REQUEST,

    // Browser
    BROWSER,
    SELECTOR,
    RACCORD,
    EXTRACT_FILES,

    // Code
    JAVASCRIPT,
    PYTHON,

    // Flow Control
    CONDITION,
    SWITCH,
    LOOP,
    MERGE,

    // Data
    SET_VARIABLE,
    LOG,
    DELAY,
    FILES,
    NOTE,

    // Navigation
    GO_TO_SCREEN,
    CLOSE_SCREEN,
    SAVE_TO_SCREEN,
    READ_FROM_SCREEN,
    SHOW_NOTIFICATION,

    // AI
    AI_NODE,

    // Advanced
    PASSTHROUGH,
    ERROR_HANDLER,
    COMMENT
}

enum class DataType {
    STRING,
    NUMBER,
    BOOLEAN,
    ARRAY,
    OBJECT,
    FILE,
    JSON,
    HTML,
    IMAGE,
    ANY
}
