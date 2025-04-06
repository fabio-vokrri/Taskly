package it.fabiovokrri.data.repository

import it.fabiovokrri.database.dao.TaskDao
import it.fabiovokrri.database.dao.TaskTagCrossRefDao
import it.fabiovokrri.database.model.PopulatedTaskResource
import it.fabiovokrri.database.model.toEntity
import it.fabiovokrri.database.model.toExternalModel
import it.fabiovokrri.model.Task
import it.fabiovokrri.model.TaskTagCrossRef
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

    override suspend fun getTaskById(id: Long): Task =
        taskDao.getTaskById(id).toExternalModel()

    override suspend fun upsertTask(task: Task) {
        // inserts the task in the task table
        val taskId = taskDao.upsertTask(task.toEntity())

        // no need to continue if the task has no tags
        if (task.tags.isEmpty()) return

        // builds a list of cross references...
        val crossReferences = task.tags.map { tag ->
            TaskTagCrossRef(
                taskId = taskId,
                tagId = tag.id
            )
        }

        // ...and inserts them in the cross reference table
        taskTagCrossRefDao.insertAll(crossReferences.map(TaskTagCrossRef::toEntity))
    }

    override suspend fun deleteTask(task: Task) {
        // delete the task from the task table
        taskDao.deleteTask(task.id)

        // delete the cross references from the cross reference table
        taskTagCrossRefDao.deleteByTaskId(task.id)
    }
}