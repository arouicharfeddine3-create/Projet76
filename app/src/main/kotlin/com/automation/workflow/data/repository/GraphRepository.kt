package com.automation.workflow.data.repository

import com.automation.workflow.data.database.dao.NodeDao
import com.automation.workflow.data.database.dao.ConnectionDao
import com.automation.workflow.data.database.entities.NodeEntity
import com.automation.workflow.data.database.entities.ConnectionEntity
import com.automation.workflow.domain.models.Node
import com.automation.workflow.domain.models.Connection
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GraphRepository(
    private val nodeDao: NodeDao,
    private val connectionDao: ConnectionDao
) {
    private val gson = Gson()

    suspend fun addNode(workflowId: String, node: Node) {
        nodeDao.insert(node.toEntity(workflowId))
    }

    suspend fun updateNode(workflowId: String, node: Node) {
        nodeDao.update(node.toEntity(workflowId))
    }

    suspend fun deleteNode(nodeId: String) {
        nodeDao.deleteById(nodeId)
    }

    suspend fun getNode(nodeId: String): Node? {
        return nodeDao.getById(nodeId)?.toDomain()
    }

    fun getWorkflowNodes(workflowId: String): Flow<List<Node>> {
        return nodeDao.getByWorkflowFlow(workflowId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    suspend fun addConnection(workflowId: String, connection: Connection) {
        connectionDao.insert(connection.toEntity(workflowId))
    }

    suspend fun updateConnection(workflowId: String, connection: Connection) {
        connectionDao.update(connection.toEntity(workflowId))
    }

    suspend fun deleteConnection(connectionId: String) {
        connectionDao.deleteById(connectionId)
    }

    fun getWorkflowConnections(workflowId: String): Flow<List<Connection>> {
        return connectionDao.getByWorkflowFlow(workflowId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    suspend fun getOutgoingConnections(nodeId: String): List<Connection> {
        return connectionDao.getOutgoing(nodeId).map { it.toDomain() }
    }

    suspend fun getIncomingConnections(nodeId: String): List<Connection> {
        return connectionDao.getIncoming(nodeId).map { it.toDomain() }
    }

    private fun Node.toEntity(workflowId: String): NodeEntity {
        return NodeEntity(
            id = id,
            workflowId = workflowId,
            type = type.name,
            name = name,
            description = description,
            x = x,
            y = y,
            width = width,
            height = height,
            config = gson.toJson(config),
            inputPorts = gson.toJson(inputPorts),
            outputPorts = gson.toJson(outputPorts),
            script = script,
            tags = gson.toJson(tags),
            isBreakpoint = isBreakpoint,
            errorOutput = errorOutput,
            timeout = timeout,
            retryCount = retryCount
        )
    }

    private fun NodeEntity.toDomain(): Node {
        return Node(
            id = id,
            type = com.automation.workflow.domain.models.NodeType.valueOf(type),
            name = name,
            description = description,
            x = x,
            y = y,
            width = width,
            height = height,
            config = gson.fromJson(config, Map::class.java) as? Map<String, Any> ?: emptyMap(),
            inputPorts = emptyList(),
            outputPorts = emptyList(),
            script = script,
            tags = gson.fromJson(tags, List::class.java) as? List<String> ?: emptyList(),
            isBreakpoint = isBreakpoint,
            errorOutput = errorOutput,
            timeout = timeout,
            retryCount = retryCount
        )
    }

    private fun Connection.toEntity(workflowId: String): ConnectionEntity {
        return ConnectionEntity(
            id = id,
            workflowId = workflowId,
            fromNodeId = fromNodeId,
            fromPortId = fromPortId,
            toNodeId = toNodeId,
            toPortId = toPortId,
            dataType = dataType.name,
            waypoints = gson.toJson(waypoints)
        )
    }

    private fun ConnectionEntity.toDomain(): Connection {
        return Connection(
            id = id,
            fromNodeId = fromNodeId,
            fromPortId = fromPortId,
            toNodeId = toNodeId,
            toPortId = toPortId,
            dataType = com.automation.workflow.domain.models.DataType.valueOf(dataType),
            waypoints = gson.fromJson(waypoints, List::class.java) as? List<com.automation.workflow.domain.models.Waypoint> ?: emptyList()
        )
    }
}
