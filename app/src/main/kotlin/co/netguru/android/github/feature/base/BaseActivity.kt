package co.netguru.android.github.feature.base

import android.support.annotation.CallSuper
import android.support.test.espresso.idling.CountingIdlingResource
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import javax.inject.Inject

abstract class BaseActivity<V : MvpView?, P : MvpPresenter<V>?> : MvpActivity<V, P>(), BaseView {

    @Inject
    lateinit var idlingResource: CountingIdlingResource

    @CallSuper
    override fun progressBarVisibility(visible: Boolean) {
        if (visible) {
            idlingResource.increment()
        } else {
            idlingResource.decrement()
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(res: Int) {
        showMessage(getString(res))
    }
}