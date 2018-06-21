package co.netguru.android.github.feature.details

import co.netguru.android.github.R
import co.netguru.android.github.data.api.UsersApi
import co.netguru.android.github.feature.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class DetailsPresenter(val usersApi: UsersApi) : BasePresenter<DetailsContract.View>(), DetailsContract.Presenter {

    override fun getData(login: String) {
        if (login.isBlank()) {
            ifViewAttached { view ->
                view.showMessage(R.string.err_default)
            }
            return
        }
        ifViewAttached { view -> view.progressBarVisibility(true) }
        Observables.zip(usersApi.getUserDetails(login), usersApi.getUserRepos(login))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onNext = {
                            ifViewAttached { view ->
                                view.onDataFetched(it.first, it.second)
                                view.progressBarVisibility(false)
                            }
                        },
                        onError = {
                            ifViewAttached { view ->
                                view.showMessage(R.string.err_default)
                                view.progressBarVisibility(false)

                            }
                        }
                )
    }
}