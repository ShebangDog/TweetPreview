package dog.shebang.tweetpreview.data.model

import android.net.Uri

data class User(val id: String, val name: String, val imageUrl: Uri) {
    companion object {
        fun create(id: String, name: String) = User(id, name, Uri.EMPTY)
    }
}