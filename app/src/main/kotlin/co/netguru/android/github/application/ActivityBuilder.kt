package co.netguru.android.github.application

import co.netguru.android.github.application.scope.ActivityScope
import co.netguru.android.github.feature.details.DetailsActivity
import co.netguru.android.github.feature.details.DetailsModule
import co.netguru.android.github.feature.splash.SplashActivity
import co.netguru.android.github.feature.splash.SplashModule
import co.netguru.android.github.feature.users.UsersActivity
import co.netguru.android.github.feature.users.UsersModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = [SplashModule::class])
    abstract fun bindSplashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [UsersModule::class])
    abstract fun bindUsersActivity(): UsersActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [DetailsModule::class])
    abstract fun bindDetailsActivity(): DetailsActivity
}