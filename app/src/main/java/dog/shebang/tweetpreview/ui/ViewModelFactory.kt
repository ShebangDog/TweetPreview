package dog.shebang.tweetpreview.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dog.shebang.tweetpreview.data.TweetRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: TweetRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(repository) as T
    }
}