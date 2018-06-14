package co.netguru.android.github

import co.netguru.android.github.data.model.PagedResponse
import co.netguru.android.github.data.model.User

object Objects {
    val USERS = PagedResponse(2, false, listOf(User(id = 1, login = "user1"), User(id = 2, login = "user2")))
    const val QUERY = "user"
    const val EMPTY_QUERY = ""
    val EMPTY_USERS = PagedResponse<User>(0, false, listOf())
}