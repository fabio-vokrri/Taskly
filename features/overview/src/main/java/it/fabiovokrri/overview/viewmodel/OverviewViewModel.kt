package it.fabiovokrri.overview.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.fabiovokrri.data.repository.TasksRepository
import it.fabiovokrri.model.Task
import it.fabiovokrri.model.TaskStatus
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val tasksRepository: TasksRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    
    val uiState: StateFlow<UiState> = tasksRepository
        .getTasks()
        .catch { UiState.Failed(it.message ?: "Unknown error") }
        .map(UiState::Success)
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
     * Handles the given [event].
     */
    fun onEvent(event: OverviewEvent) {
        when (event) {
            is OverviewEvent.StartTask -> startTask(event.task)
            is OverviewEvent.CompleteTask -> completeTask(event.task)
            is OverviewEvent.DeleteTask -> deleteTask(event.task)
        }
    }

    companion object {
        private const val TAG = "OverviewViewModel"
        private const val TIMEOUT_MILLIS = 5_000L
    }
}