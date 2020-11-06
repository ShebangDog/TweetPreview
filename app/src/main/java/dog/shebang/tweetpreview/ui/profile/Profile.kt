package dog.shebang.tweetpreview.ui.profile

import androidx.compose.foundation.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun ProfileScreen(
    navController: NavHostController,
    isDarkMode: Boolean,
    onChange: (Boolean) -> Unit
) {
    Text(text = "profile")
}