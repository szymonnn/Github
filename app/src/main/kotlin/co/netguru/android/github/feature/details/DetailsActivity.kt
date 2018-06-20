package co.netguru.android.github.feature.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import co.netguru.android.github.R
import co.netguru.android.github.data.model.User
import co.netguru.android.github.data.model.details.Repo
import co.netguru.android.github.data.model.details.UserDetails
import co.netguru.android.github.feature.base.BaseActivity
import com.bumptech.glide.Glide
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailsActivity : BaseActivity<DetailsContract.View, DetailsContract.Presenter>(), DetailsContract.View {

    @Inject
    lateinit var detailsPresenter: DetailsContract.Presenter

    private lateinit var repoAdapter: RepoAdapter

    val itemClick = PublishSubject.create<Repo>()

    companion object {
        private const val EXTRA_USER_LOGIN = "extra:login"
        fun start(context: Context, user: User) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(EXTRA_USER_LOGIN, user.login)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_details)
        super.onCreate(savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter() {
        repoAdapter = RepoAdapter()
        repoAdapter.itemClick = itemClick
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = repoAdapter
    }

    override fun provideLogin() = Observable.just(intent.getStringExtra(EXTRA_USER_LOGIN))

    override fun onDataFetched(details: UserDetails?, repos: List<Repo>?) {
        divider.visibility = View.VISIBLE
        Glide.with(this).load(details?.avatarUrl).into(avatarImageView)
        loginTextView.text = details?.login
        nameTextView.text = details?.name
        typeTextView.text = getString(R.string.format_type, details?.type)
        companyTextView.text = getString(R.string.format_company, details?.company)
        websiteTextView.text = getString(R.string.format_website, details?.blog)
        locationTextView.text = getString(R.string.format_location, details?.location)
        repoAdapter.setItems(repos)
    }

    override fun progressBarVisibility(visible: Boolean) {
        progressBar.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }

    override fun createPresenter() = detailsPresenter

}