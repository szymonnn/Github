package co.netguru.android.github.feature.users

import android.content.Context
import android.content.Intent
import android.os.Bundle
import co.netguru.android.github.R
import co.netguru.android.github.data.model.PagedResponse
import co.netguru.android.github.data.model.User
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_users.*
import timber.log.Timber
import javax.inject.Inject

class UsersActivity : MvpActivity<UsersView, UsersPresenter>(), UsersView {
    @Inject
    lateinit var usersPresenter: UsersPresenter

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, UsersActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        supportActionBar?.title = getString(R.string.users_title)
        setupSearch()
    }

    private fun setupSearch() {
        RxTextView.afterTextChangeEvents(searchEditText)
                .map { it.editable().toString() }
                .filter { !it.isEmpty() }
                .subscribe { presenter.onSearch(it) }
    }

    override fun onSearchComplete(users: PagedResponse<User>?) {
        users?.items?.forEach {
            Timber.i(it.login)
        }
    }

    override fun createPresenter(): UsersPresenter = usersPresenter
}