package dog.shebang.tweetpreview.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.setContent
import androidx.navigation.compose.*
import dog.shebang.tweetpreview.theme.TweetPreviewTheme
import dog.shebang.tweetpreview.ui.detail.DetailScreen
import dog.shebang.tweetpreview.ui.home.HomeScreen
import dog.shebang.tweetpreview.ui.navigation.Screen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TweetPreviewTheme {
                Navigation()
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    Scaffold(bottomBar = {
        BottomNavigation {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)

            listOf(Screen.Home, Screen.Detail).forEach { screen ->
                BottomNavigationItem(
                    icon = { Icon(asset = screen.icon) },
                    selected = currentRoute == screen.name,
                    onClick = {
                        navController.popBackStack()

                        if (screen.name != currentRoute) navController.navigate(screen.name)
                    }
                )
            }
        }
    }) { innerPadding ->

        NavHost(navController = navController, startDestination = Screen.Home.name) {
            composable(Screen.Home.name) { HomeScreen() }
            composable(Screen.Detail.name) { DetailScreen(innerPadding) }
        }
    }
}
