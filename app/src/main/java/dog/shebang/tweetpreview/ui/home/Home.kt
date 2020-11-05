package dog.shebang.tweetpreview.ui.home

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.navigation.NavController
import dog.shebang.tweetpreview.data.DefaultTweetRepository
import dog.shebang.tweetpreview.data.model.Tweet
import dog.shebang.tweetpreview.ui.UserViewModel
import dog.shebang.tweetpreview.ui.UserViewModelFactory
import dog.shebang.tweetpreview.ui.detail.Tweet
import dog.shebang.tweetpreview.ui.navigation.navigateToDetail
import dog.shebang.tweetpreview.ui.navigation.navigateToPost
import dog.shebang.tweetpreview.ui.navigation.navigateToProfile

@Composable
fun HomeScreen(navController: NavController) {
    val userViewModel: UserViewModel = viewModel(
        factory = UserViewModelFactory(DefaultTweetRepository())
    )

    Scaffold(topBar = {
        val title = "Home"
        TopAppBar(
            title = { Text(text = title) },
            elevation = 8.dp,
            navigationIcon = {
                IconButton(
                    onClick = { navController.navigateToProfile() },
                    icon = { Icon(asset = Icons.Outlined.Person) }
                )
            }
        )
    },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigateToPost() },
                icon = { Icon(asset = Icons.Default.Add) })
        }) {

        HomeContent(navController, userViewModel)
    }
}

@Composable
fun HomeContent(navController: NavController, userViewModel: UserViewModel) {
    val tweetList by userViewModel.tweetList.observeAsState()

    tweetList?.let { list ->
        TweetList(tweetList = list) {
            navController.navigateToDetail(it)
        }
    }
}

@Composable
fun TweetList(modifier: Modifier = Modifier, tweetList: List<Tweet>, onClick: (String) -> Unit) {
    LazyColumnForIndexed(
        items = tweetList,
        modifier = modifier.padding(horizontal = 8.dp),
        itemContent = { index, item ->
            val topPadding = (if (index == 0) 8 else 0).dp

            Tweet(
                tweet = item,
                modifier = Modifier.padding(top = topPadding, bottom = 8.dp),
                showDetail = false,
                onClick = { onClick(item.id) }
            )
        })
}
