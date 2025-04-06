package it.fabiovokrri.addmodify.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import it.fabiovokrri.addmodify.navigation.AddModifyRoute
import it.fabiovokrri.data.repository.TagsRepository
import it.fabiovokrri.data.repository.TasksRepository
import it.fabiovokrri.model.Tag
import it.fabiovokrri.model.Task
import it.fabiovokrri.model.TaskStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddModifyViewModel @Inject constructor(
    private val tasksRepository: TasksRepository,
    private val tagsRepository: TagsRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    // retrieves the id of the task to be modified from the navigation arguments
    private val taskId: Long? = savedStateHandle.toRoute<AddModifyRoute>().id

    private val _taskState = MutableStateFlow(TaskState())
    val taskState: StateFlow<TaskState> = _taskState.asStateFlow()

    val tags = tagsRepository.getTags().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
        initialValue = emptyList()
    )

    init {
        taskId?.let { id ->
            viewModelScope.launch {
                // gets the task from the database and updates the state
                val task = tasksRepository.getTaskById(id)

                _taskState.update {
                    it.copy(
                        title = task.title,
                        description = task.description,
                        tags = task.tags,
                        status = task.status,
                        priority = task.priority,
                        dueDate = task.dueDate,
                    )
                }
            }
        }
    }

    /**
     * Saves the current task.
     */
    private fun save() {
        check(_taskState.value.title.isEmpty()) {
            "$TAG: Title cannot be empty"
        }

        viewModelScope.launch {
            with(_taskState.value) {
                tasksRepository.upsertTask(
                    Task(
                        id = taskId ?: 0,
                        title = title,
                        description = description,
                        tags = tags,
                        status = status,
                        priority = priority,
                        dueDate = dueDate,
                    )
                )
            }
        }
    }

    /**
     * Changes the title of the task with the given [newTitle].
     */
    private fun changeTitle(newTitle: String) {
        check(newTitle.isNotEmpty()) {
            "$TAG: Title cannot be empty"
        }

        _taskState.update {
            it.copy(title = newTitle)
        }
    }

    /**
     * Changes the description of the task with the given [newDescription].
     */
    private fun changeDescription(newDescription: String) = _taskState.update {
        it.copy(description = newDescription)
    }

    /**
     * Changes the priority of the task with the given [newPriority].
     */
    private fun changePriority(newPriority: Int) {
        check(newPriority in 0..10) {
            "$TAG: Priority must be in range 0..10"
        }

        _taskState.update {
            it.copy(priority = newPriority)
        }
    }

    /**
     * Changes the status of the task with the given [newStatus].
     */
    private fun changeStatus(newStatus: TaskStatus) = _taskState.update {
        it.copy(status = newStatus)
    }

    /**
     * Changes the due date of the task with the given [newDueDate].
     */
    private fun changeDueDate(newDueDate: Long) = _taskState.update {
        it.copy(dueDate = newDueDate)
    }

    /**
     * Adds the given [newTag] to the task.
     */
    private fun addTag(newTag: Tag) = _taskState.update {
        it.copy(tags = it.tags + newTag)
    }

    /**
     * Removes the given [tag] from the task.
     */
    private fun removeTag(tag: Tag) = _taskState.update {
        it.copy(tags = it.tags - tag)
    }

    /**
     * Creates a new tag with the given [name].
     */
    private fun createTag(name: String) = viewModelScope.launch {
        check(name.isNotEmpty()) {
            "$TAG: Tag name cannot be empty"
        }

        val tag = Tag(name = name)
        tagsRepository.upsertTag(tag)
    }

    /**
     * Deletes the given [tag].
     */
    private fun deleteTag(tag: Tag) = viewModelScope.launch {
        tagsRepository.deleteTag(tag)
    }

    /**
     * Handles the given [event].
     */
    fun onEvent(event: AddModifyEvent) {
        when (event) {
            is AddModifyEvent.ChangeTitle -> changeTitle(event.title)
            is AddModifyEvent.ChangeDescription -> changeDescription(event.description)
            is AddModifyEvent.ChangePriority -> changePriority(event.priority)
            is AddModifyEvent.ChangeStatus -> changeStatus(event.status)
            is AddModifyEvent.ChangeDueDate -> changeDueDate(event.dueDate)

            is AddModifyEvent.AddTag -> addTag(event.tag)
            is AddModifyEvent.RemoveTag -> removeTag(event.tag)
            is AddModifyEvent.CreateTag -> createTag(event.name)
            is AddModifyEvent.DeleteTag -> deleteTag(event.tag)

            is AddModifyEvent.Save -> save()
        }
    }

    companion object {
        private const val TAG = "AddModifyViewModel"
        private const val TIMEOUT_MILLIS = 5_000L
    }
}