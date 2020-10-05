package dog.shebang.tweetpreview.data

import dog.shebang.tweetpreview.data.model.Tweet
import kotlinx.coroutines.flow.Flow

interface TweetRepository {
    fun fetchTweet(tweetId: String): Flow<Tweet>
}