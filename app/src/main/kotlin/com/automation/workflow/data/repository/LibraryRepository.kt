package com.automation.workflow.data.repository

import com.automation.workflow.data.database.dao.ScriptLibraryDao
import com.automation.workflow.data.database.dao.BrowserConnectorDao
import com.automation.workflow.data.database.dao.SelectorDao
import com.automation.workflow.domain.models.ScriptLibraryItem
import com.automation.workflow.domain.models.BrowserConnector
import com.automation.workflow.domain.models.Selector
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LibraryRepository(
    private val scriptLibraryDao: ScriptLibraryDao,
    private val browserConnectorDao: BrowserConnectorDao,
    private val selectorDao: SelectorDao
) {

    // Scripts
    suspend fun addScript(script: ScriptLibraryItem) {
        scriptLibraryDao.insert(script.toScriptEntity())
    }

    suspend fun updateScript(script: ScriptLibraryItem) {
        scriptLibraryDao.update(script.toScriptEntity())
    }

    suspend fun deleteScript(scriptId: String) {
        scriptLibraryDao.deleteById(scriptId)
    }

    fun getScriptsByType(type: String): Flow<List<ScriptLibraryItem>> {
        return scriptLibraryDao.getByTypeFlow(type).map { entities ->
            entities.map { it.toDomainScript() }
        }
    }

    fun searchScripts(query: String): Flow<List<ScriptLibraryItem>> {
        return scriptLibraryDao.searchFlow(query).map { entities ->
            entities.map { it.toDomainScript() }
        }
    }

    // Connectors
    suspend fun addConnector(connector: BrowserConnector) {
        browserConnectorDao.insert(connector.toConnectorEntity())
    }

    suspend fun updateConnector(connector: BrowserConnector) {
        browserConnectorDao.update(connector.toConnectorEntity())
    }

    suspend fun deleteConnector(connectorId: String) {
        browserConnectorDao.deleteById(connectorId)
    }

    fun getAllConnectors(): Flow<List<BrowserConnector>> {
        return browserConnectorDao.getAllFlow().map { entities ->
            entities.map { it.toDomainConnector() }
        }
    }

    fun searchConnectors(query: String): Flow<List<BrowserConnector>> {
        return browserConnectorDao.searchFlow(query).map { entities ->
            entities.map { it.toDomainConnector() }
        }
    }

    // Selectors
    suspend fun addSelector(selector: Selector) {
        selectorDao.insert(selector.toSelectorEntity())
    }

    suspend fun updateSelector(selector: Selector) {
        selectorDao.update(selector.toSelectorEntity())
    }

    suspend fun deleteSelector(selectorId: String) {
        selectorDao.deleteById(selectorId)
    }

    fun getAllSelectors(): Flow<List<Selector>> {
        return selectorDao.getAllFlow().map { entities ->
            entities.map { it.toDomainSelector() }
        }
    }

    fun searchSelectors(query: String): Flow<List<Selector>> {
        return selectorDao.searchFlow(query).map { entities ->
            entities.map { it.toDomainSelector() }
        }
    }

    private fun ScriptLibraryItem.toScriptEntity() =
        com.automation.workflow.data.database.entities.ScriptLibraryEntity(
            id = id,
            name = name,
            type = type.name,
            content = content,
            description = description,
            tags = com.google.gson.Gson().toJson(tags),
            version = version,
            versions = com.google.gson.Gson().toJson(versions),
            isPublic = isPublic,
            rating = rating,
            downloads = downloads,
            author = author,
            createdAt = createdAt,
            updatedAt = updatedAt,
            dependencies = com.google.gson.Gson().toJson(dependencies),
            testData = testData
        )

    private fun com.automation.workflow.data.database.entities.ScriptLibraryEntity.toDomainScript() =
        ScriptLibraryItem(
            id = id,
            name = name,
            type = com.automation.workflow.domain.models.ScriptType.valueOf(type),
            content = content,
            description = description,
            tags = com.google.gson.Gson().fromJson(tags, List::class.java) as? List<String> ?: emptyList(),
            version = version,
            versions = com.google.gson.Gson().fromJson(versions, List::class.java) as? List<com.automation.workflow.domain.models.ScriptVersion> ?: emptyList(),
            isPublic = isPublic,
            rating = rating,
            downloads = downloads,
            author = author,
            createdAt = createdAt,
            updatedAt = updatedAt,
            dependencies = com.google.gson.Gson().fromJson(dependencies, List::class.java) as? List<String> ?: emptyList(),
            testData = testData
        )

    private fun BrowserConnector.toConnectorEntity() =
        com.automation.workflow.data.database.entities.BrowserConnectorEntity(
            id = id,
            name = name,
            description = description,
            steps = com.google.gson.Gson().toJson(steps),
            tags = com.google.gson.Gson().toJson(tags),
            version = version,
            variables = com.google.gson.Gson().toJson(variables),
            createdAt = createdAt,
            updatedAt = updatedAt,
            isPublic = isPublic
        )

    private fun com.automation.workflow.data.database.entities.BrowserConnectorEntity.toDomainConnector() =
        BrowserConnector(
            id = id,
            name = name,
            description = description,
            steps = com.google.gson.Gson().fromJson(steps, List::class.java) as? List<com.automation.workflow.domain.models.BrowserStep> ?: emptyList(),
            tags = com.google.gson.Gson().fromJson(tags, List::class.java) as? List<String> ?: emptyList(),
            version = version,
            variables = com.google.gson.Gson().fromJson(variables, List::class.java) as? List<com.automation.workflow.domain.models.ConnectorVariable> ?: emptyList(),
            createdAt = createdAt,
            updatedAt = updatedAt,
            isPublic = isPublic
        )

    private fun Selector.toSelectorEntity() =
        com.automation.workflow.data.database.entities.SelectorEntity(
            id = id,
            name = name,
            type = type.name,
            value = value,
            testText = testText,
            tags = com.google.gson.Gson().toJson(tags),
            createdAt = createdAt,
            isPublic = isPublic
        )

    private fun com.automation.workflow.data.database.entities.SelectorEntity.toDomainSelector() =
        Selector(
            id = id,
            name = name,
            type = com.automation.workflow.domain.models.SelectorType.valueOf(type),
            value = value,
            testText = testText,
            tags = com.google.gson.Gson().fromJson(tags, List::class.java) as? List<String> ?: emptyList(),
            createdAt = createdAt,
            isPublic = isPublic
        )
}
