package co.netguru.android.github.feature.splash

import android.os.Handler
import co.netguru.android.github.application.scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class SplashModule {

    @ActivityScope
    @Provides
    fun handler(): Handler = Handler()
}