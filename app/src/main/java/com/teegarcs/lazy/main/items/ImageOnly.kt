package com.teegarcs.lazy.main.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.teegarcs.lazy.core.GenericLazyItem
import com.teegarcs.lazy.ui.theme.LazyTheme

/**
 * Demo of Image only Composable for Lazy List
 */
data class ImageOnly(
    private val imageUrl: String,
    private val imageCD: String,
    private val groupTitle: String
) : GenericLazyItem<ItemIntent>() {

    override fun sectionMatcher() = groupTitle.hashCode()

    @Composable
    override fun BuildHeaderItem(processIntent: (ItemIntent) -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .clickable { processIntent(ItemIntent.ItemHeader(groupTitle)) }
        ) {
            Text(text = groupTitle, style = MaterialTheme.typography.headlineMedium)
        }
    }

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
fun ImageOnlyPreview() {
    LazyTheme {
        ImageOnly(
            imageUrl = "https://free-images.com/lg/4275/penguin_funny_blue_water.jpg",
            imageCD = "Penguin Image",
            groupTitle = "Penguins"
        ).BuildItem {}
    }
}
