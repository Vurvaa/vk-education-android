package com.vurvaa.ui.appdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.vurvaa.domain.AppDetails

/** Adapted from the practical repository's AppDetailsContent, with Showcase at the bottom. */
@Composable
fun AppDetailsContent(
    appDetails: AppDetails,
    descriptionCollapsed: Boolean,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onInstallClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    onDeveloperClick: () -> Unit,
    onShowcaseAppClick: (AppDetails) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier.testTag("app_details").verticalScroll(rememberScrollState())) {
        Toolbar(onBackClick = onBackClick, onShareClick = onShareClick)
        Spacer(Modifier.height(8.dp))
        AppDetailsHeader(
            appDetails = appDetails,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        Spacer(Modifier.height(16.dp))
        InstallButton(
            onClick = onInstallClick,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        )
        appDetails.screenshotUrlList?.takeIf { it.isNotEmpty() }?.let { screenshots ->
            Spacer(Modifier.height(12.dp))
            ScreenshotsList(
                screenshotUrlList = screenshots,
                contentPadding = PaddingValues(horizontal = 16.dp),
            )
        }
        Spacer(Modifier.height(12.dp))
        AppDescription(
            description = appDetails.description,
            collapsed = descriptionCollapsed,
            onReadMoreClick = onReadMoreClick,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        )
        Spacer(Modifier.height(12.dp))
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.outlineVariant,
        )
        Spacer(Modifier.height(12.dp))
        Developer(
            name = appDetails.developer,
            onClick = onDeveloperClick,
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
        )
        Spacer(Modifier.height(16.dp))
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        Spacer(Modifier.height(16.dp))
        Showcase(onAppClick = onShowcaseAppClick)
        Spacer(Modifier.height(16.dp))
    }
}
