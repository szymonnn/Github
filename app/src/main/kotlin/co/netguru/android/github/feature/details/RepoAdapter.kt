package co.netguru.android.github.feature.details

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import co.netguru.android.github.R
import co.netguru.android.github.data.model.details.Repo
import io.reactivex.subjects.PublishSubject

class RepoAdapter : RecyclerView.Adapter<RepoViewHolder>() {

    private val repos = mutableListOf<Repo>()
    var itemClick: PublishSubject<Repo>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(view)
    }

    fun setItems(list: List<Repo>?) {
        repos.clear()
        if (list != null) {
            repos.addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount() = repos.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(repos[position], itemClick)
    }

}