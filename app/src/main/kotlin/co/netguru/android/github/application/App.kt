package co.netguru.android.github.application

import android.app.Activity
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import io.reactivex.plugins.RxJavaPlugins
import javax.inject.Inject

class App : DaggerApplication() {

    @Inject
    lateinit var debugMetricsHelper: DebugMetricsHelper

    @Inject
    lateinit var rxJavaErrorHandler: RxJavaErrorHandler

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler(rxJavaErrorHandler)
    }

    override fun applicationInjector(): AndroidInjector<App> = DaggerApplicationComponent.builder().create(this)

    override fun activityInjector() = activityDispatchingAndroidInjector
}
