package dog.shebang.tweetpreview.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.navigation.NavController
import androidx.navigation.compose.navigate

sealed class Screen(val name: String, val icon: VectorAsset) {
    object Home : Screen("home", Icons.Outlined.Home)
    object Detail : Screen("detail", Icons.Outlined.List)
    object Post : Screen("post", Icons.Outlined.PostAdd)
    object Profile : Screen("profile", Icons.Outlined.Person)
    object Setting : Screen("setting", Icons.Outlined.Settings)
}

fun NavController.navigateToHome() = navigate(Screen.Home.name)
fun NavController.navigateToDetail(id: String) = navigate(Screen.Detail.name)
fun NavController.navigateToPost() = navigate(Screen.Post.name)
fun NavController.navigateToProfile() = navigate(Screen.Profile.name)
fun NavController.navigateToSetting() = navigate(Screen.Setting.name)
