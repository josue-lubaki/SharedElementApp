package ca.josue_lubaki.sharedelementapp.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import ca.josue_lubaki.sharedelementapp.navigation.images
import coil.compose.AsyncImage


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailScreen(
    animatedVisibilityScope : AnimatedVisibilityScope,
    imageId: Int,
    onClick: () -> Boolean
) {
    Column(modifier =
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = images[imageId - 1].photo,
            contentDescription = "Image by ${images[imageId - 1].author}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .sharedElement(
                    state = rememberSharedContentState(key = "image-$imageId"),
                    animatedVisibilityScope = animatedVisibilityScope,
                )
                .fillMaxWidth()
                .height(350.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { onClick() }
                ),
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 12.dp)
                .padding(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){

            Text(
                text = images[imageId - 1].author,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .sharedBounds(
                        sharedContentState = rememberSharedContentState(key = "author-$imageId"),
                        enter = fadeIn(),
                        exit = fadeOut(),
                        animatedVisibilityScope = animatedVisibilityScope,
                    )
            )

            Text(
                text = LoremIpsum(300).values.single(),
                fontWeight = FontWeight.Normal,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                softWrap = false,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .fillMaxWidth()
                    .sharedBounds(
                        sharedContentState = rememberSharedContentState(key = "description-$imageId"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        enter = fadeIn(),
                        exit = fadeOut(),
                        resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                    )
                    .alpha(0.6f)
            )
        }
    }
}

