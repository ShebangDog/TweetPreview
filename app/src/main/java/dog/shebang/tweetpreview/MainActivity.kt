package dog.shebang.tweetpreview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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

    Profile(vectorIcon = icon, name = "しばDog", id = "ShebangDog")
}

@Composable
fun Profile(vectorIcon: VectorAsset, name: String, id: String) {
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TweetPreviewTheme {
        Surface(color = MaterialTheme.colors.background) {
            MyApp()
        }
    }
}
