package co.netguru.android.github.application

import co.netguru.android.github.application.scope.AppScope
import dagger.Module
import dagger.Provides


@Module
class ApplicationModule {

    @AppScope
    @Provides
    fun rxJavaErrorHandler(): RxJavaErrorHandler = RxJavaErrorHandlerImpl()
}
