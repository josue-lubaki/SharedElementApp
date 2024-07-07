package ca.josue_lubaki.sharedelementapp.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ca.josue_lubaki.sharedelementapp.screen.DetailScreen
import ca.josue_lubaki.sharedelementapp.screen.HomeScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SetupNavigation(navController : NavHostController) {
    SharedTransitionLayout {
        NavHost(navController = navController, startDestination = Screen.Home.route) {
            composable(route = Screen.Home.route) {
                HomeScreen(
                    animatedVisibilityScope = this@composable,
                    onImageClicked = { imageId ->
                        navController.navigate(Screen.Detail.passImageId(imageId))
                    }
                )
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument(IMAGE_ID_ARG) { type = NavType.IntType })
            ) {
                val imageId = it.arguments?.getInt(IMAGE_ID_ARG) ?: 1
                DetailScreen(
                    animatedVisibilityScope = this@composable,
                    imageId = imageId,
                    onClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}



