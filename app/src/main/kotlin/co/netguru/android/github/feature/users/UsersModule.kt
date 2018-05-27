package co.netguru.android.github.feature.users

import co.netguru.android.github.application.scope.ActivityScope
import co.netguru.android.github.data.api.UsersApi
import dagger.Module
import dagger.Provides

@Module
class UsersModule {
    @ActivityScope
    @Provides
    fun providePresenter(usersApi: UsersApi) = UsersPresenter(usersApi)
}