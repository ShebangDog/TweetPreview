package dog.shebang.tweetpreview.ui.post

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Text
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dog.shebang.tweetpreview.ui.navigation.navigateToHome

@Composable
fun PostScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        icon = { Icon(asset = Icons.Outlined.Cancel) }
                    )
                },
                actions = {
                    Button(
                        border = BorderStroke(1.dp, Color.Black),
                        shape = RoundedCornerShape(50),
                        onClick = { navController.navigateToHome() },
                    ) {
                        Text(text = "ツイートする")
                    }
                },
                title = { Text(text = "Post") }
            )
        }
    ) {

    }
}