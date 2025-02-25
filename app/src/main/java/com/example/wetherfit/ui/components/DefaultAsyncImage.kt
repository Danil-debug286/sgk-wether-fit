package com.example.wetherfit.ui.components

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageScope
import com.example.wetherfit.ui.theme.WetherFitTheme

@Composable
internal fun DefaultAsyncImage(
    modifier: Modifier = Modifier,
    model: Any?,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null,
    colorFilter: ColorFilter? = null,
    error: @Composable (SubcomposeAsyncImageScope.(AsyncImagePainter.State.Error) -> Unit)? = null
) = SubcomposeAsyncImage(
    modifier = modifier,
    model = model ,
    contentScale = contentScale,
    contentDescription = contentDescription,
    loading = { Box(modifier = Modifier.shimmer()) },
    error = error ?: {
        Icon(
            imageVector = Icons.Default.Info,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = it.result.throwable.localizedMessage
        )
    },
    colorFilter = colorFilter
)

@Preview(name = "light", showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "dark", showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DefaultAsyncImagePreview() {
    WetherFitTheme {
        DefaultAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            model = "https://avatars.dzeninfra.ru/get-zen_doc/35845/pub_5d8ccd72d4f07a00ae4d2a8b_5d8cce73bd639600afcafdb0/scale_1200"
        )
    }
}