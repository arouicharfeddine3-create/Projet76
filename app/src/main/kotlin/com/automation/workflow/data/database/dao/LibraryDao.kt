package com.automation.workflow.data.database.dao

import androidx.room.*
import com.automation.workflow.data.database.entities.ScriptLibraryEntity
import com.automation.workflow.data.database.entities.BrowserConnectorEntity
import com.automation.workflow.data.database.entities.SelectorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScriptLibraryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(script: ScriptLibraryEntity)

    @Update
    suspend fun update(script: ScriptLibraryEntity)

    @Delete
    suspend fun delete(script: ScriptLibraryEntity)

    @Query("SELECT * FROM script_library WHERE id = :id")
    suspend fun getById(id: String): ScriptLibraryEntity?

    @Query("SELECT * FROM script_library WHERE type = :type ORDER BY updatedAt DESC")
    fun getByTypeFlow(type: String): Flow<List<ScriptLibraryEntity>>

    @Query("SELECT * FROM script_library WHERE name LIKE '%' || :query || '%' ORDER BY updatedAt DESC")
    fun searchFlow(query: String): Flow<List<ScriptLibraryEntity>>

    @Query("SELECT * FROM script_library WHERE isPublic = 1 ORDER BY rating DESC LIMIT 50")
    fun getPublicFlow(): Flow<List<ScriptLibraryEntity>>

    @Query("DELETE FROM script_library WHERE id = :id")
    suspend fun deleteById(id: String)
}

@Dao
interface BrowserConnectorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(connector: BrowserConnectorEntity)

    @Update
    suspend fun update(connector: BrowserConnectorEntity)

    @Delete
    suspend fun delete(connector: BrowserConnectorEntity)

    @Query("SELECT * FROM browser_connectors WHERE id = :id")
    suspend fun getById(id: String): BrowserConnectorEntity?

    @Query("SELECT * FROM browser_connectors ORDER BY updatedAt DESC")
    fun getAllFlow(): Flow<List<BrowserConnectorEntity>>

    @Query("SELECT * FROM browser_connectors WHERE name LIKE '%' || :query || '%' ORDER BY updatedAt DESC")
    fun searchFlow(query: String): Flow<List<BrowserConnectorEntity>>

    @Query("DELETE FROM browser_connectors WHERE id = :id")
    suspend fun deleteById(id: String)
}

@Dao
interface SelectorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(selector: SelectorEntity)

    @Update
    suspend fun update(selector: SelectorEntity)

    @Delete
    suspend fun delete(selector: SelectorEntity)

    @Query("SELECT * FROM selectors WHERE id = :id")
    suspend fun getById(id: String): SelectorEntity?

    @Query("SELECT * FROM selectors ORDER BY createdAt DESC")
    fun getAllFlow(): Flow<List<SelectorEntity>>

    @Query("SELECT * FROM selectors WHERE type = :type ORDER BY createdAt DESC")
    fun getByTypeFlow(type: String): Flow<List<SelectorEntity>>

    @Query("SELECT * FROM selectors WHERE name LIKE '%' || :query || '%' ORDER BY createdAt DESC")
    fun searchFlow(query: String): Flow<List<SelectorEntity>>

    @Query("DELETE FROM selectors WHERE id = :id")
    suspend fun deleteById(id: String)
}
