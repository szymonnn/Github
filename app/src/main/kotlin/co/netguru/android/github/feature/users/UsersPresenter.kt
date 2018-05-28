package co.netguru.android.github.feature.users

import co.netguru.android.github.data.api.UsersApi
import co.netguru.android.github.data.model.User
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.item_user.view.*

class UsersPresenter(val usersApi: UsersApi) : MvpBasePresenter<UsersView>() {

    private var users: List<User> = listOf()

    fun onSearch(query: String) {
        usersApi.searchUsers(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ users ->
                    ifViewAttached { view ->
                        this.users = users.items
                        view.onSearchComplete()
                    }
                }, {
                    it.printStackTrace()
                })
    }

    fun usersCount(): Int {
        return users.size
    }

    fun bindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.itemView.userNameTextView.text = user.login
    }


}