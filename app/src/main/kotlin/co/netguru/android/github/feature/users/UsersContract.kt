package co.netguru.android.github.feature.users

import co.netguru.android.github.data.model.PagedResponse
import co.netguru.android.github.data.model.User
import com.hannesdorfmann.mosby3.mvp.MvpView

interface UsersView : MvpView {
    fun onSearchComplete(users: PagedResponse<User>?)
}