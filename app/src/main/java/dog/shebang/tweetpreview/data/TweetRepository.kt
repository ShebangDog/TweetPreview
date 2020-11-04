package dog.shebang.tweetpreview.data

import dog.shebang.tweetpreview.data.model.Tweet
import dog.shebang.tweetpreview.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

interface TweetRepository {
    fun fetchTweet(tweetId: String): Flow<Tweet>

    fun fetchTweetList(): Flow<List<Tweet>>
}

class DefaultTweetRepository : TweetRepository {
    private val userList = listOf(
        User.create("@ShebangDog", "しばDog"),
        User.create("@AL", "momori"),
        User.create("@ruiui", "sinom"),
        User.create("@blabra", "warab"),
        User.create("@ogaoga", "crew"),
        User.create("@hoge", "hogeyan")
    )

    private val replyList = listOf(
        Tweet.create("なんで", userList.random()),
        Tweet.create("いいね", userList.random()),
        Tweet.create("おもしろい", userList.random()),
        Tweet.create("わかる", userList.random())
    )

    private val tweetList = listOf(
        Tweet.create("めしたべてきた", userList.random(), replyList.take(Random.nextInt(replyList.size))),
        Tweet.create("でーとしてきた", userList.random(), replyList.take(Random.nextInt(replyList.size))),
        Tweet.create("げーむくりあした", userList.random(), replyList.take(Random.nextInt(replyList.size))),
        Tweet.create("進捗爆上がり", userList.random(), replyList.take(Random.nextInt(replyList.size)))
    )

    override fun fetchTweet(tweetId: String) = flow {
        repeat(10) {
            kotlinx.coroutines.delay(1000L)

            emit(tweetList.random())
        }
    }

    override fun fetchTweetList() = flow {
        repeat(10) {
            kotlinx.coroutines.delay(1000L)

            emit(tweetList)
        }
    }
}