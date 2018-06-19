package co.netguru.android.github.feature.details

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface DetailsContract {
    interface View : MvpView {

    }

    interface Presenter : MvpPresenter<View> {

    }
}