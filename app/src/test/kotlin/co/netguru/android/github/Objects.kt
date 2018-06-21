package co.netguru.android.github

import co.netguru.android.github.data.model.PagedResponse
import co.netguru.android.github.data.model.User
import co.netguru.android.github.data.model.details.Repo
import co.netguru.android.github.data.model.details.UserDetails

object Objects {
    val USERS = PagedResponse(2, false, listOf(User(id = 1, login = "user1"), User(id = 2, login = "user2")))
    const val QUERY = "user"
    const val EMPTY_QUERY = ""
    const val LOGIN = "szymonnn"
    val DETAILS = UserDetails(id = 1, login = "user1")
    val REPOS = listOf(Repo(name = "repo1"), Repo(name = "repo2"))
    val DETAILS_RESPONSE = Pair(DETAILS, REPOS)
}