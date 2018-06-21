package co.netguru.android.github.feature.details

import co.netguru.android.github.Objects
import co.netguru.android.github.RxTestSchedulerOverrideRule
import co.netguru.android.github.data.api.UsersApi
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailsPresenterTest {
    @JvmField
    @Rule
    var rule = RxTestSchedulerOverrideRule()

    private lateinit var usersApi: UsersApi

    private lateinit var presenter: DetailsPresenter

    private lateinit var view: DetailsContract.View

    @Before
    fun setUp() {
        usersApi = mock()
        presenter = DetailsPresenter(usersApi)
        view = mock()
    }

    @Test
    fun whenUserProfileFetched_thenDisplayProfile() {
        whenever(usersApi.getUserDetails(Objects.LOGIN)).thenReturn(Observable.just(Objects.DETAILS))
        whenever(usersApi.getUserRepos(Objects.LOGIN)).thenReturn(Observable.just(Objects.REPOS))
        presenter.attachView(view)
        presenter.getData(Objects.LOGIN)
        verify(view).onDataFetched(Objects.DETAILS, Objects.REPOS)
    }

    @Test
    fun whenEmptyLogin_thenShowMessage() {
        presenter.attachView(view)
        presenter.getData(Objects.EMPTY_QUERY)
        verify(view).showMessage(any<Int>())
    }

    @Test
    fun whenUserProfileFetchError_thenShowMessage() {
        whenever(usersApi.getUserRepos(Objects.LOGIN)).thenReturn(Observable.error(Exception()))
        whenever(usersApi.getUserDetails(Objects.LOGIN)).thenReturn(Observable.error(Exception()))
        presenter.attachView(view)
        presenter.getData(Objects.LOGIN)
        verify(view).showMessage(any<Int>())
    }

    @Test
    fun whenRequest_thenHandleProgressBar() {
        whenever(usersApi.getUserDetails(Objects.LOGIN)).thenReturn(Observable.just(Objects.DETAILS))
        whenever(usersApi.getUserRepos(Objects.LOGIN)).thenReturn(Observable.just(Objects.REPOS))
        presenter.attachView(view)
        presenter.getData(Objects.LOGIN)
        verify(view, times(1)).progressBarVisibility(true)
        verify(view, times(1)).progressBarVisibility(false)
    }
}