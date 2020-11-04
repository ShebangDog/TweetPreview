package dog.shebang.tweetpreview.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    Scaffold(
        topBar = {
            TopAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(onClick = {}, icon = { Icon(asset = Icons.Default.Person) })
                }
            }
        },
        bottomBar = {
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
