package co.netguru.android.github.application

import android.support.test.espresso.idling.CountingIdlingResource
import co.netguru.android.github.application.scope.AppScope
import dagger.Module
import dagger.Provides


@Module
class ApplicationModule {

    @AppScope
    @Provides
    fun rxJavaErrorHandler(): RxJavaErrorHandler = RxJavaErrorHandlerImpl()

    @AppScope
    @Provides
    fun idlingResource() = CountingIdlingResource("IDLING")
}
