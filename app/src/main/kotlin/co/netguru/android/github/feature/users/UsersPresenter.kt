package co.netguru.android.github.feature.users

import co.netguru.android.github.data.api.UsersApi
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UsersPresenter(val usersApi: UsersApi) : MvpBasePresenter<UsersView>() {

    fun onSearch(query: String) {
        usersApi.searchUsers(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ users ->
                    ifViewAttached { view ->
                        view.onSearchComplete(users)
                    }
                }, {
                    it.printStackTrace()
                })
    }


}