package com.vurvaa.ui.appdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import com.vurvaa.R
import com.vurvaa.data.AppCatalog
import com.vurvaa.domain.AppDetails
import com.vurvaa.ui.apps.AppListItem

@Composable
fun Showcase(
    onAppClick: (AppDetails) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(pageCount = { AppCatalog.SHOWCASE_PAGE_COUNT })
    val pageDescription = stringResource(
        R.string.showcase_page,
        pagerState.currentPage + 1,
        pagerState.pageCount,
    )
    Column(modifier.testTag("showcase")) {
        Text(
            text = stringResource(R.string.showcase_title),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(horizontal = 16.dp).semantics { heading() },
        )
        Spacer(Modifier.height(8.dp))
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("showcase_pager")
                .semantics { stateDescription = pageDescription },
        ) { page ->
            Column(Modifier.fillMaxWidth().padding(horizontal = 4.dp)) {
                AppCatalog.showcasePages[page].forEach { app ->
                    AppListItem(
                        app = app,
                        onClick = { onAppClick(app) },
                        onClickLabel = stringResource(R.string.showcase_open_list),
                        modifier = Modifier.testTag("showcase_app_${app.id}"),
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .semantics { contentDescription = pageDescription },
            horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
        ) {
            repeat(pagerState.pageCount) { page ->
                Box(
                    Modifier.size(6.dp).background(
                        color = if (page == pagerState.currentPage) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.outlineVariant
                        },
                        shape = CircleShape,
                    ),
                )
            }
        }
    }
}
