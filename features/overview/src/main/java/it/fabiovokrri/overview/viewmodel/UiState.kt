package it.fabiovokrri.overview.viewmodel

import it.fabiovokrri.model.Tag
import it.fabiovokrri.model.Task

sealed interface UiState {
    data object Loading : UiState
    data class Failed(val message: String) : UiState
    data class Success(
        val tasks: List<Task>,
        val tags: List<Tag>,
    ) : UiState
}