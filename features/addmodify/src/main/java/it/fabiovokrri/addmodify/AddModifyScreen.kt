package it.fabiovokrri.addmodify

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import it.fabiovokrri.addmodify.viewmodel.AddModifyEvent
import it.fabiovokrri.addmodify.viewmodel.AddModifyViewModel
import it.fabiovokrri.addmodify.viewmodel.TaskState
import it.fabiovokrri.model.Tag
import it.fabiovokrri.model.TaskStatus
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AddModifyScreen(
    modifier: Modifier = Modifier,
    viewModel: AddModifyViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val currentTaskState by viewModel.taskState.collectAsStateWithLifecycle()
    val tags by viewModel.tags.collectAsStateWithLifecycle()

    AddModifyScreen(
        modifier = modifier,
        taskState = currentTaskState,
        tags = tags,
        onEvent = viewModel::onEvent,
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
private fun AddModifyScreen(
    modifier: Modifier = Modifier,
    taskState: TaskState,
    tags: List<Tag>,
    onEvent: (AddModifyEvent) -> Unit,
    onBackClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(if (taskState.title.isEmpty()) "New Task" else taskState.title)
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Navigate Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = taskState.title,
                onValueChange = { newTitle ->
                    onEvent(AddModifyEvent.ChangeTitle(newTitle))
                },
                label = { Text("Title") }
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = taskState.description,
                onValueChange = { newDescription ->
                    onEvent(AddModifyEvent.ChangeDescription(newDescription))
                },
                label = { Text("Description") }
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = taskState.dueDate.formatToDate(),
                onValueChange = { newDueDate ->
                    onEvent(AddModifyEvent.ChangeDueDate(newDueDate.toLong()))
                },
                label = { Text("Due Date") },
                enabled = false,
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                maxItemsInEachRow = 3,
            ) {
                tags.forEach { tag ->
                    val selected = tag in taskState.tags

                    InputChip(
                        selected = selected,
                        onClick = {
                            if (selected) onEvent(AddModifyEvent.RemoveTag(tag))
                            else onEvent(AddModifyEvent.AddTag(tag))
                        },
                        label = { Text(tag.name) },
                    )
                }

                InputChip(
                    selected = false,
                    onClick = { /*TODO*/ },
                    label = { Icon(Icons.Default.Add, "Add a new Tag") },
                )
            }

            SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                TaskStatus.entries.forEachIndexed { index, taskStatus ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = TaskStatus.entries.size
                        ),
                        selected = taskState.status == taskStatus,
                        onClick = { onEvent(AddModifyEvent.ChangeStatus(taskStatus)) }
                    ) { Text(taskStatus.name) }
                }
            }

            Text("Priority")
            Slider(
                modifier = Modifier.fillMaxWidth(),
                value = taskState.priority.toFloat(),
                onValueChange = { onEvent(AddModifyEvent.ChangePriority(it.toInt())) },
                valueRange = 0f..10f,
                steps = 10,
            )
        }
    }
}

fun Long.formatToDate(): String {
    val date = Date(this)
    val formatter = SimpleDateFormat("dd MM, yyyy 'at' HH:mm", Locale.getDefault())
    return formatter.format(date)
}