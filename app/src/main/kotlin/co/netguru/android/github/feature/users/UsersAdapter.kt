package co.netguru.android.github.feature.users

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import co.netguru.android.github.R
import co.netguru.android.github.data.model.User
import io.reactivex.subjects.PublishSubject

class UsersAdapter : RecyclerView.Adapter<UserViewHolder>() {

    private val users = mutableListOf<User>()
    var itemClick: PublishSubject<User>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    fun setItems(list: List<User>?) {
        users.clear()
        if (list != null) {
            users.addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position], itemClick)
    }

}