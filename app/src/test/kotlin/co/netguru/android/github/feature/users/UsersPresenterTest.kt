package co.netguru.android.github.feature.users

import co.netguru.android.github.Objects
import co.netguru.android.github.R
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
        whenever(view.itemClick()).thenReturn(Observable.just(Objects.USERS.items[0]))
        whenever(view.searchText()).thenReturn(Observable.just(Objects.QUERY))
        rule = RxTestSchedulerOverrideRule()
        usersApi = mock()
        presenter = UsersPresenter(usersApi)
    }

    @Test
    fun whenSearchUsers_thenReturnListOfUsers() {
        whenever(usersApi.searchUsers(Objects.QUERY)).thenReturn(Observable.just(Objects.USERS))
        presenter.attachView(view)
        verify(view).onSearchComplete(Objects.USERS)
    }

    @Test
    fun whenSearchUsersError_thenShowMessage() {
        whenever(usersApi.searchUsers(Objects.QUERY)).thenReturn(Observable.error(Exception("Server didn't respond")))
        presenter.attachView(view)
        verify(view).showMessage(R.string.err_default)
    }

    @Test
    fun whenSearchWithEmptyQuery_thenShowEmptyView() {
        whenever(view.searchText()).thenReturn(Observable.just(Objects.EMPTY_QUERY))
        presenter.attachView(view)
        verify(view).showEmptyView()
    }

    @Test
    fun whenUserSelected_thenGoToUserDetails() {
        whenever(usersApi.searchUsers(Objects.QUERY)).thenReturn(Observable.just(Objects.USERS))
        presenter.attachView(view)
        verify(view).gotoUserDetails(Objects.USERS.items[0])
    }
}