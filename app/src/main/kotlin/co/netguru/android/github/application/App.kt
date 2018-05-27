package co.netguru.android.github.application

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.reactivex.plugins.RxJavaPlugins
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var debugMetricsHelper: DebugMetricsHelper

    @Inject
    lateinit var rxJavaErrorHandler: RxJavaErrorHandler

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
        RxJavaPlugins.setErrorHandler(rxJavaErrorHandler)
    }

    override fun activityInjector() = activityDispatchingAndroidInjector
}
