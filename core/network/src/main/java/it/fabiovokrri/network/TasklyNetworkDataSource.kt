package it.fabiovokrri.network

import it.fabiovokrri.network.model.NetworkTask
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface TasklyNetworkDataSource {
    suspend fun getTasks(ids: List<Uuid>? = null): List<NetworkTask>
    suspend fun getTask(id: Uuid): NetworkTask
    suspend fun addTask(task: NetworkTask)
    suspend fun updateTask(task: NetworkTask)
    suspend fun deleteTask(id: Uuid)
}