package dog.shebang.tweetpreview.data.model

data class Tweet(
    val id: String,
    val content: String,
    val postedTime: PostedTime,
    val reactionState: ReactionState,
    val author: User
)

data class PostedTime(val date: Date, val time: Time)

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

data class ReactionState(val replyList: List<Tweet>, val retweet: Int, val good: Int)