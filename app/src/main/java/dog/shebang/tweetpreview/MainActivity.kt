package dog.shebang.tweetpreview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import dog.shebang.tweetpreview.ui.TweetPreviewTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TweetPreviewTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val icon = vectorResource(id = R.drawable.ic_android_black_24dp)

    Column(modifier = Modifier.fillMaxHeight().fillMaxWidth().padding(8.dp)) {
        TopAppBar()

        Profile(vectorIcon = icon, name = "しばDog", id = "ShebangDog")

        Content("Multiple Line Content\n End")

        Date()
        Divider()
        Reaction()
        Divider()
        Action()
    }
}

@Composable
fun TopAppBar() {

}

@Composable
fun Profile(vectorIcon: VectorAsset, name: String, id: String) {
    @Composable
    fun Icon(vectorIcon: VectorAsset) {
        Image(asset = vectorIcon)
    }

    @Composable
    fun Name(name: String) {
        Text(text = name)
    }

    @Composable
    fun Id(id: String) {
        Text(text = id)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {

        Icon(vectorIcon = vectorIcon)

        Column(modifier = Modifier.padding(start = 8.dp)) {
            Name(name = name)
            Id(id = id)
        }
    }
}

@Composable
fun Content(content: String) {
    Text(
        text = content,
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(vertical = 8.dp)
    )
}

@Composable
fun Date() {
    Text(text = "2020/09/29 13:45 Twitter Web App", modifier = Modifier.padding(vertical = 8.dp))
}

@Composable
fun Reaction() {
    Text(text = "1 いいね", modifier = Modifier.padding(vertical = 8.dp))
}

@Composable
fun Action() {
    val iconSize = Modifier.size(24.dp)

    val vectorImageAssets = listOf(
        R.drawable.ic_baseline_chat_bubble_outline,
        R.drawable.ic_outline_repeat,
        R.drawable.ic_baseline_favorite,
        R.drawable.ic_baseline_share
    ).map { vectorResource(id = it) }

    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        vectorImageAssets.forEach {
            Image(
                asset = it,
                modifier = iconSize.weight(1F)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TweetPreviewTheme {
        Surface(color = MaterialTheme.colors.background) {
            MyApp()
        }
    }
}
