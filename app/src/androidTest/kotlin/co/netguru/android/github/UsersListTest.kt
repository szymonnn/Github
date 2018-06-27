package co.netguru.android.github

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import co.netguru.android.github.assertions.RecyclerViewHasItemsAssertion
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
    fun main() {
        IdlingRegistry.getInstance().register(activityRule.activity.idling)
        search()
        waitFor(500)
        checkRecyclerViewFill()
        checkClickPromotesToDetails()
    }

    private fun search() {
        onView(withId(R.id.searchEditText)).perform(typeText("JakeWharton"))
    }

    private fun checkRecyclerViewFill() {
        onView(withId(R.id.recyclerView)).check(RecyclerViewHasItemsAssertion())
    }

    private fun checkClickPromotesToDetails() {
        onView(withId(R.id.recyclerView)).perform()
    }


    private fun waitFor(time: Long) {
        try {
            Thread.sleep(time)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }
}