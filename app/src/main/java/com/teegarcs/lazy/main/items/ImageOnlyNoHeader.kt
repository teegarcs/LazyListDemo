package com.teegarcs.lazy.main.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.teegarcs.lazy.core.GenericLazyItem
import com.teegarcs.lazy.ui.theme.LazyTheme

/**
 * Demo of Image only Composable for Lazy List without a sticky heady
 */
data class ImageOnlyNoHeader(
    private val imageUrl: String,
    private val imageCD: String,
) : GenericLazyItem<ItemIntent>() {

    @Composable
    override fun BuildItem(processIntent: (ItemIntent) -> Unit) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { processIntent(ItemIntent.ItemClicked(title = imageCD)) }
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = imageCD
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImageOnlyNoHeaderPreview() {
    LazyTheme {
        ImageOnlyNoHeader(
            imageUrl = "https://free-images.com/lg/4275/penguin_funny_blue_water.jpg",
            imageCD = "Penguin Image",
        ).BuildItem {}
    }
}
