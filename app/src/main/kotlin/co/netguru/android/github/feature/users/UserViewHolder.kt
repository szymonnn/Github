package co.netguru.android.github.feature.users

import android.support.v7.widget.RecyclerView
import android.view.View
import co.netguru.android.github.data.model.User
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.subjects.PublishSubject

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User, subject: PublishSubject<User>?) {
        itemView.userNameTextView.text = user.login
        Glide.with(itemView).load(user.avatarUrl).into(itemView.avatarImageView)
        RxView.clicks(itemView).subscribe { subject?.onNext(user) }
    }

}