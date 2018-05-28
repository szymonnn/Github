package co.netguru.android.github.application

import co.netguru.android.github.application.scope.AppScope
import co.netguru.android.github.data.api.ApiModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(
        modules = [
            AndroidInjectionModule::class,
            AndroidSupportInjectionModule::class,
            ApplicationModule::class,
            ActivityBuilder::class,
            ApiModule::class
        ]
)
interface ApplicationComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}


