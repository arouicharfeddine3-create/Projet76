package com.automation.workflow.data.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "script_library",
    indices = [Index("type"), Index("name"), Index("tags")]
)
data class ScriptLibraryEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val type: String,
    val content: String,
    val description: String,
    val tags: String,
    val version: String,
    val versions: String,
    val isPublic: Boolean,
    val rating: Float,
    val downloads: Int,
    val author: String,
    val createdAt: String,
    val updatedAt: String,
    val dependencies: String,
    val testData: String?
)

@Entity(
    tableName = "browser_connectors",
    indices = [Index("name"), Index("tags")]
)
data class BrowserConnectorEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val steps: String,
    val tags: String,
    val version: String,
    val variables: String,
    val createdAt: String,
    val updatedAt: String,
    val isPublic: Boolean
)

@Entity(
    tableName = "selectors",
    indices = [Index("name"), Index("type"), Index("tags")]
)
data class SelectorEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val type: String,
    val value: String,
    val testText: String?,
    val tags: String,
    val createdAt: String,
    val isPublic: Boolean
)
