package dog.shebang.tweetpreview.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TweetPreviewApp()
        }
    }
}

@Composable
fun TweetPreviewApp() {
    TweetPreviewTheme {
        AppContent()
    }
}

@Composable
fun AppContent() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.name) {
        composable(Screen.Home.name) { HomeScreen(navController) }
        composable(Screen.Detail.name) { DetailScreen(navController, name = "") }
        composable(Screen.Post.name) { PostScreen(navController) }
        composable(Screen.Profile.name) { ProfileScreen() }
    }
}
