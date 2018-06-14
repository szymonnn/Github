package co.netguru.android.github.feature.users

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
                .subscribeBy(
                        onNext = { onSearch(it) },
                        onError = { it.printStackTrace() }
                )
        view.itemClick().subscribeBy {
            view.gotoUserDetails(it)
        }
    }

    private fun onSearch(query: String) {
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