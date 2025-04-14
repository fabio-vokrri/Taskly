@file:OptIn(ExperimentalUuidApi::class)

package it.fabiovokrri.data.repository

import it.fabiovokrri.model.Task
import kotlinx.coroutines.flow.Flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Repository for the tasks.
 */
interface TasksRepository {
    /**
     * Gets all the tasks.
     */
    fun getTasks(): Flow<List<Task>>

    /**
     * Gets a task by its [id].
     */
    suspend fun getTaskById(id: Uuid): Task

    /**
     * Upserts the given [task].
     */
    suspend fun upsertTask(task: Task)

    /**
     * Deletes the given [task].
     */
    suspend fun deleteTask(task: Task)
}