package dog.shebang.tweetpreview.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.Share
import java.util.*
import kotlin.random.Random

data class Tweet(
    val id: String,
    val content: String,
    val postedTime: PostedTime,
    val reactionState: ReactionState,
    val author: User
) {
    companion object {
        fun create(content: String, author: User, reply: List<Tweet> = emptyList()): Tweet {
            return Tweet(
                UUID.randomUUID().toString(),
                content,
                PostedTime(
                    Date(2020, 10, 5),
                    Time(Random.nextInt(24), Random.nextInt(60))
                ),
                ReactionState(reply, Random.nextInt(5), Random.nextInt(10)),
                author
            )
        }
    }
}

data class PostedTime(val date: Date, val time: Time) {
    fun formatted() = "${date.formatted()} ${time.formatted()}"
}

data class Date(val year: Int, val month: Int, val day: Int) {
    fun formatted(): String {
        val monthAndDay = listOf(month, day).joinToString(separator) { "0$it".takeLast(2) }

        return "$year$separator$monthAndDay"
    }

    companion object {
        const val separator = "/"
    }
}

data class Time(val hour: Int, val minute: Int) {
    fun formatted() = listOf(hour, minute).joinToString(separator) { "0$it".takeLast(2) }

    companion object {
        const val separator = ":"
    }
}

data class ReactionState(val replyList: List<Tweet>, val retweet: Int, val good: Int) {
    companion object {
        const val retweetPostFix = "リツイート"
        const val goodPostFix = "いいね"

        val stateIconList = listOf(
            Icons.Default.ChatBubbleOutline,
            Icons.Default.Repeat,
            Icons.Default.FavoriteBorder,
            Icons.Default.Share
        )
    }
}