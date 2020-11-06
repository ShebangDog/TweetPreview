package dog.shebang.tweetpreview.ui.settting

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dog.shebang.tweetpreview.ui.navigation.Screen

@ExperimentalLayout
@Composable
fun SettingScreen(navController: NavController, darkMode: Boolean, onSwitch: (Boolean) -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        icon = { Icon(asset = Icons.Outlined.ArrowBack) }
                    )
                },
                title = { Text(text = Screen.Setting.name) }
            )
        }
    ) {

        SettingContent(isDarkMode = darkMode, onSwitch = onSwitch)
    }
}

@ExperimentalLayout
@Composable
fun SettingContent(isDarkMode: Boolean, onSwitch: (Boolean) -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        val mode = remember(isDarkMode) { if (isDarkMode) "DarkMode" else "LightMode" }

        listOf<@Composable () -> Unit>(
            {
                SettingItem(
                    name = mode,
                    shower = {
                        Switch(
                            checked = isDarkMode,
                            onCheckedChange = onSwitch,
                            modifier = Modifier.weight(1f).wrapContentWidth(Alignment.End)
                        )
                    })
            }
        ).forEach {
            it()
            Divider()
        }
    }
}

@ExperimentalLayout
@Composable
fun SettingItem(name: String, shower: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .preferredHeight(IntrinsicSize.Min)
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {

        Text(text = name, Modifier.width(96.dp))
        HorizontalDivider(Modifier.padding(horizontal = 8.dp))
        shower()
    }
}

@Composable
fun HorizontalDivider(
    modifier: Modifier = Modifier
) {
    Spacer(
        modifier = modifier
            .preferredWidth(1.dp)
            .fillMaxHeight()
            .background(color = MaterialTheme.colors.onSurface.copy(0.12f))
    )
}