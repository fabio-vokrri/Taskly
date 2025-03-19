package it.fabiovokrri.data.repository

import it.fabiovokrri.database.dao.TaskDao
import it.fabiovokrri.database.dao.TaskTagCrossRefDao
import it.fabiovokrri.database.model.PopulatedTaskResource
import it.fabiovokrri.database.model.toEntity
import it.fabiovokrri.database.model.toExternalModel
import it.fabiovokrri.model.Task
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflineFirstTasksRepository @Inject constructor(
    private val taskDao: TaskDao,
    private val taskTagCrossRefDao: TaskTagCrossRefDao,
) : TasksRepository {
    override fun getTasks(): Flow<List<Task>> = taskDao.getTasks().map {
        it.map(PopulatedTaskResource::toExternalModel)
    }

    override fun getTaskById(id: Long): Flow<Task> =
        taskDao.getTaskById(id).map(PopulatedTaskResource::toExternalModel)

    override suspend fun upsertTask(task: Task) {
        val taskId = taskDao.upsertTask(task.toEntity())
    }

    override suspend fun deleteTask(task: Task) {
    }
}