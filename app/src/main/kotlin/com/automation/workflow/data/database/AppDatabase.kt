package com.automation.workflow.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.automation.workflow.data.database.dao.*
import com.automation.workflow.data.database.entities.*

@Database(
    entities = [
        WorkflowEntity::class,
        NodeEntity::class,
        ConnectionEntity::class,
        ExecutionLogEntity::class,
        LogEntryEntity::class,
        ScriptLibraryEntity::class,
        BrowserConnectorEntity::class,
        SelectorEntity::class,
        WorkspaceFileEntity::class,
        SettingsEntity::class,
        SecretKeyEntity::class,
        GlobalVariableEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workflowDao(): WorkflowDao
    abstract fun nodeDao(): NodeDao
    abstract fun connectionDao(): ConnectionDao
    abstract fun executionLogDao(): ExecutionLogDao
    abstract fun scriptLibraryDao(): ScriptLibraryDao
    abstract fun browserConnectorDao(): BrowserConnectorDao
    abstract fun selectorDao(): SelectorDao
    abstract fun workspaceFileDao(): WorkspaceFileDao
    abstract fun settingsDao(): SettingsDao
    abstract fun secretKeyDao(): SecretKeyDao
    abstract fun globalVariableDao(): GlobalVariableDao
}
