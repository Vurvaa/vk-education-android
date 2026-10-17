package com.vurvaa.ui.appdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.vurvaa.R

@Composable
fun AppDescription(
    description: String,
    collapsed: Boolean,
    onReadMoreClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Text(text = stringResource(R.string.app_details_description))
        Spacer(Modifier.height(8.dp))
        Text(
            text = description,
            maxLines = if (collapsed) 3 else Int.MAX_VALUE,
            overflow = TextOverflow.Ellipsis,
        )
        if (collapsed) {
            TextButton(
                onClick = onReadMoreClick,
                contentPadding = PaddingValues(horizontal = 0.dp)
            ) {
                Text(
                    text = stringResource(R.string.app_details_read_more),
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}


