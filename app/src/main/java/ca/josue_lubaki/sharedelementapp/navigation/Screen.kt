package ca.josue_lubaki.sharedelementapp.navigation

const val IMAGE_ID_ARG = "imageId"

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Detail : Screen("detail/{${IMAGE_ID_ARG}}") {
        fun passImageId(id : Int) = "detail/$id"
    }
}

data class UnSplashImage(
    val id: Int,
    val photo: String,
    val author : String,
    val location : String
)

val images = listOf(
    UnSplashImage(1, "https://images.unsplash.com/photo-1718964313194-5c028558f69d?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w2MzA2Njd8MHwxfHJhbmRvbXx8fHx8fHx8fDE3MjAzNTcyMTB8&ixlib=rb-4.0.3&q=80&w=1080", "Branislav Rodman", "Home"),
    UnSplashImage(2, "https://images.unsplash.com/photo-1719304576167-fbc0048ab0f8?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w2MzA2Njd8MHwxfHJhbmRvbXx8fHx8fHx8fDE3MjAzNTczMzh8&ixlib=rb-4.0.3&q=80&w=1080", "Pascal Debrunner", "Switzerland"),
    UnSplashImage(3, "https://images.unsplash.com/photo-1719552483173-a99523e6f482?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w2MzA2Njd8MHwxfHJhbmRvbXx8fHx8fHx8fDE3MjAzNTczNzd8&ixlib=rb-4.0.3&q=80&w=1080", "JC Gellidon", "Switzerland"),
    UnSplashImage(4, "https://images.unsplash.com/photo-1717154462649-e0c67fa0f556?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w2MzA2Njd8MHwxfHJhbmRvbXx8fHx8fHx8fDE3MjAzNTc0NDJ8&ixlib=rb-4.0.3&q=80&w=1080", "Harper Sunday", "Melbourne"),
)
