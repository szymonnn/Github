package co.netguru.android.github.assertions

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.matcher.ViewMatchers.assertThat
import android.support.v7.widget.RecyclerView
import android.view.View
import org.hamcrest.Matchers


class RecyclerViewHasItemsAssertion : ViewAssertion {
    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        noViewFoundException?.let { throw noViewFoundException }
        val rv = view as RecyclerView
        assertThat(rv.adapter.itemCount, Matchers.greaterThan(0))
    }
}