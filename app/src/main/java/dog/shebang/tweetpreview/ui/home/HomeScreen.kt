package dog.shebang.tweetpreview.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import dog.shebang.tweetpreview.data.DefaultTweetRepository
import dog.shebang.tweetpreview.data.model.Tweet
import dog.shebang.tweetpreview.ui.UserViewModel
import dog.shebang.tweetpreview.ui.UserViewModelFactory
import dog.shebang.tweetpreview.ui.detail.Tweet

@Composable
fun HomeScreen(innerPadding: PaddingValues) {
    val userViewModel: UserViewModel = viewModel(
        factory = UserViewModelFactory(DefaultTweetRepository())
    )

    HomeContent(innerPadding, userViewModel)
}

@Composable
fun HomeContent(innerPadding: PaddingValues, userViewModel: UserViewModel) {
    val tweetList by userViewModel.tweetList.observeAsState()

    tweetList?.let { TweetList(modifier = Modifier.padding(innerPadding), it) }
}

@Composable
fun TweetList(modifier: Modifier = Modifier, tweetList: List<Tweet>) {
    LazyColumnForIndexed(
        items = tweetList,
        modifier = modifier.padding(horizontal = 8.dp),
        itemContent = { index, item ->
            val topPadding = (if (index == 0) 8 else 0).dp

            Tweet(
                tweet = item,
                modifier = Modifier.padding(top = topPadding, bottom = 8.dp),
                showDetail = false
            )
        })
}
