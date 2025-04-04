package it.fabiovokrri.overview.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.fabiovokrri.data.repository.TagsRepository
import it.fabiovokrri.data.repository.TasksRepository
import it.fabiovokrri.model.Task
import it.fabiovokrri.model.TaskStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val tasksRepository: TasksRepository,
    private val tagsRepository: TagsRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _selectedTags = MutableStateFlow<Set<Long>>(emptySet())
    val selectedTags: StateFlow<Set<Long>> = _selectedTags.asStateFlow()

    val uiState: StateFlow<UiState> = combine(
        tasksRepository.getTasks(),
        tagsRepository.getTags(),
    ) { tasks, tags -> UiState.Success(tasks, tags) }
        .catch {
            Log.e(TAG, "Failed to get tasks", it)
            UiState.Failed(it.message ?: "Unknown error")
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = UiState.Loading
        )

    /**
     * Starts the given [task].
     */
    private fun startTask(task: Task) = viewModelScope.launch {
        tasksRepository.upsertTask(
            task.copy(status = TaskStatus.IN_PROGRESS)
        )
    }

    /**
     * Completes the given [task].
     */
    private fun completeTask(task: Task) = viewModelScope.launch {
        tasksRepository.upsertTask(
            task.copy(status = TaskStatus.COMPLETED)
        )
    }

    /**
     * Deletes the given [task].
     */
    private fun deleteTask(task: Task) = viewModelScope.launch {
        tasksRepository.deleteTask(task)
    }

    /**
     * Toggles the selection of the given [tagId].
     */
    private fun toggleTagSelection(tagId: Long) {
        _selectedTags.value = _selectedTags.value.toMutableSet().apply {
            if (contains(tagId)) remove(tagId) else add(tagId)
        }
    }

    /**
     * Handles the given [event].
     */
    fun onEvent(event: OverviewEvent) {
        when (event) {
            is OverviewEvent.StartTask -> startTask(event.task)
            is OverviewEvent.CompleteTask -> completeTask(event.task)
            is OverviewEvent.DeleteTask -> deleteTask(event.task)
            is OverviewEvent.ToggleTagSelection -> toggleTagSelection(event.tagId)
        }
    }

    companion object {
        private const val TAG = "OverviewViewModel"
        private const val TIMEOUT_MILLIS = 5_000L
    }
}