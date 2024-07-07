package ca.josue_lubaki.sharedelementapp.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ca.josue_lubaki.sharedelementapp.composables.UnSplashImageView
import ca.josue_lubaki.sharedelementapp.navigation.images

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    onImageClicked: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            items = images,
            key = { image -> image.id }
        ) { image ->
            UnSplashImageView(
                data = image,
                onClick = onImageClicked,
                animatedVisibilityScope = animatedVisibilityScope
            )
        }
    }
}