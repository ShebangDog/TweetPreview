package dog.shebang.tweetpreview.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.ExperimentalLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dog.shebang.tweetpreview.theme.TweetPreviewTheme
import dog.shebang.tweetpreview.ui.detail.DetailScreen
import dog.shebang.tweetpreview.ui.home.HomeScreen
import dog.shebang.tweetpreview.ui.navigation.Screen
import dog.shebang.tweetpreview.ui.post.PostScreen
import dog.shebang.tweetpreview.ui.profile.ProfileScreen
import dog.shebang.tweetpreview.ui.settting.SettingScreen

class MainActivity : AppCompatActivity() {
    @ExperimentalLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TweetPreviewApp()
        }
    }
}

@ExperimentalLayout
@Composable
fun TweetPreviewApp() {
    val isDarkModeState = remember { mutableStateOf<Boolean?>(null) }

    TweetPreviewTheme(darkTheme = isDarkModeState.value ?: isSystemInDarkTheme()) {
        AppContent(isDarkModeState)
    }
}

@ExperimentalLayout
@Composable
fun AppContent(isDarkModeState: MutableState<Boolean?>) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.name) {
        composable(Screen.Home.name) { HomeScreen(navController) }
        composable(Screen.Detail.name) { DetailScreen(navController, name = "") }
        composable(Screen.Post.name) { PostScreen(navController) }
        composable(Screen.Setting.name) {
            SettingScreen(navController, isDarkModeState.value ?: isSystemInDarkTheme()) {
                isDarkModeState.value = it
            }
        }
        composable(Screen.Profile.name) {
            ProfileScreen(navController, isDarkModeState.value ?: isSystemInDarkTheme()) {
                isDarkModeState.value = it
            }
        }
    }
}
