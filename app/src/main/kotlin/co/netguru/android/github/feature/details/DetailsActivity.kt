package co.netguru.android.github.feature.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import co.netguru.android.github.R
import co.netguru.android.github.data.model.User
import co.netguru.android.github.data.model.details.Repo
import co.netguru.android.github.data.model.details.UserDetails
import co.netguru.android.github.feature.base.BaseActivity
import dagger.android.AndroidInjection
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailsActivity : BaseActivity<DetailsContract.View, DetailsContract.Presenter>(), DetailsContract.View {

    @Inject
    lateinit var detailsPresenter: DetailsContract.Presenter

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
    }

    override fun provideLogin() = Observable.just(intent.getStringExtra(EXTRA_USER_LOGIN))

    override fun onDataFetched(details: UserDetails?, repos: List<Repo>?) {
        Toast.makeText(this, "${details?.login} ${repos?.size}", Toast.LENGTH_SHORT).show()
    }

    override fun progressBarVisibility(visible: Boolean) {
        progressBar.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }

    override fun createPresenter() = detailsPresenter

}