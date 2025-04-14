package it.fabiovokrri.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Tag model
 */
@OptIn(ExperimentalUuidApi::class)
data class Tag(
    val id: Uuid = Uuid.random(),
    val name: String,
)