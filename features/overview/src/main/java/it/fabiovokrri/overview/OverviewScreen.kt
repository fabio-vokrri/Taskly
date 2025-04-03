package it.fabiovokrri.overview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import it.fabiovokrri.overview.viewmodel.OverviewEvent
import it.fabiovokrri.overview.viewmodel.OverviewViewModel
import it.fabiovokrri.overview.viewmodel.UiState

@Composable
internal fun OverviewScreen(
    modifier: Modifier = Modifier,
    viewModel: OverviewViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    OverviewScreen(
        modifier = modifier,
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun OverviewScreen(
    modifier: Modifier = Modifier,
    state: UiState,
    onEvent: (OverviewEvent) -> Unit,
) {
    Scaffold(modifier.fillMaxSize()) { innerPadding ->
        val innerModifier = Modifier.padding(innerPadding)
        when (state) {
            is UiState.Failed -> ErrorScreen(
                modifier = innerModifier,
                cause = "Something went wrong:\n${state.message}"
            )

            is UiState.Loading -> LoadingScreen(modifier = innerModifier)

            is UiState.Success -> OverviewScreenContent(modifier = innerModifier, onEvent = onEvent)
        }
    }

}

@Composable
private fun ErrorScreen(
    modifier: Modifier = Modifier,
    cause: String,
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(cause, textAlign = TextAlign.Center)
    }
}

@Composable
private fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun OverviewScreenContent(
    modifier: Modifier = Modifier,
    onEvent: (OverviewEvent) -> Unit,
) {

}