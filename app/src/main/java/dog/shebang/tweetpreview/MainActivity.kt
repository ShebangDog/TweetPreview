package dog.shebang.tweetpreview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.vectorResource
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
    val fillModifier = Modifier.fillMaxHeight().fillMaxWidth()
    val icon = vectorResource(id = R.drawable.ic_android_black_24dp)

    Column(modifier = fillModifier) {
        Title(title = "Title")

        Thumbnail(vectorIcon = icon)

        Content(content = "Contents\nMultiline")
    }
}

@Composable
fun Thumbnail(vectorIcon: VectorAsset) {
    Image(asset = vectorIcon)
}

@Composable
fun Title(title: String) {
    Text(text = title)
}

@Composable
fun Content(content: String) {
    Text(text = content)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TweetPreviewTheme { MyApp() }
}
