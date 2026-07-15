package com.automation.workflow.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val id: String = "default",
    val theme: AppTheme = AppTheme.SYSTEM,
    val language: AppLanguage = AppLanguage.EN,
    val fontSize: Float = 14f,
    val editorFontSize: Float = 12f,
    val logFontSize: Float = 11f,
    val accentColor: String = "#2196F3",
    val enableNotifications: Boolean = true,
    val notificationSound: Boolean = true,
    val vibrationEnabled: Boolean = true,
    val batteryOptimization: Boolean = true,
    val cellularDataLimit: Boolean = false,
    val cellularDataLimitMB: Int = 100,
    val nodeTimeout: Long = 30000L,
    val parallelExecutionLimit: Int = 5,
    val enableAutoSave: Boolean = true,
    val autoSaveIntervalSeconds: Int = 60,
    val enableWebViewDebug: Boolean = false,
    val enableSystemLogs: Boolean = false,
    val backupEncryption: Boolean = true,
    val biometricEnabled: Boolean = false,
    val pinEnabled: Boolean = false,
    val pin: String? = null,
    val workspaceEncryption: Boolean = false,
    val encryptionPassword: String? = null
)

enum class AppTheme {
    LIGHT,
    DARK,
    SYSTEM
}

enum class AppLanguage {
    EN,
    AR
}

@Serializable
data class SecretKey(
    val id: String,
    val name: String,
    val value: String,
    val type: SecretType,
    val description: String = "",
    val createdAt: String = "",
    val expiresAt: String? = null,
    val isEncrypted: Boolean = true
)

enum class SecretType {
    API_KEY,
    BEARER_TOKEN,
    BASIC_AUTH,
    OAUTH2,
    WEBHOOK_SECRET,
    DATABASE_CONNECTION,
    SSH_KEY,
    CUSTOM
}

@Serializable
data class GlobalVariable(
    val id: String,
    val name: String,
    val value: Any?,
    val type: DataType,
    val description: String = "",
    val isSecret: Boolean = false,
    val scope: VariableScope = VariableScope.GLOBAL
)

enum class VariableScope {
    GLOBAL,
    WORKFLOW,
    EXECUTION
}
