package com.automation.workflow.data.database.dao

import androidx.room.*
import com.automation.workflow.data.database.entities.SettingsEntity
import com.automation.workflow.data.database.entities.SecretKeyEntity
import com.automation.workflow.data.database.entities.GlobalVariableEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(settings: SettingsEntity)

    @Update
    suspend fun update(settings: SettingsEntity)

    @Query("SELECT * FROM settings WHERE id = :id")
    fun getFlow(id: String = "default"): Flow<SettingsEntity?>

    @Query("SELECT * FROM settings WHERE id = :id")
    suspend fun get(id: String = "default"): SettingsEntity?
}

@Dao
interface SecretKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(secret: SecretKeyEntity)

    @Update
    suspend fun update(secret: SecretKeyEntity)

    @Delete
    suspend fun delete(secret: SecretKeyEntity)

    @Query("SELECT * FROM secret_keys WHERE id = :id")
    suspend fun getById(id: String): SecretKeyEntity?

    @Query("SELECT * FROM secret_keys WHERE name = :name")
    suspend fun getByName(name: String): SecretKeyEntity?

    @Query("SELECT * FROM secret_keys ORDER BY name")
    fun getAllFlow(): Flow<List<SecretKeyEntity>>

    @Query("SELECT * FROM secret_keys WHERE type = :type ORDER BY name")
    fun getByTypeFlow(type: String): Flow<List<SecretKeyEntity>>

    @Query("DELETE FROM secret_keys WHERE id = :id")
    suspend fun deleteById(id: String)
}

@Dao
interface GlobalVariableDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(variable: GlobalVariableEntity)

    @Update
    suspend fun update(variable: GlobalVariableEntity)

    @Delete
    suspend fun delete(variable: GlobalVariableEntity)

    @Query("SELECT * FROM global_variables WHERE id = :id")
    suspend fun getById(id: String): GlobalVariableEntity?

    @Query("SELECT * FROM global_variables WHERE name = :name")
    suspend fun getByName(name: String): GlobalVariableEntity?

    @Query("SELECT * FROM global_variables ORDER BY name")
    fun getAllFlow(): Flow<List<GlobalVariableEntity>>

    @Query("SELECT * FROM global_variables WHERE scope = :scope ORDER BY name")
    fun getByScopeFlow(scope: String): Flow<List<GlobalVariableEntity>>

    @Query("DELETE FROM global_variables WHERE id = :id")
    suspend fun deleteById(id: String)
}
