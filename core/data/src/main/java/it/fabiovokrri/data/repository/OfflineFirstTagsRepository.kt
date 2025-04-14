@file:OptIn(ExperimentalUuidApi::class)

package it.fabiovokrri.data.repository

import it.fabiovokrri.database.dao.TagDao
import it.fabiovokrri.database.dao.TaskTagCrossRefDao
import it.fabiovokrri.database.model.TagEntity
import it.fabiovokrri.database.model.toEntity
import it.fabiovokrri.database.model.toExternalModel
import it.fabiovokrri.model.Tag
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class OfflineFirstTagsRepository @Inject constructor(
    private val tagDao: TagDao,
    private val crossRefDao: TaskTagCrossRefDao,
) : TagsRepository {
    override fun getTags(): Flow<List<Tag>> {
        return tagDao.getTags().map { it.map(TagEntity::toExternalModel) }
    }

    override fun getTagById(id: Uuid): Flow<Tag> {
        return tagDao.getTagById(id.toHexString()).map(TagEntity::toExternalModel)
    }

    override suspend fun upsertTag(tag: Tag) = tagDao.upsertTag(tag.toEntity())

    override suspend fun deleteTag(tag: Tag) {
        tagDao.deleteByTagId(tag.id.toHexString())
        crossRefDao.deleteByTagId(tag.id.toHexString())
    }

    override fun getTagsByTaskId(taskId: Uuid): Flow<List<Tag>> {
        return tagDao.getTagsByTaskId(taskId.toHexString()).map {
            it.map(TagEntity::toExternalModel)
        }
    }
}