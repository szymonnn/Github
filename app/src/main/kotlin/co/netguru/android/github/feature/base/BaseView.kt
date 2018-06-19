package co.netguru.android.github.feature.base

import android.support.annotation.StringRes
import com.hannesdorfmann.mosby3.mvp.MvpView

interface BaseView : MvpView {

    fun progressBarVisibility(visible: Boolean)

    fun showMessage(@StringRes res: Int)

    fun showMessage(message: String)
}