package dog.shebang.tweetpreview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
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
    val fillModifier = Modifier
        .padding(all = 16.dp)
        .fillMaxHeight()
        .fillMaxWidth()

    val icon = vectorResource(id = R.drawable.ic_android_black_24dp)

    Column(modifier = fillModifier) {
        Title(title = "Title")

        Thumbnail(vectorIcon = icon)

        Content(content = "Contents\nMultiline")
    }
}

@Composable
fun Thumbnail(vectorIcon: VectorAsset) {
    Image(
        asset = vectorIcon,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(align = Alignment.CenterHorizontally)
    )
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(align = Alignment.CenterHorizontally)
    )
}

@Composable
fun Content(content: String) {
    Text(
        text = content,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TweetPreviewTheme { MyApp() }
}
