package co.netguru.android.github.feature.users

import com.hannesdorfmann.mosby3.mvp.MvpView

interface UsersView : MvpView {
    fun onSearchComplete()
}