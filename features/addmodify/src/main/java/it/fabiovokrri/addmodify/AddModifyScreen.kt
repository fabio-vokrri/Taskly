package it.fabiovokrri.addmodify

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import it.fabiovokrri.addmodify.viewmodel.AddModifyEvent
import it.fabiovokrri.addmodify.viewmodel.AddModifyViewModel
import it.fabiovokrri.addmodify.viewmodel.TaskState

@Composable
fun AddModifyScreen(
    modifier: Modifier = Modifier,
    viewModel: AddModifyViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val currentTaskState by viewModel.taskState.collectAsStateWithLifecycle()

    AddModifyScreen(
        modifier = modifier,
        taskState = currentTaskState,
        onEvent = viewModel::onEvent,
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddModifyScreen(
    modifier: Modifier = Modifier,
    taskState: TaskState,
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
        
    }
}