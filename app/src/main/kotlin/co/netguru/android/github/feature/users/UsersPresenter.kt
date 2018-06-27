package co.netguru.android.github.feature.users

import co.netguru.android.github.data.api.UsersApi
import co.netguru.android.github.feature.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class UsersPresenter(val usersApi: UsersApi) : BasePresenter<UsersContract.View>(), UsersContract.Presenter {

    override fun attachView(view: UsersContract.View) {
        super.attachView(view)
        addViewDisposable(view.searchText()
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { onSearch(it) },
                        onError = { it.printStackTrace() })
        )
        addViewDisposable(view.itemClick().subscribe { user -> ifViewAttached { view -> view.gotoUserDetails(user) } })
    }

    private fun onSearch(query: String) {
        if (query.isBlank()) {
            ifViewAttached { it.showEmptyView(); }
            return
        }
        ifViewAttached {
            it.progressBarVisibility(true)
        }
        usersApi.searchUsers(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ users ->
                    ifViewAttached { view ->
                        view.onSearchComplete(users)
                        view.progressBarVisibility(false)
                    }
                }, {
                    ifViewAttached { view ->
                        view.progressBarVisibility(false)
                        view.showMessage(R.string.err_default)
                    }
                })
    }

}