package it.fabiovokrri.overview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import it.fabiovokrri.model.Tag
import it.fabiovokrri.model.Task
import it.fabiovokrri.model.TaskStatus.COMPLETED
import it.fabiovokrri.model.TaskStatus.IN_PROGRESS
import it.fabiovokrri.model.TaskStatus.NOT_STARTED
import it.fabiovokrri.overview.viewmodel.OverviewEvent
import it.fabiovokrri.overview.viewmodel.OverviewViewModel
import it.fabiovokrri.overview.viewmodel.UiState

@Composable
internal fun OverviewScreen(
    modifier: Modifier = Modifier,
    viewModel: OverviewViewModel = hiltViewModel(),
    onTaskClick: (Long) -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val selectedTags by viewModel.selectedTags.collectAsStateWithLifecycle()

    OverviewScreen(
        modifier = modifier,
        state = state,
        onEvent = viewModel::onEvent,
        selectedTags = selectedTags,
        onTaskClick = onTaskClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OverviewScreen(
    modifier: Modifier = Modifier,
    state: UiState,
    selectedTags: Set<Long>,
    onEvent: (OverviewEvent) -> Unit,
    onTaskClick: (Long) -> Unit,
) {
    Scaffold(
        modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Taskly") },
            )
        }
    ) { innerPadding ->
        val innerModifier = Modifier.padding(innerPadding)
        when (state) {
            is UiState.Failed -> ErrorScreen(
                modifier = innerModifier,
                cause = "Something went wrong:\n${state.message}"
            )

            is UiState.Loading -> LoadingScreen(modifier = innerModifier)

            is UiState.Success -> OverviewScreenContent(
                modifier = innerModifier,
                tasks = state.tasks,
                tags = state.tags,
                selectedTags = selectedTags,
                onEvent = onEvent,
                onTaskClick = onTaskClick,
            )
        }
    }

}

/**
 * Shows an error screen with the given [cause].
 */
@Composable
private fun ErrorScreen(
    modifier: Modifier = Modifier,
    cause: String,
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(cause, textAlign = TextAlign.Center)
    }
}

/**
 * Shows a loading screen.
 */
@Composable
private fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

/**
 * Shows the overview screen with the given [tasks] and [tags].
 */
@Composable
private fun OverviewScreenContent(
    modifier: Modifier = Modifier,
    tasks: List<Task>,
    tags: List<Tag>,
    selectedTags: Set<Long>,
    onEvent: (OverviewEvent) -> Unit,
    onTaskClick: (Long) -> Unit,
) {
    Column(modifier = modifier) {
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(tags, key = Tag::id) {
                val selected = it.id in selectedTags

                FilterChip(
                    selected = selected,
                    leadingIcon = {
                        if (selected) Icon(Icons.Default.Done, "selected ${it.name}")
                    },
                    onClick = {
                        onEvent(OverviewEvent.ToggleTagSelection(it.id))
                    },
                    label = { Text(it.name) }
                )
            }
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(tasks, key = Task::id) {

                val checkState: ToggleableState = when (it.status) {
                    NOT_STARTED -> ToggleableState.Off
                    IN_PROGRESS -> ToggleableState.Indeterminate
                    COMPLETED -> ToggleableState.On
                }

                ListItem(
                    modifier = Modifier.clickable { onTaskClick(it.id) },
                    headlineContent = { Text(it.title) },
                    supportingContent = { Text("Due to ${it.dueDate}") },
                    leadingContent = {
                        TriStateCheckbox(
                            state = checkState,
                            onClick = { onEvent(OverviewEvent.StartTask(it)) }
                        )
                    }
                )
            }
        }
    }
}