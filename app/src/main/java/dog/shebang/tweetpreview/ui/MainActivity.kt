package dog.shebang.tweetpreview.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import dog.shebang.tweetpreview.data.DefaultTweetRepository
import dog.shebang.tweetpreview.data.model.PostedTime
import dog.shebang.tweetpreview.data.model.ReactionState
import dog.shebang.tweetpreview.data.model.Tweet
import dog.shebang.tweetpreview.data.model.User
import dog.shebang.tweetpreview.theme.TweetPreviewTheme

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<UserViewModel> {
        ViewModelFactory(DefaultTweetRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TweetPreviewTheme {
                TweetPreview(viewModel)
            }
        }
    }
}

@Composable
fun TweetPreview(viewModel: UserViewModel) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Tweet Preview") },
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(asset = Icons.Default.ArrowBack)
                }
            }
        )
    }) { innerPadding ->

        val tweet by viewModel.tweet("").observeAsState()

        tweet?.let {
            BodyContents(modifier = Modifier.padding(innerPadding), it)
        }
    }
}

@Composable
fun BodyContents(modifier: Modifier = Modifier, tweet: Tweet) {

    Column(modifier = modifier.fillMaxHeight().fillMaxWidth().padding(8.dp)) {

        Profile(vectorIcon = Icons.Default.Person, author = tweet.author)

        Content(content = tweet.content)

        PostedTime(postedTime = tweet.postedTime)

        Divider()

        Reaction(reactionState = tweet.reactionState)

        Divider()

        Action()
    }
}

@Composable
fun Profile(vectorIcon: VectorAsset, author: User) {
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
            Name(name = author.name)
            Id(id = author.id)
        }
    }
}

@Composable
fun Content(modifier: Modifier = Modifier, content: String) {
    Text(
        text = content,
        modifier = modifier.fillMaxWidth().wrapContentHeight().padding(vertical = 8.dp)
    )
}

@Composable
fun PostedTime(modifier: Modifier = Modifier, postedTime: PostedTime) {
    Text(
        text = "${postedTime.formatted()} Twitter Web App",
        modifier = modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun Reaction(modifier: Modifier = Modifier, reactionState: ReactionState) {
    val normalPadding = Modifier.padding(start = 8.dp)

    Row(modifier.padding(vertical = 8.dp)) {
        Text(
            text = "${reactionState.retweet} ${ReactionState.retweetPostFix}",
            modifier = normalPadding
        )

        Text(
            text = "${reactionState.good} ${ReactionState.goodPostFix}",
            modifier = normalPadding
        )
    }
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
            TweetPreview(viewModel())
        }
    }
}
