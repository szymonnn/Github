package co.netguru.android.github.feature.details

import co.netguru.android.github.R
import co.netguru.android.github.data.api.UsersApi
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class DetailsPresenter(val usersApi: UsersApi) : MvpBasePresenter<DetailsContract.View>(), DetailsContract.Presenter {
    private lateinit var login: String

    override fun attachView(view: DetailsContract.View) {
        super.attachView(view)
        view.provideLogin()
                .doOnNext { login = it }
                .subscribeBy {
                    ifViewAttached { view -> view.progressBarVisibility(true) }
                    val userDetailsObservable = usersApi.getUserDetails(login)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                    val userReposObservable = usersApi.getUserRepos(login)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                    Observables.zip(userDetailsObservable, userReposObservable)
                            .subscribeBy(
                                    onNext = {
                                        ifViewAttached { view ->
                                            view.onDataFetched(it.first, it.second)
                                            view.progressBarVisibility(false)
                                        }
                                    },
                                    onError = {
                                        ifViewAttached {
                                            it.showMessage(R.string.err_default)
                                            view.progressBarVisibility(false)

                                        }
                                    }
                            )
                }
    }
}