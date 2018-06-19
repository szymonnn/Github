package co.netguru.android.github.feature.users

import co.netguru.android.github.R
import co.netguru.android.github.data.api.UsersApi
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class UsersPresenter(val usersApi: UsersApi) : MvpBasePresenter<UsersContract.View>(), UsersContract.Presenter {

    override fun attachView(view: UsersContract.View) {
        super.attachView(view)
        view.searchText()
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { onSearch(it) },
                        onError = { it.printStackTrace() }
                )
        view.itemClick().subscribe { user -> ifViewAttached { view -> view.gotoUserDetails(user) } }
    }

    private fun onSearch(query: String) {
        if (query.isBlank()) {
            ifViewAttached { it.showEmptyView(); }
            return
        }
        ifViewAttached { it.progressBarVisibility(true) }
        usersApi.searchUsers(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ users ->
                    ifViewAttached { view ->
                        view.progressBarVisibility(false)
                        view.onSearchComplete(users)
                    }
                }, {
                    ifViewAttached { view ->
                        view.progressBarVisibility(false)
                        view.showMessage(R.string.err_default)
                    }
                })
    }

}