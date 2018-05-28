package co.netguru.android.github.feature.users

import co.netguru.android.github.data.api.UsersApi
import dagger.Module
import dagger.Provides

@Module
class UsersModule {

    @Provides
    fun providePresenter(usersApi: UsersApi) = UsersPresenter(usersApi)
}