package it.fabiovokrri.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Task Tag Cross Reference model
 */
@OptIn(ExperimentalUuidApi::class)
data class TaskTagCrossRef(
    val taskId: Uuid,
    val tagId: Uuid,
)