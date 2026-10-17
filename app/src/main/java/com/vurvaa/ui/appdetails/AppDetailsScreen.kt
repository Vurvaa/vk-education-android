package com.vurvaa.ui.appdetails

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.vurvaa.R
import com.vurvaa.domain.AppDetails
import kotlinx.coroutines.launch

@Composable
fun AppDetailsScreen(
    app: AppDetails,
    onBackClick: () -> Unit,
    onShowcaseAppClick: (AppDetails) -> Unit,
    modifier: Modifier = Modifier,
) {
    var descriptionCollapsed by rememberSaveable(app.id) { mutableStateOf(true) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val underDevelopment = stringResource(R.string.under_development)
    val shareTitle = stringResource(R.string.share_app)
    val shareUnavailable = stringResource(R.string.share_unavailable)
    val context = LocalContext.current
    val showUnderDevelopment: () -> Unit = {
        coroutineScope.launch { snackbarHostState.showSnackbar(underDevelopment) }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { padding ->
        AppDetailsContent(
            appDetails = app,
            descriptionCollapsed = descriptionCollapsed,
            onBackClick = onBackClick,
            onShareClick = {
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "${app.name}\n${app.description}")
                }
                try {
                    context.startActivity(Intent.createChooser(shareIntent, shareTitle))
                } catch (_: ActivityNotFoundException) {
                    coroutineScope.launch { snackbarHostState.showSnackbar(shareUnavailable) }
                }
            },
            onInstallClick = showUnderDevelopment,
            onReadMoreClick = { descriptionCollapsed = false },
            onDeveloperClick = showUnderDevelopment,
            onShowcaseAppClick = onShowcaseAppClick,
            modifier = Modifier.fillMaxSize().padding(padding),
        )
    }
}
