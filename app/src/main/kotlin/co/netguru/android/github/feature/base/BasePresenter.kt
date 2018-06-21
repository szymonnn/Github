package co.netguru.android.github.feature.base

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter<V : MvpView> : MvpBasePresenter<V>() {
    private val viewDisposables: CompositeDisposable = CompositeDisposable()

    override fun detachView() {
        viewDisposables.dispose()
        super.detachView()
    }

    fun addViewDisposable(disposable: Disposable) {
        viewDisposables.add(disposable)
    }
}