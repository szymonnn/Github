package co.netguru.android.github.feature.splash

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import co.netguru.android.github.feature.users.UsersActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {
    private val splashTimeMs = 1000L

    @Inject
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        startHandler()
    }

    private fun startHandler() {
        handler.postDelayed({
            UsersActivity.start(this)
            finish()
        }, splashTimeMs)
    }
}
