package co.netguru.android.github.feature.users

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import co.netguru.android.github.R

class UsersAdapter(private val presenter: UsersContract.Presenter) : RecyclerView.Adapter<UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount() = presenter.usersCount()

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        presenter.bindViewHolder(holder, position);
    }

}