package co.netguru.android.github.feature.details

import co.netguru.android.github.data.api.UsersApi
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before

import org.junit.Test

class DetailsPresenterTest {
    private lateinit var usersApi: UsersApi

    private lateinit var presenter: DetailsPresenter

    @Before
    fun setUp() {
        usersApi = mock()
        presenter = DetailsPresenter(usersApi)
    }

    @Test
    fun whenUserProfileFetched_thenDisplayProfile() {

    }
}