package it.fabiovokrri.data.repository

import it.fabiovokrri.database.dao.TagDao
import it.fabiovokrri.database.model.TagEntity
import it.fabiovokrri.database.model.toEntity
import it.fabiovokrri.database.model.toExternalModel
import it.fabiovokrri.model.Tag
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineFirstTagsRepository @Inject constructor(
    private val tagDao: TagDao,
) : TagsRepository {
    override fun getTags(): Flow<List<Tag>> {
        return tagDao.getTags().map { it.map(TagEntity::toExternalModel) }
    }

    override fun getTagById(id: Long): Flow<Tag> {
        return tagDao.getTagById(id).map(TagEntity::toExternalModel)
    }

    override suspend fun upsertTag(tag: Tag) = tagDao.upsertTag(tag.toEntity())

    override suspend fun deleteTag(tag: Tag) {
        // TODO: decide what to do when a tag is deleted
        // TODO: should we delete all the tasks that have this tag?
        // TODO: or should we just remove the cross reference?
        // TODO: what if a task has only that tag?

        TODO("I DON'T REALLY KNOW WHAT TO DO")
        tagDao.deleteTag(tag.id)
    }

    override fun getTagsByTaskId(taskId: Long): Flow<List<Tag>> {
        return tagDao.getTagsByTaskId(taskId).map {
            it.map(TagEntity::toExternalModel)
        }
    }
}