package co.netguru.android.github.feature.details

import co.netguru.android.github.data.model.details.Repo
import co.netguru.android.github.data.model.details.UserDetails
import co.netguru.android.github.feature.base.BaseView
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import io.reactivex.Observable

interface DetailsContract {
    interface View : BaseView {
        fun provideLogin(): Observable<String>
        fun onDataFetched(details: UserDetails, repos: List<Repo>)
    }

    interface Presenter : MvpPresenter<View> {
        fun getData(login: String)

    }
}