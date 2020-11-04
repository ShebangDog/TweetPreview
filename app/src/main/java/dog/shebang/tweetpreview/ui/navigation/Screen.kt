package dog.shebang.tweetpreview.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.graphics.vector.VectorAsset

sealed class Screen(val name: String, val icon: VectorAsset) {
    object Detail : Screen("detail", Icons.Outlined.List)
    object Home : Screen("home", Icons.Outlined.Home)
}