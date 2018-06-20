package co.netguru.android.github.feature.details

import android.support.v7.widget.RecyclerView
import android.view.View
import co.netguru.android.github.data.model.details.Repo
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_repo.view.*

class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(repo: Repo, subject: PublishSubject<Repo>?) {
        itemView.repoNameTextView.text = repo.name
        RxView.clicks(itemView).subscribe { subject?.onNext(repo) }
    }

}