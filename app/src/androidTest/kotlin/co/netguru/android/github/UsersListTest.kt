package co.netguru.android.github

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import co.netguru.android.github.feature.users.UsersActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class UsersListTest{

    @get:Rule
    public val  activityRule = ActivityTestRule<UsersActivity>(UsersActivity::class.java)

    @Test
    fun typeTextTest(){
        onView(withId(R.id.searchEditText)).perform(typeText("JakeWharton"))
    }
}