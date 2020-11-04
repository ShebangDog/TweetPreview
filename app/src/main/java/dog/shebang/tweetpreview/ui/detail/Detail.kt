package dog.shebang.tweetpreview.ui.detail

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.viewModel
import dev.chrisbanes.accompanist.glide.GlideImage
import dog.shebang.tweetpreview.data.DefaultTweetRepository
import dog.shebang.tweetpreview.data.model.PostedTime
import dog.shebang.tweetpreview.data.model.ReactionState
import dog.shebang.tweetpreview.data.model.Tweet
import dog.shebang.tweetpreview.data.model.User
import dog.shebang.tweetpreview.ui.UserViewModel
import dog.shebang.tweetpreview.ui.UserViewModelFactory

@Composable
fun DetailScreen(innerPadding: PaddingValues) {
    val userViewModel: UserViewModel = viewModel(
        factory = UserViewModelFactory(DefaultTweetRepository())
    )

    DetailContent(userViewModel, innerPadding)
}

@Composable
fun DetailContent(viewModel: UserViewModel, innerPadding: PaddingValues) {
    val tweet by viewModel.tweet("").observeAsState()

    tweet?.let { Tweet(modifier = Modifier.padding(innerPadding).padding(all = 8.dp), tweet = it) }
}

@Composable
fun Tweet(modifier: Modifier = Modifier, tweet: Tweet, showDetail: Boolean = true) {
    val smallPadding = 8.dp

    Card(
        elevation = 2.dp,
        modifier = modifier.clip(RoundedCornerShape(16.dp)),
        backgroundColor = Color.Gray
    ) {

        Column(modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(smallPadding)) {
            Author(author = tweet.author)
            Spacer(modifier = Modifier.preferredHeight(smallPadding))
            Content(content = tweet.content)
            Spacer(modifier = Modifier.preferredHeight(smallPadding))
            PostedTime(postedTime = tweet.postedTime)
            Spacer(modifier = Modifier.preferredHeight(8.dp))
            Divider()
            if (showDetail) {
                Reaction(reactionState = tweet.reactionState)
                Divider()
            }
            ReActionBar()
        }
    }
}

@Composable
fun Author(modifier: Modifier = Modifier, author: User) {
    @Composable
    fun AccountIcon(data: String) {
        GlideImage(
            data = data,
            loading = {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            },
            modifier = Modifier.preferredSize(48.dp).clip(CircleShape),
        )
    }

    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        AccountIcon("https://avatars3.githubusercontent.com/u/38370581?s=460&u=2f167ff1f76ad0097d4097ed48041afbaca44bfc&v=4")

        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(text = author.name)

            Text(text = author.id, fontSize = 12.sp)
        }
    }
}

@Composable
fun Content(modifier: Modifier = Modifier, content: String) {
    Text(text = content, modifier = modifier)
}

@Composable
fun PostedTime(modifier: Modifier = Modifier, postedTime: PostedTime) {
    Text(
        modifier = modifier,
        text = "${postedTime.formatted()} Twitter for Android",
        style = TextStyle(color = Color.DarkGray, fontSize = 12.sp)
    )
}

@Composable
fun Reaction(modifier: Modifier = Modifier, reactionState: ReactionState) {
    val eachModifier = Modifier.padding(start = 4.dp)

    Row(modifier.padding(vertical = 8.dp)) {
        Text(
            text = "${reactionState.good}${ReactionState.goodPostFix}",
            modifier = eachModifier
        )

        Text(
            text = "${reactionState.retweet}${ReactionState.retweetPostFix}",
            modifier = eachModifier
        )
    }
}

@Composable
fun ReActionBar(modifier: Modifier = Modifier) {
    val iconSize = 24.dp

    Row(modifier = modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        ReactionState.stateIconList.forEach { icon ->
            IconButton(
                onClick = {},
                modifier = Modifier.size(iconSize).weight(1f),
                icon = { Icon(asset = icon) })
        }
    }
}