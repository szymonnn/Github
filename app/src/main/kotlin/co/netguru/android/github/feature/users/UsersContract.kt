package co.netguru.android.github.feature.users

import co.netguru.android.github.data.model.PagedResponse
import co.netguru.android.github.data.model.User
import co.netguru.android.github.feature.base.BaseView
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import io.reactivex.Observable

interface UsersContract {

    interface View : BaseView {
        fun onSearchComplete(users: PagedResponse<User>)

        fun searchText(): Observable<String>

        fun itemClick(): Observable<User>

        fun gotoUserDetails(user: User)

        fun showEmptyView()
    }

    interface Presenter : MvpPresenter<View>
}