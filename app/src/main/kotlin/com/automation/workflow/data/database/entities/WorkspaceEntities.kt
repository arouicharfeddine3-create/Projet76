package com.automation.workflow.data.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "workspace_files",
    indices = [Index("path"), Index("type"), Index("name")]
)
data class WorkspaceFileEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val path: String,
    val type: String,
    val size: Long,
    val mimeType: String,
    val createdAt: String,
    val updatedAt: String,
    val isDirectory: Boolean,
    val parentId: String?,
    val isFavorite: Boolean,
    val tags: String,
    val checksum: String?
)
