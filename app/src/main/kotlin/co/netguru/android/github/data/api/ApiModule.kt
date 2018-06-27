package co.netguru.android.github.data.api

import co.netguru.android.github.application.scope.AppScope
import co.netguru.android.github.common.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    @AppScope
    @Provides
    fun provideGson() = GsonBuilder().setDateFormat("yyyy-MM-dd'T'hh:mm:ssZ").create()

    @AppScope
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
    }

    @AppScope
    @Provides
    fun provideUsersApi(retrofit: Retrofit): UsersApi {
        return retrofit.create<UsersApi>(UsersApi::class.java)
    }
}