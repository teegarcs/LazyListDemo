package com.teegarcs.lazy.main.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.teegarcs.lazy.core.GenericLazyItem
import com.teegarcs.lazy.ui.theme.LazyTheme

/**
 * Demo of Image on right with title and description on left Composable for Lazy List
 */
data class TextImageRight(
    private val imageUrl: String,
    private val title: String,
    private val description: String,
    private val groupTitle: String
) : GenericLazyItem<ItemIntent>() {

    override fun sectionMatcher() = groupTitle.hashCode()

    @Composable
    override fun BuildHeaderItem(processIntent: (ItemIntent) -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Green)
                .clickable { processIntent(ItemIntent.ItemHeader(groupTitle)) }
        ) {
            Text(text = groupTitle, style = MaterialTheme.typography.headlineMedium)
        }
    }

    @Composable
    override fun BuildItem(processIntent: (ItemIntent) -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { processIntent(ItemIntent.ItemClicked(title = title)) }
        ) {
            Column(
                modifier = Modifier
                    .weight(.6f)
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = title, style = MaterialTheme.typography.titleLarge)
                Text(text = description, style = MaterialTheme.typography.bodyMedium)
            }

            AsyncImage(
                modifier = Modifier.weight(.4f),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "$title $description"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextImageRightPreview() {
    LazyTheme {
        TextImageRight(
            imageUrl = "https://free-images.com/lg/4275/penguin_funny_blue_water.jpg",
            title = "Penguin Image",
            description = "penguin walking around",
            groupTitle = "Penguins"
        ).BuildItem {}
    }
}
