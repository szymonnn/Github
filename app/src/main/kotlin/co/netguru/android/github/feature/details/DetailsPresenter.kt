package co.netguru.android.github.feature.details

import co.netguru.android.github.data.api.UsersApi
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

class DetailsPresenter(usersApi: UsersApi) : MvpBasePresenter<DetailsContract.View>(), DetailsContract.Presenter {

    override fun attachView(view: DetailsContract.View) {
        super.attachView(view)
    }
}