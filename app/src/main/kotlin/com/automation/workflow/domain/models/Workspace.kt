package com.automation.workflow.domain.models

import kotlinx.serialization.Serializable
import java.io.File

@Serializable
data class WorkspaceFile(
    val id: String,
    val name: String,
    val path: String,
    val type: FileType,
    val size: Long = 0L,
    val mimeType: String = "",
    val createdAt: String = "",
    val updatedAt: String = "",
    val isDirectory: Boolean = false,
    val children: List<WorkspaceFile> = emptyList(),
    val isFavorite: Boolean = false,
    val tags: List<String> = emptyList(),
    val checksum: String? = null
)

enum class FileType {
    TEXT,
    JSON,
    CSV,
    JAVASCRIPT,
    PYTHON,
    MARKDOWN,
    IMAGE,
    PDF,
    VIDEO,
    AUDIO,
    ARCHIVE,
    DIRECTORY,
    UNKNOWN
}

@Serializable
data class CloudSyncConfig(
    val id: String,
    val provider: CloudProvider,
    val isEnabled: Boolean,
    val accessToken: String? = null,
    val refreshToken: String? = null,
    val syncFolder: String = "",
    val lastSyncTime: String? = null,
    val autoSync: Boolean = true,
    val conflictResolution: ConflictResolution = ConflictResolution.MANUAL
)

enum class CloudProvider {
    GOOGLE_DRIVE,
    DROPBOX,
    AWS_S3,
    WEBDAV,
    LOCAL_GIT
}

enum class ConflictResolution {
    MANUAL,
    KEEP_LOCAL,
    KEEP_REMOTE,
    MERGE
}

@Serializable
data class GitRepository(
    val id: String,
    val url: String,
    val branch: String = "main",
    val localPath: String,
    val username: String? = null,
    val lastCommitHash: String? = null,
    val lastPullTime: String? = null
)
