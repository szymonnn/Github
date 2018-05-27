package co.netguru.android.github.application

import android.app.Application
import co.netguru.android.github.application.scope.AppScope
import co.netguru.android.github.data.api.ApiModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
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
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}


