package co.netguru.android.github.feature.users

import android.support.v7.widget.RecyclerView
import android.view.View
import co.netguru.android.github.data.model.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User) {
        itemView.userNameTextView.text = user.login
    }

}