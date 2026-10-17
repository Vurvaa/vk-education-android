package com.vurvaa.ui.apps

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vurvaa.R
import com.vurvaa.domain.AppDetails

@Composable
fun AppsListScreen(
    apps: List<AppDetails>,
    onAppClick: (AppDetails) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = modifier) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding).testTag("apps_list"),
            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 16.dp),
        ) {
            item(key = "title") {
                Column(Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
                    Text(
                        text = stringResource(R.string.apps_title),
                        style = MaterialTheme.typography.headlineLarge,
                    )
                    Text(
                        text = stringResource(R.string.apps_subtitle),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
            items(apps, key = { it.id }) { app ->
                AppListItem(
                    app = app,
                    onClick = { onAppClick(app) },
                    modifier = Modifier.testTag("catalog_app_${app.id}"),
                )
            }
        }
    }
}
