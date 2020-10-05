package dog.shebang.tweetpreview.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dog.shebang.tweetpreview.data.TweetRepository

class UserViewModel(private val tweetRepository: TweetRepository) : ViewModel() {

    fun tweet(id: String) = tweetRepository.fetchTweet(id).asLiveData()
}