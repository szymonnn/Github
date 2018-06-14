package co.netguru.android.github.feature.users

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import co.netguru.android.github.R
import co.netguru.android.github.data.model.PagedResponse
import co.netguru.android.github.data.model.User
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_users.*
import javax.inject.Inject

class UsersActivity : MvpActivity<UsersContract.View, UsersContract.Presenter>(), UsersContract.View {

    @Inject
    lateinit var usersPresenter: UsersContract.Presenter

    private lateinit var usersAdapter: UsersAdapter

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, UsersActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_users)
        setupAdapter()
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.users_title)
    }

    private fun setupAdapter() {
        usersAdapter = UsersAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = usersAdapter
    }

    override fun searchText() = RxTextView.afterTextChangeEvents(searchEditText)
            .map { it.editable().toString() }

    override fun itemClick() = usersAdapter.itemClick

    override fun gotoUserDetails(user: User) {
        Toast.makeText(this, user.login, Toast.LENGTH_LONG).show()
    }

    override fun onSearchComplete(users: PagedResponse<User>) {
        usersAdapter.setItems(users.items)
    }

    override fun createPresenter(): UsersContract.Presenter = usersPresenter
}