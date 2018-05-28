package co.netguru.android.github.feature.users

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import co.netguru.android.github.R
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_users.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UsersActivity : MvpActivity<UsersView, UsersPresenter>(), UsersView {

    @Inject
    lateinit var usersPresenter: UsersPresenter

    private lateinit var usersAdapter: UsersAdapter

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
        setupAdapter()
    }

    private fun setupSearch() {
        RxTextView.afterTextChangeEvents(searchEditText)
                .map { it.editable().toString() }
                .debounce(500, TimeUnit.MILLISECONDS)
                .filter { !it.isEmpty() }
                .subscribe { presenter.onSearch(it) }
    }

    private fun setupAdapter() {
        usersAdapter = UsersAdapter(presenter)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = usersAdapter
    }

    override fun onSearchComplete() {
        usersAdapter.notifyDataSetChanged()
    }

    override fun createPresenter(): UsersPresenter = usersPresenter
}