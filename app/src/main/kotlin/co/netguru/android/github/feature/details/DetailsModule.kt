package co.netguru.android.github.feature.details

import co.netguru.android.github.data.api.UsersApi
import dagger.Module
import dagger.Provides

@Module
class DetailsModule {
    @Provides
    fun providePresenter(usersApi: UsersApi): DetailsContract.Presenter = DetailsPresenter(usersApi)
}