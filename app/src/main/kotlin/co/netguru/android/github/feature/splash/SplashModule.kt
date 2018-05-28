package co.netguru.android.github.feature.splash

import android.os.Handler
import dagger.Module
import dagger.Provides

@Module
class SplashModule {

    @Provides
    fun handler(): Handler = Handler()
}