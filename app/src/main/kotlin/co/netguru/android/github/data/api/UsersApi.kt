package co.netguru.android.github.data.api

import co.netguru.android.github.data.model.PagedResponse
import co.netguru.android.github.data.model.User
import co.netguru.android.github.data.model.details.Repo
import co.netguru.android.github.data.model.details.UserDetails
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UsersApi {

    @GET("search/users")
    fun searchUsers(@Query("q") query: String): Observable<PagedResponse<User>>

    @GET("users/{login}")
    fun getUserDetails(@Path("login") login: String): Observable<UserDetails>

    @GET("users/{login}/repos")
    fun getUserRepos(@Path("login") login: String): Observable<List<Repo>>

}