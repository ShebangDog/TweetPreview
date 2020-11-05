package dog.shebang.tweetpreview.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.navigation.NavController
import androidx.navigation.compose.navigate

sealed class Screen(val name: String, val icon: VectorAsset) {
    object Detail : Screen("detail", Icons.Outlined.List)
    object Home : Screen("home", Icons.Outlined.Home)
    object Profile : Screen("profile", Icons.Outlined.Person)
}

fun NavController.navigateToProfile() = navigate(Screen.Profile.name)
fun NavController.navigateToDetail(id: String) = navigate(Screen.Detail.name)