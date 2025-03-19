package it.fabiovokrri.data.repository

import it.fabiovokrri.database.dao.TaskDao
import it.fabiovokrri.database.model.PopulatedTaskResource
import it.fabiovokrri.model.TaskStatus
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class OfflineFirstTaskRepository @Inject constructor(
    private val taskDao: TaskDao,
) : TasksRepository {
    override fun getTasks(): Flow<List<PopulatedTaskResource>> {
        TODO("Not yet implemented")
    }

    override fun getTaskById(id: Long): Flow<PopulatedTaskResource> {
        TODO("Not yet implemented")
    }

    override fun upsertTask(task: PopulatedTaskResource) {
        TODO("Not yet implemented")
    }

    override fun deleteTask(task: PopulatedTaskResource) {
        TODO("Not yet implemented")
    }

    override fun getTasksByTag(tagId: Long): Flow<List<PopulatedTaskResource>> {
        TODO("Not yet implemented")
    }

    override fun getTasksByStatus(status: TaskStatus): Flow<List<PopulatedTaskResource>> {
        TODO("Not yet implemented")
    }

    override fun getTasksByDueDate(dueDate: Long): Flow<List<PopulatedTaskResource>> {
        TODO("Not yet implemented")
    }

    override fun getTasksByPriority(priority: Int): Flow<List<PopulatedTaskResource>> {
        TODO("Not yet implemented")
    }

    override fun changeTaskStatus(
        task: PopulatedTaskResource,
        newStatus: TaskStatus,
    ) {
        TODO("Not yet implemented")
    }

    override fun changeTaskPriority(task: PopulatedTaskResource, newPriority: Int) {
        TODO("Not yet implemented")
    }

}