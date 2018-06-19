package co.netguru.android.github.feature.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import co.netguru.android.github.R
import co.netguru.android.github.data.model.User
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class DetailsActivity : MvpActivity<DetailsContract.View, DetailsContract.Presenter>(), DetailsContract.View {

    @Inject
    lateinit var detailsPresenter: DetailsContract.Presenter

    override fun createPresenter() = detailsPresenter

    companion object {
        private const val EXTRA_USER_ID = "extra:id"
        fun start(context: Context, user: User) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(EXTRA_USER_ID, user.id)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_details)
        super.onCreate(savedInstanceState)
    }

}