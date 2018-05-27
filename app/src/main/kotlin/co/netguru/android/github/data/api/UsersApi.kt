package co.netguru.android.github.data.api

import co.netguru.android.github.data.model.PagedResponse
import co.netguru.android.github.data.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {

    @GET("search/users")
    fun searchUsers(@Query("q") query: String): Observable<PagedResponse<User>>
}