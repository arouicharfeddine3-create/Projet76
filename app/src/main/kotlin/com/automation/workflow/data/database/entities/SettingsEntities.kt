package com.automation.workflow.data.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "settings",
    indices = [Index("id")]
)
data class SettingsEntity(
    @PrimaryKey
    val id: String = "default",
    val theme: String,
    val language: String,
    val fontSize: Float,
    val editorFontSize: Float,
    val logFontSize: Float,
    val accentColor: String,
    val enableNotifications: Boolean,
    val notificationSound: Boolean,
    val vibrationEnabled: Boolean,
    val batteryOptimization: Boolean,
    val cellularDataLimit: Boolean,
    val cellularDataLimitMB: Int,
    val nodeTimeout: Long,
    val parallelExecutionLimit: Int,
    val enableAutoSave: Boolean,
    val autoSaveIntervalSeconds: Int,
    val enableWebViewDebug: Boolean,
    val enableSystemLogs: Boolean,
    val backupEncryption: Boolean,
    val biometricEnabled: Boolean,
    val pinEnabled: Boolean,
    val pin: String?,
    val workspaceEncryption: Boolean,
    val encryptionPassword: String?
)

@Entity(
    tableName = "secret_keys",
    indices = [Index("name"), Index("type")]
)
data class SecretKeyEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val value: String,
    val type: String,
    val description: String,
    val createdAt: String,
    val expiresAt: String?,
    val isEncrypted: Boolean
)

@Entity(
    tableName = "global_variables",
    indices = [Index("name"), Index("scope")]
)
data class GlobalVariableEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val value: String,
    val type: String,
    val description: String,
    val isSecret: Boolean,
    val scope: String
)
