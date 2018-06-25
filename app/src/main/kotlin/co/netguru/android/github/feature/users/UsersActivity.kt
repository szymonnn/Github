package co.netguru.android.github.feature.users

import android.support.v7.widget.LinearLayoutManager
import co.netguru.android.github.data.model.PagedResponse
import co.netguru.android.github.data.model.User
import co.netguru.android.github.feature.base.BaseActivity
import co.netguru.android.github.feature.details.DetailsActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import dagger.android.AndroidInjection
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class UsersActivity : BaseActivity<UsersContract.View, UsersContract.Presenter>(), UsersContract.View {

    @Inject
    lateinit var usersPresenter: UsersContract.Presenter

    private lateinit var usersAdapter: UsersAdapter

    val itemClick = PublishSubject.create<User>()

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
        usersAdapter.itemClick = itemClick
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = usersAdapter
    }

    override fun searchText() = RxTextView.afterTextChangeEvents(searchEditText)
            .map { it.editable().toString() }

    override fun itemClick() = itemClick

    override fun gotoUserDetails(user: User) {
        DetailsActivity.start(this, user)
    }

    override fun onSearchComplete(users: PagedResponse<User>) {
        recyclerView.visibility = View.VISIBLE
        emptyList.visibility = View.INVISIBLE
        usersAdapter.setItems(users.items)
    }

    override fun progressBarVisibility(visible: Boolean) {
        super.progressBarVisibility(visible)
        progressBar.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }

    override fun createPresenter(): UsersContract.Presenter = usersPresenter

    override fun showEmptyView() {
        recyclerView.visibility = View.INVISIBLE
        emptyList.visibility = View.VISIBLE
    }
}