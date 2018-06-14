package co.netguru.android.github.feature.users

import co.netguru.android.github.Objects
import co.netguru.android.github.RxTestSchedulerOverrideRule
import co.netguru.android.github.data.api.UsersApi
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UsersPresenterTest {


    @JvmField
    @Rule
    var rule = RxTestSchedulerOverrideRule()
    var view = mock<UsersContract.View>()
    var usersApi = mock<UsersApi>()
    var presenter = UsersPresenter(usersApi)

    @Before
    fun setUp() {
        rule = RxTestSchedulerOverrideRule()
        usersApi = mock()
        presenter = UsersPresenter(usersApi)
    }

    @Test
    fun whenSearchUsers_thenReturnListOfUsers() {
        whenever(view.searchText()).thenReturn(Observable.just(Objects.QUERY))
        whenever(usersApi.searchUsers(Objects.QUERY)).thenReturn(Observable.just(Objects.USERS))
        presenter.attachView(view)
        verify(view).onSearchComplete(Objects.USERS)
    }

    @Test
    fun whenSearchWithEmptyQuery_thenReturnEmptyList() {
        whenever(view.searchText()).thenReturn(Observable.just(Objects.EMPTY_QUERY))
        whenever(usersApi.searchUsers(Objects.EMPTY_QUERY)).thenReturn(Observable.just(Objects.EMPTY_USERS))
        presenter.attachView(view)
        verify(view).onSearchComplete(Objects.EMPTY_USERS)
    }
}