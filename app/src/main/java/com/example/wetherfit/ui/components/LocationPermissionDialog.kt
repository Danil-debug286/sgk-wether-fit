package com.example.wetherfit.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.wetherfit.R
import com.example.wetherfit.ui.theme.WetherFitTheme

@Composable
internal fun LocationPermissionDialog(
    isPermissionDenied: Boolean,
    onDismiss: () -> Unit,
    onAccess: () -> Unit,
) = Dialog(onDismissRequest = onDismiss) {
    LocationPermissionDialogCard(
        modifier = Modifier.fillMaxWidth(),
        isPermissionDenied = isPermissionDenied,
        onDismiss = onDismiss,
        onAccess = onAccess,
    )
}

@Composable
private fun LocationPermissionDialogCard(
    modifier: Modifier,
    isPermissionDenied: Boolean,
    onDismiss: () -> Unit,
    onAccess: () -> Unit,
) = Card(
    modifier = modifier,
    shape = MaterialTheme.shapes.medium,
) {
    Spacer(modifier = Modifier.height(24.dp))
    Text(
        modifier = Modifier.padding(horizontal = 16.dp),
        text = stringResource(R.string.location_permission_dialog_title),
        style = MaterialTheme.typography.titleLarge,
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        modifier = Modifier.padding(horizontal = 16.dp),
        text = if (isPermissionDenied) stringResource(R.string.location_permission_denied_dialog_description)
        else stringResource(R.string.location_permission_dialog_description),
        style = MaterialTheme.typography.bodyMedium,
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Button(
            shape = MaterialTheme.shapes.medium,
            onClick = onDismiss
        ) {
            Text(
                text = stringResource(R.string.location_permission_dialog_button_dismiss),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            shape = MaterialTheme.shapes.medium,
            onClick = onAccess
        ) {
            Text(
                text = if (isPermissionDenied) stringResource(R.string.location_permission_denied_dialog_button_access)
                else stringResource(R.string.location_permission_dialog_button_access),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview(name = "light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun LocationPermissionDialogPreview() {
    WetherFitTheme {
        LocationPermissionDialogCard(
            modifier = Modifier.padding(16.dp),
            isPermissionDenied = false,
            onDismiss = {},
            onAccess = {}
        )
    }
}

@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LocationPermissionDeniedDialogPreview() {
    WetherFitTheme {
        LocationPermissionDialogCard(
            modifier = Modifier.padding(16.dp),
            isPermissionDenied = true,
            onDismiss = {},
            onAccess = {}
        )
    }
}