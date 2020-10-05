package dog.shebang.tweetpreview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import dog.shebang.tweetpreview.theme.TweetPreviewTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TweetPreviewTheme {
                TweetPreview()
            }
        }
    }
}

@Composable
fun TweetPreview() {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Tweet Preview") },
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(asset = Icons.Default.ArrowBack)
                }
            }
        )
    }) {

        BodyContents(modifier = Modifier.padding(it))
    }
}

@Composable
fun BodyContents(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxHeight().fillMaxWidth().padding(8.dp)) {
        Profile(vectorIcon = Icons.Default.Person, name = "しばDog", id = "ShebangDog")

        Content("Multiple Line Content\n End")

        Date()
        Divider()
        Reaction()
        Divider()
        Action()
    }
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
fun Content(content: String, modifier: Modifier = Modifier) {
    Text(
        text = content,
        modifier = modifier.fillMaxWidth().wrapContentHeight().padding(vertical = 8.dp)
    )
}

@Composable
fun Date(modifier: Modifier = Modifier) {
    Text(text = "2020/09/29 13:45 Twitter Web App", modifier = modifier.padding(vertical = 8.dp))
}

@Composable
fun Reaction(modifier: Modifier = Modifier) {
    Text(text = "1 いいね", modifier = modifier.padding(vertical = 8.dp))
}

@Composable
fun Action(modifier: Modifier = Modifier) {
    val iconSize = Modifier.size(24.dp)

    val icons = listOf(
        Icons.Default.ChatBubbleOutline,
        Icons.Default.Repeat,
        Icons.Default.FavoriteBorder,
        Icons.Default.Share
    )

    Row(modifier = modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        icons.forEach {
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
            TweetPreview()
        }
    }
}
